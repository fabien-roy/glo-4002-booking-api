package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.domainobjects.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.domainobjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassDtoInvalidException;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PassParser implements EntityParser<Pass, PassEntity>, DtoParser<List<Pass>, PassesDto> {

    private PassCategoryBuilder categoryBuilder = new PassCategoryBuilder();
    private PassOptionBuilder optionBuilder = new PassOptionBuilder();

    @Override
    public List<Pass> parseDto(PassesDto dto) {
        if (dto.passNumber < 1L) {
            throw new PassDtoInvalidException();
        }

        PassCategory category = categoryBuilder.buildByName(dto.passCategory);
        PassOption option = optionBuilder.buildByName(dto.passOption);

        List<Pass> passes = new ArrayList<>();

        for (String eventDate : dto.eventDates) {
            passes.add(parseSingle(dto.passNumber, category, option, parseEventDate(eventDate)));
        }

        return passes;
    }

    @Override
    public Pass parseEntity(PassEntity entity) {
        PassCategory category = categoryBuilder.buildById(entity.getCategoryId());
        PassOption option = optionBuilder.buildById(entity.getOptionId());

        return new Pass(entity.getId(), category, option, entity.getEventDate());
    }

    @Override
    public PassesDto toDto(List<Pass> passes) {
        Pass pass = passes.get(0);

        PassesDto dto = new PassesDto();
        dto.passNumber = pass.getId();
        dto.passCategory = pass.getCategory().getName();
        dto.passOption = pass.getOption().getName();
        // dto.eventDates = new ArrayList<>(Collections.singletonList(pass.getEventDate()));

        return dto;
    }

    @Override
    public PassEntity toEntity(Pass pass) {
        return new PassEntity(
                pass.getId(),
                pass.getCategory().getId(),
                pass.getOption().getId(),
                pass.getEventDate()
        );
    }

    private Pass parseSingle(Long id, PassCategory category, PassOption option, LocalDate eventDate) {
        validateEventDate(eventDate);

        return new Pass(id, category, option, eventDate);
    }

    private LocalDate parseEventDate(String eventDate) {
        try {
            return FestivalDateUtil.toLocalDate(eventDate);
        } catch(InvalidDateException exception) {
            throw new PassDtoInvalidException();
        }
    }

    private void validateEventDate(LocalDate eventDate) {
        if (FestivalDateUtil.isOutsideFestivalDates(eventDate)) {
            throw new PassDtoInvalidException();
        }
    }
}
