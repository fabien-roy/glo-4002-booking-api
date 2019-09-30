package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.domainObjects.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.domainObjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.exceptions.passes.PassDtoInvalidException;
import ca.ulaval.glo4002.booking.validators.FestivalDateValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassParser implements Parser<List<Pass>, PassesDto, PassEntity> {

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

        for (LocalDate eventDate : dto.eventDates) {
            passes.add(parseSingle(dto.passNumber, category, option, eventDate));
        }

        return passes;
    }

    @Override
    public List<Pass> parseEntity(PassEntity entity) {
        PassCategory category = categoryBuilder.buildById(entity.categoryId);
        PassOption option = optionBuilder.buildById(entity.optionId);

        return new ArrayList<>(Collections.singletonList(new Pass(entity.id, category, option, entity.eventDate)));
    }

    // TODO : This should work for a list of passes (but never will be used this way)
    @Override
    public PassesDto toDto(List<Pass> passes) {
        Pass pass = passes.get(0);

        PassesDto dto = new PassesDto();
        dto.passNumber = pass.getId();
        dto.passCategory = pass.getCategory().getName();
        dto.passOption = pass.getOption().getName();
        dto.eventDates = new ArrayList<>(Collections.singletonList(pass.getEventDate()));

        return dto;
    }

    // TODO : This should work for a list of passes (but never will be used this way)
    @Override
    public PassEntity toEntity(List<Pass> passes) {
        Pass pass = passes.get(0);

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

    private void validateEventDate(LocalDate eventDate) {
        if (FestivalDateValidator.isOutsideFestivalDates(eventDate)) {
            throw new PassDtoInvalidException();
        }
    }
}
