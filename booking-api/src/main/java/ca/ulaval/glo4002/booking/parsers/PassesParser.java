package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassCategory;
import ca.ulaval.glo4002.booking.domain.passes.options.PassOption;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.factories.PassFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PassesParser {

    private PassFactory passFactory;

    public PassesParser(PassFactory passFactory) {
        this.passFactory = passFactory;
    }

    public List<Pass> parsePasses(PassesDto passesDto) {
        List<Pass> passes = new ArrayList<>();

        PassOption passOption = parsePassOption(passesDto);
        PassCategory passCategory = parsePassCategory(passesDto);

        passesDto.getEventDates().forEach(eventDate -> {
            Pass pass = new Pass(LocalDate.parse(eventDate), passOption, passCategory);
            passes.add(pass);
        });

        return passes;
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
