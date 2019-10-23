package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.passes.InvalidPassCategoryException;
import ca.ulaval.glo4002.booking.exceptions.passes.InvalidPassOptionException;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PassesParserTest {

    private PassesParser subject;
    private PassFactory passFactory;

    @BeforeEach
    void setUpSubject() {
        passFactory = mock(PassFactory.class);
        subject = new PassesParser(passFactory);
    }

    // TODO : Refactor to fit actual parser

    @Test
    void parsePasses_shouldThrowInvalidPassOptionException_whenPassOptionDoesNotExist() {
        String anInvalidPassOption = "anInvalidPassOption";
        PassesDto passesDto = new PassesDto(PassCategories.SUPERNOVA.toString(), anInvalidPassOption, new ArrayList<>());

        assertThrows(InvalidPassOptionException.class, () -> subject.parsePasses(passesDto));
    }

    @Test
    void parsePasses_shouldBuildPassOption() {
        String aValidPassOption = PassOptions.PACKAGE.toString();
        PassesDto passesDto = new PassesDto(PassCategories.SUPERNOVA.toString(), aValidPassOption, new ArrayList<>());

        subject.parsePasses(passesDto);

        verify(passFactory).buildPassOption(any());
    }

    @Test
    void parsePasses_shouldThrowInvalidPassCategoryException_whenPassCategoryDoesNotExist() {
        String anInvalidPassOption = "anInvalidPassCategory";
        PassesDto passesDto = new PassesDto(anInvalidPassOption, PassOptions.PACKAGE.toString(), new ArrayList<>());

        assertThrows(InvalidPassCategoryException.class, () -> subject.parsePasses(passesDto));
    }

    @Test
    void parsePasses_shouldBuildPassCategory() {
        String aValidPassCategory = PassCategories.SUPERNOVA.toString();
        PassesDto passesDto = new PassesDto(aValidPassCategory, PassOptions.PACKAGE.toString(), new ArrayList<>());

        subject.parsePasses(passesDto);

        verify(passFactory).buildPassCategory(any());
    }
}