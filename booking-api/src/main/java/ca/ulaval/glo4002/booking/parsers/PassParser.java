package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.dto.PassDto;
import ca.ulaval.glo4002.booking.entities.passes.Pass;
import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import ca.ulaval.glo4002.booking.exceptions.passes.PassDtoInvalidException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PassParser implements Parser<List<Pass>, PassDto> {

    @Override
    public List<Pass> parse(PassDto dto) {
        if (dto.passNumber < 1L) {
            throw new PassDtoInvalidException();
        }

        PassCategoryBuilder categoryBuilder = new PassCategoryBuilder();
        PassOptionBuilder optionBuilder = new PassOptionBuilder();

        PassCategory category = categoryBuilder.buildByName(dto.passCategory);
        PassOption option = optionBuilder.buildByName(dto.passOption);

        List<Pass> passes = new ArrayList<>();

        for (LocalDate eventDate : dto.eventDates) {
            passes.add(parseSingle(dto.passNumber, category, option, eventDate));
        }

        return passes;
    }

    private Pass parseSingle(Long id, PassCategory category, PassOption option, LocalDate eventDate) {
        validateEventDate(eventDate);

        return new Pass(category, option, id, eventDate);
    }

    private void validateEventDate(LocalDate eventDate) {
        if (eventDate.isBefore(FestivalConstants.Dates.START_DATE)
            || eventDate.isAfter(FestivalConstants.Dates.END_DATE)) {
            throw new PassDtoInvalidException();
        }
    }
}
