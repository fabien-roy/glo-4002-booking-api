package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.InvalidPassOptionException;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PassesParserTest {

    private PassesParser subject;

    @Test
    void parsePassOption_shouldThrowInvalidPassOptionException_whenPassOptionDoesNotExist() {
        subject = new PassesParser(mock(PassFactory.class));
        String anInvalidPassOption = "anInvalidPassOption";
        PassesDto passesDto = new PassesDto("lol", anInvalidPassOption, new ArrayList<>());

        assertThrows(InvalidPassOptionException.class, () -> subject.parsePassOption(passesDto));
    }

    @Test
    void parsePassOption_shouldBuildPassOption() {
        PassFactory passFactory = mock(PassFactory.class);
        subject = new PassesParser(passFactory);
        String aValidPassOption = PassOptions.PACKAGE.toString();
        PassesDto passesDto = new PassesDto("lol", aValidPassOption, new ArrayList<>());

        subject.parsePassOption(passesDto);

        verify(passFactory).buildPassOption(any());
    }

    // TODO
    /*
    @Test
    void parsePassCategory_shouldThrowInvalidPassCategoryException_whenPassCategoryDoesNotExist() {
        subject = new PassesParser(mock(PassFactory.class));
        PassesDto passesDto = new PassesDto("lol", passOption, eventDates);

        assertThrows(InvalidPassOptionException.class, () -> subject.parsePassOption(passesDto));
    }
    */
}