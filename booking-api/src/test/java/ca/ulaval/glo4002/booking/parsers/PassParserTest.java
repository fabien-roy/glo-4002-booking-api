package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.dto.PassDto;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.entities.PassEntity;
import ca.ulaval.glo4002.booking.exceptions.passes.PassCategoryNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassDtoInvalidException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassOptionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PassParserTest {

    private final static Long A_PASS_NUMBER = 1L;
    private final static String A_PASS_CATEGORY = PassConstants.Categories.SUPERNOVA_NAME;
    private final static String A_PASS_OPTION = PassConstants.Options.SINGLE_NAME;
    private final static List<LocalDate> SOME_EVENT_DATES = new ArrayList<>(Arrays.asList(
            DateConstants.START_DATE,
            DateConstants.START_DATE.plusDays(1)
    ));
    private final static String AN_INVALID_PASS_CATEGORY = "anInvalidPassCategory";
    private final static String AN_INVALID_PASS_OPTION = "anInvalidPassOption";
    private final static List<LocalDate> SOME_EVENT_DATES_NOT_IN_FESTIVAL = new ArrayList<>(Collections.singletonList(
            DateConstants.END_DATE.plusDays(1)
    ));
    private PassParser subject = new PassParser();
    private PassesDto dto = new PassesDto();
    private Pass pass;
    private PassCategoryBuilder categoryBuilder = new PassCategoryBuilder();
    private PassOptionBuilder optionBuilder = new PassOptionBuilder();

    @BeforeEach
    void setUp() {
        subject = new PassParser();
        dto.passNumber = A_PASS_NUMBER;
        dto.passCategory = A_PASS_CATEGORY;
        dto.passOption = A_PASS_OPTION;
        dto.eventDates = new ArrayList<>();

        SOME_EVENT_DATES.forEach(eventDate -> dto.eventDates.add(eventDate.toString()));

        pass = new Pass(
                A_PASS_NUMBER,
                categoryBuilder.buildByName(A_PASS_CATEGORY),
                optionBuilder.buildByName(A_PASS_OPTION),
                SOME_EVENT_DATES.get(0)
        );
    }

    @Test
    void parseDto_shouldThrowPassCategoryNotFoundException_whenPassCategoryIsInvalid() {
        dto.passCategory = AN_INVALID_PASS_CATEGORY;

        PassCategoryNotFoundException thrown = assertThrows(
                PassCategoryNotFoundException.class,
                () -> subject.parseDto(dto)
        );

        assertEquals(ExceptionConstants.Pass.CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void parseDto_shouldThrowPassOptionNotFoundException_whenPassOptionIsInvalid() {
        dto.passOption = AN_INVALID_PASS_OPTION;

        PassOptionNotFoundException thrown = assertThrows(
                PassOptionNotFoundException.class,
                () -> subject.parseDto(dto)
        );

        assertEquals(ExceptionConstants.Pass.OPTION_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void parseDto_shouldThrowInvalidPassDtoException_whenEventDatesAreNotInFestivalDates() {
        SOME_EVENT_DATES_NOT_IN_FESTIVAL.forEach(eventDate -> dto.eventDates.add(eventDate.toString()));

        PassDtoInvalidException thrown = assertThrows(
                PassDtoInvalidException.class,
                () -> subject.parseDto(dto)
        );

        assertEquals(ExceptionConstants.Pass.DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    void parseDto_shouldThrowInvalidPassDtoException_whenEventDatesIsNotNull_andOptionIsPackage() {
        SOME_EVENT_DATES_NOT_IN_FESTIVAL.forEach(eventDate -> dto.eventDates.add(eventDate.toString()));
        dto.passOption = PassConstants.Options.PACKAGE_NAME;

        PassDtoInvalidException thrown = assertThrows(
                PassDtoInvalidException.class,
                () -> subject.parseDto(dto)
        );

        assertEquals(ExceptionConstants.Pass.DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    void parseDto_shouldReturnPassWithSupernovaPassCategory_whenPassCategoryIsSupernova() {
        dto.passCategory = PassConstants.Categories.SUPERNOVA_NAME;

        Pass pass = subject.parseDto(dto).get(0);

        assertEquals(PassConstants.Categories.SUPERNOVA_ID, pass.getCategory().getId());
    }

    @Test
    void parseDto_shouldReturnPassWithSupernovaPassCategory_whenPassCategoryIsSupergiant() {
        dto.passCategory = PassConstants.Categories.SUPERGIANT_NAME;

        Pass pass = subject.parseDto(dto).get(0);

        assertEquals(PassConstants.Categories.SUPERGIANT_ID, pass.getCategory().getId());
    }

    @Test
    void parseDto_shouldReturnPassWithSupernovaPassCategory_whenPassCategoryIsNebula() {
        dto.passCategory = PassConstants.Categories.NEBULA_NAME;

        Pass pass = subject.parseDto(dto).get(0);

        assertEquals(PassConstants.Categories.NEBULA_ID, pass.getCategory().getId());
    }

    @Test
    void parseDto_shouldReturnPassWithPackagePassOption_whenPassOptionIsPackage() {
        dto.passOption = PassConstants.Options.PACKAGE_NAME;
        dto.eventDates = null;

        Pass pass = subject.parseDto(dto).get(0);

        assertEquals(PassConstants.Options.PACKAGE_ID, pass.getOption().getId());
    }

    @Test
    void parseDto_shouldReturnPassWithSinglePassOption_whenPassOptionIsSinglePass() {
        dto.passOption = PassConstants.Options.SINGLE_NAME;

        Pass pass = subject.parseDto(dto).get(0);

        assertEquals(PassConstants.Options.SINGLE_ID, pass.getOption().getId());
    }

    @Test
    void parseDto_shouldReturnMultiplePassWithSinglePassOption_whenMultiplePassWithSinglePassOption() {
        dto.passOption = PassConstants.Options.SINGLE_NAME;

        List<Pass> passes = subject.parseDto(dto);

        assertEquals(2, passes.size());
    }

    @Test
    void whenParsingEntity_orderShouldBeValid() {
        PassEntity entity = subject.toEntity(pass);

        Pass parsedPass = subject.parseEntity(entity);

        assertEquals(entity.getId(), parsedPass.getId());
        assertEquals(entity.getCategoryId(), parsedPass.getCategory().getId());
        assertEquals(entity.getOptionId(), parsedPass.getOption().getId());
        assertEquals(entity.getEventDate(), parsedPass.getEventDate());
    }

    @Test
    void whenParsingToEntity_entityShouldBeValid() {
        PassEntity entity = subject.toEntity(pass);

        assertEquals(pass.getId(), entity.getId());
        assertEquals(pass.getCategory().getId(), entity.getCategoryId());
        assertEquals(pass.getOption().getId(), entity.getOptionId());
        assertEquals(pass.getEventDate(), entity.getEventDate());
    }

    @Test
    void whenParsingToDto_dtoShouldBeValid() {
        List<PassDto> dtos = subject.toDto(new ArrayList<>(Collections.singletonList(pass)));
        PassDto dto = dtos.get(0);

        assertEquals(pass.getId(), dto.passNumber);
        assertEquals(pass.getCategory().getName(), dto.passCategory);
        assertEquals(pass.getOption().getName(), dto.passOption);
        assertEquals(pass.getEventDate().toString(), dto.eventDate);
    }
}
