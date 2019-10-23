package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.passes.EventDate;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassCategory;
import ca.ulaval.glo4002.booking.domain.passes.Passes;
import ca.ulaval.glo4002.booking.domain.passes.options.PackagePassOption;
import ca.ulaval.glo4002.booking.domain.passes.options.PassOption;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.passes.DuplicatePassEventDateException;
import ca.ulaval.glo4002.booking.exceptions.passes.PackagePassWithEventDateException;
import ca.ulaval.glo4002.booking.exceptions.passes.SinglePassWithoutEventDateException;
import ca.ulaval.glo4002.booking.factories.PassFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PassesParser {

    private PassFactory passFactory;

    public PassesParser(PassFactory passFactory) {
        this.passFactory = passFactory;
    }

    public Passes parsePasses(PassesDto passesDto) {
        List<Pass> passes = new ArrayList<>();

        PassOption passOption = parsePassOption(passesDto);
        PassCategory passCategory = parsePassCategory(passesDto);

        validateEventDates(passesDto.getEventDates(), passOption);

        passesDto.getEventDates().forEach(eventDate -> {
            EventDate passEventDate = new EventDate(eventDate);
            Pass pass = new Pass(passEventDate);
            passes.add(pass);
        });

        return new Passes(passes, passCategory, passOption);
    }

    private void validateEventDates(List<String> eventDates, PassOption passOption) {
        if (eventDates != null && passOption instanceof PackagePassOption) {
            throw new PackagePassWithEventDateException();
        } else if (eventDates == null) {
            throw new SinglePassWithoutEventDateException();
        }

        boolean hasDuplicates = !eventDates.stream().allMatch(new HashSet<>()::add);
        if (hasDuplicates) {
            throw new DuplicatePassEventDateException();
        }
    }

    private PassOption parsePassOption(PassesDto passesDto) {
        PassOptions passOptionElem = PassOptions.get(passesDto.getPassOption());

        return passFactory.buildPassOption(passOptionElem);
    }

    private PassCategory parsePassCategory(PassesDto passesDto) {
        PassCategories passCategoryElem = PassCategories.get(passesDto.getPassCategory());

        return passFactory.buildPassCategory(passCategoryElem);
    }
}
