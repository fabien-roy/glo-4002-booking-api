package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.domainobjects.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.domainobjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.dto.PassDto;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassDtoInvalidException;
import ca.ulaval.glo4002.booking.util.FestivalDateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassParser implements EntityParser<Pass, PassEntity>, ParseDtoParser<List<Pass>, PassesDto>, ToDtoParser<List<Pass>, List<PassDto>> {

    private PassCategoryBuilder categoryBuilder = new PassCategoryBuilder();
    private PassOptionBuilder optionBuilder = new PassOptionBuilder();

    @Override
    public List<Pass> parseDto(PassesDto dto) {
        PassCategory category = categoryBuilder.buildByName(dto.passCategory);
        PassOption option = optionBuilder.buildByName(dto.passOption);

        if (option.getId().equals(PassConstants.Options.PACKAGE_ID)) {
            if (dto.eventDates != null) {
                throw new PassDtoInvalidException();
            }

            return new ArrayList<>(Collections.singletonList(new Pass(dto.passNumber, category, option)));
        }

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
    public List<PassDto> toDto(List<Pass> passes) {
        List<PassDto> dtos = new ArrayList<>();

        passes.forEach(pass -> dtos.add(toSingle(pass)));

        return dtos;
    }

    @Override
    public PassEntity toEntity(Pass pass) {
        PassEntity newPassEntity;
        if(pass.getOption().getId().equals(PassConstants.Options.PACKAGE_ID)){
            newPassEntity = new PassEntity(pass.getId(),
                    pass.getCategory().getId(),
                    pass.getOption().getId()
            );
        }
        else{
            newPassEntity = new PassEntity(pass.getId(),
                    pass.getCategory().getId(),
                    pass.getOption().getId(),
                    pass.getEventDate()
            );
        }
        return newPassEntity;
    }

    private Pass parseSingle(Long id, PassCategory category, PassOption option, LocalDate eventDate) {
        validateEventDate(eventDate);

        return new Pass(id, category, option, eventDate);
    }

    private PassDto toSingle(Pass pass) {
        PassDto dto = new PassDto();
        dto.passNumber = pass.getId();
        dto.passCategory = pass.getCategory().getName();
        dto.passOption = pass.getOption().getName();

        if (pass.getOption().getId().equals(PassConstants.Options.SINGLE_ID)) {
            dto.eventDate = pass.getEventDate().toString();
        }

        return dto;
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
