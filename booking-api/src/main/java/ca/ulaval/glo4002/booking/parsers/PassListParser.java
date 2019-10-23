package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.passes.EventDate;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.domain.passes.PassOption;
import ca.ulaval.glo4002.booking.dto.PassListDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.passes.DuplicatePassEventDateException;
import ca.ulaval.glo4002.booking.exceptions.passes.PackagePassWithEventDateException;
import ca.ulaval.glo4002.booking.exceptions.passes.SinglePassWithoutEventDateException;
import ca.ulaval.glo4002.booking.factories.PassFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PassListParser {

    private PassFactory passFactory;

    public PassListParser(PassFactory passFactory) {
        this.passFactory = passFactory;
    }

    public PassList parsePasses(PassListDto passListDto) {
        PassOptions passOption = parsePassOption(passListDto);
        PassCategories passCategory = parsePassCategory(passListDto);

        validateEventDates(passListDto.getEventDates(), passOption);

        PassList passList = passFactory.build(passCategory, passOption);

        List<Pass> passes = new ArrayList<>();
        if (passListDto.getEventDates() == null) {
            passes.add(new Pass());
        } else {
            passListDto.getEventDates().forEach(eventDate -> {
                EventDate passEventDate = new EventDate(eventDate);
                Pass pass = new Pass(passEventDate);
                passes.add(pass);
            });
        }

        passList.setPasses(passes);

        return passList;
    }

    private void validateEventDates(List<String> eventDates, PassOptions passOption) {
        if (eventDates == null) {
            if (passOption.equals(PassOptions.SINGLE_PASS)) {
                throw new SinglePassWithoutEventDateException();
            }
        } else {
            if (passOption.equals(PassOptions.PACKAGE)) {
                throw new PackagePassWithEventDateException();
            }

            boolean hasDuplicates = !eventDates.stream().allMatch(new HashSet<>()::add);
            if (hasDuplicates) {
                throw new DuplicatePassEventDateException();
            }
        }
    }

    private PassOptions parsePassOption(PassListDto passListDto) {
        return PassOptions.get(passListDto.getPassOption());
    }

    private PassCategories parsePassCategory(PassListDto passListDto) {
        return PassCategories.get(passListDto.getPassCategory());
    }
}
