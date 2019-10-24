package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.passes.EventDate;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.dto.PassListDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.passes.InvalidEventDateFormatException;
import ca.ulaval.glo4002.booking.exceptions.passes.PackagePassWithEventDateException;
import ca.ulaval.glo4002.booking.exceptions.passes.SinglePassWithoutEventDateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PassListFactory {

    private NumberGenerator numberGenerator;
    private PassFactory passFactory;

    public PassListFactory(NumberGenerator numberGenerator, PassFactory passFactory) {
        this.numberGenerator = numberGenerator;
        this.passFactory = passFactory;
    }

    public PassList buildWithDto(PassListDto passListDto) {
        PassOptions passOption = parsePassOption(passListDto);
        PassCategories passCategory = parsePassCategory(passListDto);

        validateEventDates(passListDto.getEventDates(), passOption);

        PassList passList = passFactory.build(passCategory, passOption);

        List<Pass> passes = new ArrayList<>();
        if (passListDto.getEventDates() == null) {
            passes.add(new Pass(numberGenerator.generate()));
        } else {
            passListDto.getEventDates().forEach(eventDate -> {
                EventDate builtEventDate = buildEventDate(eventDate);
                Pass pass = new Pass(numberGenerator.generate(), builtEventDate);
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
        }
    }

    private EventDate buildEventDate(String eventDate) {
        LocalDate parsedEventDate;

        try {
            parsedEventDate = LocalDate.parse(eventDate);
        } catch (Exception exception) {
            throw new InvalidEventDateFormatException();
        }

        return new EventDate(parsedEventDate);
    }

    private PassOptions parsePassOption(PassListDto passListDto) {
        return PassOptions.get(passListDto.getPassOption());
    }

    private PassCategories parsePassCategory(PassListDto passListDto) {
        return PassCategories.get(passListDto.getPassCategory());
    }
}
