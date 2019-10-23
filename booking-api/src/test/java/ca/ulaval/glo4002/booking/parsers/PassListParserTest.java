package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.passes.PassCategory;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.domain.passes.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.EventDate;
import ca.ulaval.glo4002.booking.domain.passes.options.PackagePassOption;
import ca.ulaval.glo4002.booking.domain.passes.options.SinglePassOption;
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

    @BeforeEach
    void setUpSubject() {
        passFactory = mock(PassFactory.class);
        subject = new PassListParser(passFactory);
    }

    @Test
    void parsePasses_shouldParseASinglePass_whenThereIsOnlyOneEventDate() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> aEventDate = new ArrayList<>(Collections.singleton(EventDate.START_DATE.toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, aEventDate);

        PassList passList = subject.parsePasses(passListDto);

        assertEquals(1, passList.size());
    }

    @Test
    void parsePasses_shouldParseMultiplePasses_whenThereAreMultipleEventDates() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, someEventDates);

        PassList passList = subject.parsePasses(passListDto);

        assertEquals(2, passList.size());
    }

    @Test
    void parsePasses_shouldSetSamePriceCalculationStrategyForOptionAndCategory() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.PACKAGE.toString();
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, null);
        when(passFactory.buildPassOption(any())).thenReturn(new PackagePassOption(mock(Money.class)));
        when(passFactory.buildPassCategory(any())).thenReturn(new PassCategory());

        PassList passList = subject.parsePasses(passListDto);

        assertEquals(passList.getOption().getPriceCalculationStrategy(), passList.getCategory().getPriceCalculationStrategy());
    }

    @Test
    void parsePasses_shouldThrowDuplicatePassEventDateException_whenEventDateIsDuplicated() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        String aDate = EventDate.START_DATE.toString();
        List<String> someEventDates = new ArrayList<>(Collections.nCopies(2, aDate));
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, someEventDates);

        assertThrows(DuplicatePassEventDateException.class, () -> subject.parsePasses(passListDto));
    }

    @Test
    void parsePasses_shouldThrowPackagePassWithEventDateException_whenEventDateIsNotNullAndPassOptionIsPackage() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, PassOptions.PACKAGE.toString(), someEventDates);
        when(passFactory.buildPassOption(any())).thenReturn(new PackagePassOption(mock(Money.class)));

        assertThrows(PackagePassWithEventDateException.class, () -> subject.parsePasses(passListDto));
    }

    @Test
    void parsePasses_shouldThrowSinglePassWithoutEventDateException_whenEventDateIsNullAndPassOptionIsSinglePass() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        PassListDto passListDto = new PassListDto(aPassCategory, PassOptions.SINGLE_PASS.toString(), null);
        when(passFactory.buildPassOption(any())).thenReturn(new SinglePassOption(mock(Money.class)));

        assertThrows(SinglePassWithoutEventDateException.class, () -> subject.parsePasses(passListDto));
    }

    @Test
    void parsePasses_shouldThrowInvalidPassOptionException_whenPassOptionDoesNotExist() {
        String anInvalidPassOption = "anInvalidPassOption";
        PassListDto passListDto = new PassListDto(PassCategories.SUPERNOVA.toString(), anInvalidPassOption, new ArrayList<>());

        assertThrows(InvalidPassOptionException.class, () -> subject.parsePasses(passListDto));
    }

    @Test
    void parsePasses_shouldBuildPassOption() {
        String aValidPassOption = PassOptions.PACKAGE.toString();
        PassListDto passListDto = new PassListDto(PassCategories.SUPERNOVA.toString(), aValidPassOption, new ArrayList<>());

        subject.parsePasses(passListDto);

        verify(passFactory).buildPassOption(any());
    }

    @Test
    void parsePasses_shouldThrowInvalidPassCategoryException_whenPassCategoryDoesNotExist() {
        String anInvalidPassOption = "anInvalidPassCategory";
        PassListDto passListDto = new PassListDto(anInvalidPassOption, PassOptions.PACKAGE.toString(), new ArrayList<>());

        assertThrows(InvalidPassCategoryException.class, () -> subject.parsePasses(passListDto));
    }

    @Test
    void parsePasses_shouldBuildPassCategory() {
        String aValidPassCategory = PassCategories.SUPERNOVA.toString();
        PassListDto passListDto = new PassListDto(aValidPassCategory, PassOptions.PACKAGE.toString(), new ArrayList<>());

        subject.parsePasses(passListDto);

        verify(passFactory).buildPassCategory(any());
    }
}