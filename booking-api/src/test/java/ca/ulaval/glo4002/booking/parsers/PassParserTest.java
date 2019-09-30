package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.exceptions.passes.PassCategoryNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassDtoInvalidException;
import ca.ulaval.glo4002.booking.exceptions.passes.PassOptionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PassParserTest {

    private PassParser subject = new PassParser();
    private PassesDto dto = new PassesDto();
    private final static Long A_PASS_NUMBER = 1L;
    private final static String A_PASS_CATEGORY = PassConstants.Categories.SUPERNOVA_NAME;
    private final static String A_PASS_OPTION = PassConstants.Options.SINGLE_NAME;
    private final static List<String> SOME_EVENT_DATES = new ArrayList<>(Arrays.asList(
            FestivalConstants.Dates.START_DATE.toString(),
            FestivalConstants.Dates.START_DATE.plusDays(1).toString()
    ));
    private final static Long AN_INVALID_PASS_NUMBER = -1L;
    private final static String AN_INVALID_PASS_CATEGORY = "anInvalidPassCategory";
    private final static String AN_INVALID_PASS_OPTION = "anInvalidPassOption";
    private final static List<String> SOME_EVENT_DATES_NOT_IN_FESTIVAL = new ArrayList<>(Collections.singletonList(
            FestivalConstants.Dates.END_DATE.plusDays(1).toString()
    ));

    @BeforeEach
    void setUp() {
        subject = new PassParser();
        dto.passNumber = A_PASS_NUMBER;
        dto.passCategory = A_PASS_CATEGORY;
        dto.passOption = A_PASS_OPTION;
        dto.eventDates = SOME_EVENT_DATES;
    }

    @Test
    void parseDto_shouldThrowInvalidPassDtoException_whenPassNumberIsInvalid() {
        dto.passNumber = AN_INVALID_PASS_NUMBER;

        PassDtoInvalidException thrown = assertThrows(
                PassDtoInvalidException.class,
                () -> subject.parseDto(dto)
        );

        assertEquals(ExceptionConstants.PASS_DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    void parseDto_shouldThrowPassCategoryNotFoundException_whenPassCategoryIsInvalid() {
        dto.passCategory = AN_INVALID_PASS_CATEGORY;

        PassCategoryNotFoundException thrown = assertThrows(
                PassCategoryNotFoundException.class,
                () -> subject.parseDto(dto)
        );

        assertEquals(ExceptionConstants.PASS_CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void parseDto_shouldThrowPassOptionNotFoundException_whenPassOptionIsInvalid() {
        dto.passOption = AN_INVALID_PASS_OPTION;

        PassOptionNotFoundException thrown = assertThrows(
                PassOptionNotFoundException.class,
                () -> subject.parseDto(dto)
        );

        assertEquals(ExceptionConstants.PASS_OPTION_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void parseDto_shouldThrowInvalidPassDtoException_whenEventDatesAreNotInFestivalDates() {
        dto.eventDates = SOME_EVENT_DATES_NOT_IN_FESTIVAL;

        PassDtoInvalidException thrown = assertThrows(
                PassDtoInvalidException.class,
                () -> subject.parseDto(dto)
        );

        assertEquals(ExceptionConstants.PASS_DTO_INVALID_MESSAGE, thrown.getMessage());
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
        // TODO : Readd eventDates = null when checking for package and single pass
        // dto.eventDates = null;

        Pass pass = subject.parseDto(dto).get(0);

        assertEquals(PassConstants.Options.PACKAGE_ID, pass.getOption().getId());
    }

    @Test
    void parseDto_shouldReturnPassWithSinglePassOption_whenPassOptionIsSinglePass() {
        dto.passOption = PassConstants.Options.SINGLE_NAME;

        Pass pass = subject.parseDto(dto).get(0);

        assertEquals(PassConstants.Options.SINGLE_ID, pass.getOption().getId());
    }

    // TODO : Test parseEntity

    // TODO : Test toEntity

    // TODO : Test toDto
}
