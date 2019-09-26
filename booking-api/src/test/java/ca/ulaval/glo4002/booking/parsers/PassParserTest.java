package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.builders.passes.PassOptionBuilder;
import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.dto.PassDto;
import ca.ulaval.glo4002.booking.entities.passes.Pass;
import ca.ulaval.glo4002.booking.exceptions.passes.PassDtoInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PassParserTest {

    private PassParser subject = new PassParser();
    private PassDto dto = new PassDto();
    private PassCategoryBuilder categoryBuilder = new PassCategoryBuilder();
    private PassOptionBuilder optionBuilder = new PassOptionBuilder();
    private final static Long A_PASS_NUMBER = 1L;
    private final static String A_PASS_CATEGORY = PassConstants.Categories.SUPERNOVA_NAME;
    private final static String A_PASS_OPTION = PassConstants.Options.SINGLE_NAME;
    private final static List<String> SOME_EVENT_DATES = new ArrayList<>(Arrays.asList("2050-07-17", "2050-07-18"));
    private final static Long AN_INVALID_PASS_NUMBER = -1L;
    private final static String AN_INVALID_PASS_CATEGORY = "anInvalidPassCategory";
    private final static String AN_INVALID_PASS_OPTION = "anInvalidPassOption";
    private final static List<String> SOME_INVALID_EVENT_DATES = new ArrayList<>(Collections.singletonList("anInvalidEventDate"));
    private final static List<String> SOME_EVENT_DATES_NOT_IN_FESTIVAL = new ArrayList<>(Collections.singletonList("2001-07-17"));

    @BeforeEach
    public void setUp() {
        subject = new PassParser();
        subject.setBuilders(categoryBuilder, optionBuilder);
        dto.passNumber = A_PASS_NUMBER;
        dto.passCategory = A_PASS_CATEGORY;
        dto.passOption = A_PASS_OPTION;
        dto.eventDates = SOME_EVENT_DATES;
    }

    @Test
    public void parse_shouldThrowInvalidPassDtoException_whenPassNumberIsInvalid() {
        dto.passNumber = AN_INVALID_PASS_NUMBER;

        PassDtoInvalidException thrown = assertThrows(
                PassDtoInvalidException.class,
                () -> subject.parse(dto)
        );

        assertEquals(ExceptionConstants.PASS_DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    public void parse_shouldThrowInvalidPassDtoException_whenPassCategoryIsInvalid() {
        dto.passCategory = AN_INVALID_PASS_CATEGORY;

        PassDtoInvalidException thrown = assertThrows(
                PassDtoInvalidException.class,
                () -> subject.parse(dto)
        );

        assertEquals(ExceptionConstants.PASS_DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    public void parse_shouldThrowInvalidPassDtoException_whenPassOptionIsInvalid() {
        dto.passOption = AN_INVALID_PASS_OPTION;

        PassDtoInvalidException thrown = assertThrows(
                PassDtoInvalidException.class,
                () -> subject.parse(dto)
        );

        assertEquals(ExceptionConstants.PASS_DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    public void parse_shouldThrowInvalidPassDtoException_whenEventDatesAreInvalid() {
        dto.eventDates = SOME_INVALID_EVENT_DATES;

        PassDtoInvalidException thrown = assertThrows(
                PassDtoInvalidException.class,
                () -> subject.parse(dto)
        );

        assertEquals(ExceptionConstants.PASS_DTO_INVALID_MESSAGE, thrown.getMessage());
    }

    @Test
    public void parse_shouldThrowInvalidPassDtoException_whenEventDatesAreNotInFestivalDates() {
        // TODO : Check if pass eventDates are in festival
        /*
        dto.eventDates = SOME_EVENT_DATES_NOT_IN_FESTIVAL;

        PassDtoInvalidException thrown = assertThrows(
                PassDtoInvalidException.class,
                () -> subject.parse(dto)
        );

        assertEquals(ExceptionConstants.PASS_DTO_INVALID_MESSAGE, thrown.getMessage());
        */
    }

    @Test
    public void parse_shouldReturnPassWithSupernovaPassCategory_whenPassCategoryIsSupernova() {
        dto.passCategory = PassConstants.Categories.SUPERNOVA_NAME;

        Pass pass = subject.parse(dto).get(0);

        assertEquals(PassConstants.Categories.SUPERNOVA_ID, pass.getCategory().getId());
    }

    @Test
    public void parse_shouldReturnPassWithSupernovaPassCategory_whenPassCategoryIsSupergiant() {
        dto.passCategory = PassConstants.Categories.SUPERGIANT_NAME;

        Pass pass = subject.parse(dto).get(0);

        assertEquals(PassConstants.Categories.SUPERGIANT_ID, pass.getCategory().getId());
    }

    @Test
    public void parse_shouldReturnPassWithSupernovaPassCategory_whenPassCategoryIsNebula() {
        dto.passCategory = PassConstants.Categories.NEBULA_NAME;

        Pass pass = subject.parse(dto).get(0);

        assertEquals(PassConstants.Categories.NEBULA_ID, pass.getCategory().getId());
    }

    @Test
    public void parse_shouldReturnPassWithPackagePassOption_whenPassOptionIsPackage() {
        dto.passOption = PassConstants.Options.PACKAGE_NAME;
        // TODO : Readd eventDates = null when checking for package and single pass
        // dto.eventDates = null;

        Pass pass = subject.parse(dto).get(0);

        assertEquals(PassConstants.Options.PACKAGE_ID, pass.getOption().getId());
    }

    @Test
    public void parse_shouldReturnPassWithSinglePassOption_whenPassOptionIsSinglePass() {
        dto.passOption = PassConstants.Options.SINGLE_NAME;

        Pass pass = subject.parse(dto).get(0);

        assertEquals(PassConstants.Options.SINGLE_ID, pass.getOption().getId());
    }
}
