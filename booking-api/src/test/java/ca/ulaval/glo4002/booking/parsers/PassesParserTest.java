package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassCategory;
import ca.ulaval.glo4002.booking.domain.passes.options.PackagePassOption;
import ca.ulaval.glo4002.booking.domain.passes.options.PassOption;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;
import ca.ulaval.glo4002.booking.dto.PassesDto;
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

class PassesParserTest {

    private PassesParser subject;
    private PassFactory passFactory;

    @BeforeEach
    void setUpSubject() {
        passFactory = mock(PassFactory.class);
        subject = new PassesParser(passFactory);
    }

    @Test
    void parsePasses_shouldParseASinglePass_whenThereIsOnlyOneEventDate() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> aEventDate = new ArrayList<>(Collections.singleton("2050-07-14"));
        PassesDto passesDto = new PassesDto(aPassCategory, aPassOption, aEventDate);

        List<Pass> passes = subject.parsePasses(passesDto);

        assertEquals(1, passes.size());
    }

    @Test
    void parsePasses_shouldParseMultiplePasses_whenThereAreMultipleEventDates() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList("2050-07-14", "2050-07-15"));
        PassesDto passesDto = new PassesDto(aPassCategory, aPassOption, someEventDates);

        List<Pass> passes = subject.parsePasses(passesDto);

        assertEquals(2, passes.size());
    }

    @Test
    void parsePasses_shouldSetSamePassCategoryForEachPass() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList("2050-07-14", "2050-07-15"));
        PassesDto passesDto = new PassesDto(aPassCategory, aPassOption, someEventDates);

        List<Pass> passes = subject.parsePasses(passesDto);

        assertEquals(passes.get(0).getCategory(), passes.get(1).getCategory());
    }

    @Test
    void parsePasses_shouldSetSamePassOptionForEachPass() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList("2050-07-14", "2050-07-15"));
        PassesDto passesDto = new PassesDto(aPassCategory, aPassOption, someEventDates);

        List<Pass> passes = subject.parsePasses(passesDto);

        assertEquals(passes.get(0).getOption(), passes.get(1).getOption());
    }

    @Test
    void parsePasses_shouldThrowDuplicatePassEventDateException_whenEventDateIsDuplicated() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        String aDate = "2050-07-14";
        List<String> someEventDates = new ArrayList<>(Collections.nCopies(2, aDate));
        PassesDto passesDto = new PassesDto(aPassCategory, aPassOption, someEventDates);

        assertThrows(DuplicatePassEventDateException.class, () -> subject.parsePasses(passesDto));
    }

    @Test
    void parsePasses_shouldThrowPackagePassWithEventDateException_whenEventDateIsNotNullAndPassOptionIsPackage() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList("2050-07-14", "2050-07-15"));
        PassesDto passesDto = new PassesDto(aPassCategory, PassOptions.PACKAGE.toString(), someEventDates);
        when(passFactory.buildPassOption(any())).thenReturn(new PackagePassOption(mock(Money.class), mock(PriceCalculationStrategy.class)));

        assertThrows(PackagePassWithEventDateException.class, () -> subject.parsePasses(passesDto));
    }

    @Test
    void parsePasses_shouldThrowSinglePassWithoutEventDateException_whenEventDateIsNullAndPassOptionIsSinglePass() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        PassesDto passesDto = new PassesDto(aPassCategory, PassOptions.SINGLE_PASS.toString(), null);
        when(passFactory.buildPassOption(any())).thenReturn(new PackagePassOption(mock(Money.class), mock(PriceCalculationStrategy.class)));

        assertThrows(SinglePassWithoutEventDateException.class, () -> subject.parsePasses(passesDto));
    }

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