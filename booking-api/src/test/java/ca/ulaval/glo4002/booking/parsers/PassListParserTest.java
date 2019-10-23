package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.passes.PassCategory;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.domain.passes.EventDate;
import ca.ulaval.glo4002.booking.domain.passes.PassOption;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;
import ca.ulaval.glo4002.booking.dto.PassListDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.passes.*;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PassListParserTest {

    private PassListParser subject;
    private PassFactory passFactory;
    private PassList passList;

    @BeforeEach
    void setUpSubject() {
        passFactory = mock(PassFactory.class);
        passList = new PassList(mock(PassCategory.class), mock(PassOption.class), mock(PriceCalculationStrategy.class));
        when(passFactory.build(any(), any())).thenReturn(passList);

        subject = new PassListParser(passFactory);
    }

    @Test
    void parseDto_shouldBuildAPassList() {
        PassListDto passListDto = new PassListDto(PassCategories.SUPERNOVA.toString(), PassOptions.SINGLE_PASS.toString(), new ArrayList<>());

        subject.parseDto(passListDto);

        verify(passFactory).build(any(), any());
    }

    @Test
    void parseDto_shouldParseASinglePass_whenThereIsOnlyOneEventDate() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> aEventDate = new ArrayList<>(Collections.singleton(EventDate.START_DATE.toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, aEventDate);

        PassList passList = subject.parseDto(passListDto);

        assertEquals(1, passList.size());
    }

    @Test
    void parseDto_shouldParseMultiplePasses_whenThereAreMultipleEventDates() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, someEventDates);

        PassList passList = subject.parseDto(passListDto);

        assertEquals(2, passList.size());
    }

    @Test
    void parseDto_shouldThrowDuplicatePassEventDateException_whenEventDateIsDuplicated() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        String aDate = EventDate.START_DATE.toString();
        List<String> someEventDates = new ArrayList<>(Collections.nCopies(2, aDate));
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, someEventDates);

        assertThrows(DuplicatePassEventDateException.class, () -> subject.parseDto(passListDto));
    }

    @Test
    void parseDto_shouldThrowPackagePassWithEventDateException_whenEventDateIsNotNullAndPassOptionIsPackage() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, PassOptions.PACKAGE.toString(), someEventDates);
        passList.setOption(mock(PassOption.class));

        assertThrows(PackagePassWithEventDateException.class, () -> subject.parseDto(passListDto));
    }

    @Test
    void parseDto_shouldThrowSinglePassWithoutEventDateException_whenEventDateIsNullAndPassOptionIsSinglePass() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        PassListDto passListDto = new PassListDto(aPassCategory, PassOptions.SINGLE_PASS.toString(), null);
        passList.setOption(mock(PassOption.class));

        assertThrows(SinglePassWithoutEventDateException.class, () -> subject.parseDto(passListDto));
    }

    @Test
    void parseDto_shouldThrowInvalidPassCategoryException_whenPassCategoryDoesNotExist() {
        String anInvalidPassOption = "anInvalidPassCategory";
        PassListDto passListDto = new PassListDto(anInvalidPassOption, PassOptions.PACKAGE.toString(), new ArrayList<>());

        assertThrows(InvalidPassCategoryException.class, () -> subject.parseDto(passListDto));
    }

    @Test
    void parseDto_shouldThrowInvalidPassOptionException_whenPassOptionDoesNotExist() {
        String anInvalidPassOption = "anInvalidPassOption";
        PassListDto passListDto = new PassListDto(PassCategories.SUPERNOVA.toString(), anInvalidPassOption, new ArrayList<>());

        assertThrows(InvalidPassOptionException.class, () -> subject.parseDto(passListDto));
    }
}