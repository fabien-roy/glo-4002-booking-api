package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.dto.PassDto;
import ca.ulaval.glo4002.booking.entities.passes.Pass;
import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassDtoInvalidException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class PassParser implements Parser<List<Pass>, PassDto> {

    private PassCategoryBuilder categoryBuilder;
    private PassOptionBuilder optionBuilder;
    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FestivalConstants.Dates.FORMAT);

    public void setBuilders(PassCategoryBuilder categoryBuilder, PassOptionBuilder optionBuilder) {
        this.categoryBuilder = categoryBuilder;
        this.optionBuilder = optionBuilder;
    }

    @Override
    public List<Pass> parse(PassDto dto) throws PassDtoInvalidException {
        if (dto.passNumber < 1L) {
            throw new PassDtoInvalidException();
        }

        PassCategory category;
        PassOption option;

        try {
            category = categoryBuilder.buildByName(dto.passCategory);
            option = optionBuilder.buildByName(dto.passOption);
        } catch (FestivalException exception) {
            throw new PassDtoInvalidException();
        }

        List<Pass> passes = new ArrayList<>();

        for (String eventDate : dto.eventDates) {
            passes.add(parseSingle(dto.passNumber, category, option, eventDate));
        }

        return passes;
    }

    private Pass parseSingle(Long id, PassCategory category, PassOption option, String eventDate) throws PassDtoInvalidException {
        LocalDate parsedEventDate;

        try {
            parsedEventDate = LocalDate.parse(eventDate, dateTimeFormatter);
        } catch (DateTimeParseException exception) {
            throw new PassDtoInvalidException();
        }

        validateEventDate(parsedEventDate);

        return new Pass(category, option, id, parsedEventDate);
    }

    private void validateEventDate(LocalDate eventDate) throws PassDtoInvalidException {
        if (eventDate.isBefore(FestivalConstants.Dates.START_DATE)
            || eventDate.isAfter(FestivalConstants.Dates.END_DATE)) {
            throw new PassDtoInvalidException();
        }
    }
}
