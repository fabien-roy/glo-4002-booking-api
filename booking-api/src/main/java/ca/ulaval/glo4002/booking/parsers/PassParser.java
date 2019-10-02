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
import ca.ulaval.glo4002.booking.exceptions.passes.PassInvalidDateException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassInvalidFormatException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassOptionPackageWithEventDateException;
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
                throw new PassOptionPackageWithEventDateException();
            }

            try {
                return new ArrayList<>(Collections.singletonList(new Pass(dto.passNumber, category, option)));
            } catch (Exception exception) {
                throw new PassInvalidFormatException();
            }
        }

        List<Pass> passes = new ArrayList<>();

        for (String eventDate : dto.eventDates) {
            passes.add(parseSingleDto(dto.passNumber, category, option, parseEventDate(eventDate)));
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

    private Pass parseSingleDto(Long id, PassCategory category, PassOption option, LocalDate eventDate) {
        validateEventDate(eventDate);

        try {
            return new Pass(id, category, option, eventDate);
        } catch (Exception exception) {
            throw new PassInvalidFormatException();
        }
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
            throw new PassInvalidFormatException();
        }
    }

    private void validateEventDate(LocalDate eventDate) {
        if (FestivalDateUtil.isOutsideFestivalDates(eventDate)) {
            throw new PassInvalidDateException();
        }
    }
}
