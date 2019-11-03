package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.passes.*;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;
import ca.ulaval.glo4002.booking.dto.PassListDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassListFactoryTest {

    private PassListFactory subject;
    private PassFactory passFactory;
    private PassList passList;

    @BeforeEach
    void setUpSubject() {
        NumberGenerator numberGenerator = new NumberGenerator();
        passFactory = mock(PassFactory.class);
        passList = new PassList(mock(PassCategory.class), mock(PassOption.class), mock(PriceCalculationStrategy.class));
        when(passFactory.build(any(), any())).thenReturn(passList);

        subject = new PassListFactory(numberGenerator, passFactory);
    }

    @Test
    void buildWithDto_shouldBuildAPassList() {
        PassListDto passListDto = new PassListDto(PassCategories.SUPERNOVA.toString(), PassOptions.SINGLE_PASS.toString(), new ArrayList<>());

        subject.buildWithDto(passListDto);

        verify(passFactory).build(any(), any());
    }

    @Test
    void buildWithDto_shouldParseASinglePass_whenThereIsOnlyOneEventDate() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> aEventDate = new ArrayList<>(Collections.singleton(EventDate.START_DATE.toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, aEventDate);

        PassList passList = subject.buildWithDto(passListDto);

        assertEquals(1, passList.size());
    }

    @Test
    void buildWithDto_shouldParseMultiplePasses_whenThereAreMultipleEventDates() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        String aPassOption = PassOptions.SINGLE_PASS.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, aPassOption, someEventDates);

        PassList passList = subject.buildWithDto(passListDto);

        assertEquals(2, passList.size());
    }

    @Test
    void buildWithDto_shouldThrowInvalidFormatException_whenEventDateIsNotNullAndPassOptionIsPackage() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        List<String> someEventDates = new ArrayList<>(Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString()));
        PassListDto passListDto = new PassListDto(aPassCategory, PassOptions.PACKAGE.toString(), someEventDates);
        passList.setOption(mock(PassOption.class));

        assertThrows(InvalidFormatException.class, () -> subject.buildWithDto(passListDto));
    }

    @Test
    void buildWithDto_shouldThrowSinglePassWithoutEventDateException_whenEventDateIsNullAndPassOptionIsSinglePass() {
        String aPassCategory = PassCategories.SUPERNOVA.toString();
        PassListDto passListDto = new PassListDto(aPassCategory, PassOptions.SINGLE_PASS.toString(), null);
        passList.setOption(mock(PassOption.class));

        assertThrows(InvalidFormatException.class, () -> subject.buildWithDto(passListDto));
    }

    @Test
    void buildWithDto_shouldThrowInvalidFormatException_whenPassCategoryDoesNotExist() {
        String anInvalidPassOption = "anInvalidPassCategory";
        PassListDto passListDto = new PassListDto(anInvalidPassOption, PassOptions.PACKAGE.toString(), new ArrayList<>());

        assertThrows(InvalidFormatException.class, () -> subject.buildWithDto(passListDto));
    }

    @Test
    void buildWithDto_shouldThrowInvalidFormatException_whenPassOptionDoesNotExist() {
        String anInvalidPassOption = "anInvalidPassOption";
        PassListDto passListDto = new PassListDto(PassCategories.SUPERNOVA.toString(), anInvalidPassOption, new ArrayList<>());

        assertThrows(InvalidFormatException.class, () -> subject.buildWithDto(passListDto));
    }

    @Test
    void buildWithDto_shouldThrowInvalidFormatException_whenEventDateIsInvalid() {
        String anInvalidEventDate = "anInvalidDate";
        PassListDto passListDto = new PassListDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList(anInvalidEventDate)
        );

        assertThrows(InvalidFormatException.class, () -> subject.buildWithDto(passListDto));
    }
}