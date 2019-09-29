package ca.ulaval.glo4002.booking.builders.passes;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import ca.ulaval.glo4002.booking.exceptions.passes.PassOptionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassOptionBuilderTest {

    private PassOptionBuilder subject;
    private static final Long AN_INVALID_ID = -1L;
    private static final String AN_INVALID_NAME = "An invalid code";

    @BeforeEach
    public void setUp() {
        subject = new PassOptionBuilder();
    }

    @Test
    public void buildById_shouldThrowPassOptionNotFoundException_whenOptionDoesNotExist() {
        PassOptionNotFoundException thrown = assertThrows(
                PassOptionNotFoundException.class,
                () -> subject.buildById(AN_INVALID_ID)
        );

        assertEquals(ExceptionConstants.PASS_OPTION_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildById_shouldReturnCorrectPassOption_whenCategoryIsPackage() {
        PassOption passOption = subject.buildById(PassConstants.Options.PACKAGE_ID);

        validatePassOption(
                passOption,
                PassConstants.Options.PACKAGE_ID,
                PassConstants.Options.PACKAGE_NAME
        );
    }

    @Test
    public void buildById_shouldReturnCorrectPassOption_whenCategoryIsSingle() {
        PassOption passOption = subject.buildById(PassConstants.Options.SINGLE_ID);

        validatePassOption(
                passOption,
                PassConstants.Options.SINGLE_ID,
                PassConstants.Options.SINGLE_NAME
        );
    }

    @Test
    public void buildByName_shouldThrowPassOptionNotFoundException_whenOptionDoesNotExist() {
        PassOptionNotFoundException thrown = assertThrows(
                PassOptionNotFoundException.class,
                () -> subject.buildByName(AN_INVALID_NAME)
        );

        assertEquals(ExceptionConstants.PASS_OPTION_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildByName_shouldReturnCorrectPassOption_whenCategoryIsPackage() {
        PassOption passOption = subject.buildByName(PassConstants.Options.PACKAGE_NAME);

        validatePassOption(
                passOption,
                PassConstants.Options.PACKAGE_ID,
                PassConstants.Options.PACKAGE_NAME
        );
    }

    @Test
    public void buildByName_shouldReturnCorrectPassOption_whenCategoryIsSingle() {
        PassOption passOption = subject.buildByName(PassConstants.Options.SINGLE_NAME);

        validatePassOption(
                passOption,
                PassConstants.Options.SINGLE_ID,
                PassConstants.Options.SINGLE_NAME
        );
    }

    private void validatePassOption(PassOption passOption, Long id, String name) {
        assertNotNull(passOption);
        assertEquals(passOption.getId(), id);
        assertEquals(passOption.getName(), name);
    }
}
