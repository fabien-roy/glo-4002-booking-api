package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.*;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.NoDiscountPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;
import ca.ulaval.glo4002.booking.dto.PassDto;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PassListMapperTest {

    private PassListMapper subject;

    @BeforeEach
    void setUpSubject() {
        subject = new PassListMapper();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void toDto_shouldBuildCorrectQuantityOfDtos(int expectedSize) {
        Number aPassNumber = new Number(1L);
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class), mock(Money.class));
        List<Pass> passes = new ArrayList<>(Collections.nCopies(expectedSize, aPass));
        PassList passList = new PassList(passes, mock(PassCategory.class), mock(PassOption.class));

        List<PassDto> passDtos = subject.toDto(passList);

        assertEquals(expectedSize, passDtos.size());
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectPassNumbers() {
        Number aPassNumber = new Number(1L);
        Number anotherPassNumber = new Number(2L);
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class), mock(Money.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(EventDate.class), mock(Money.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassList passList = new PassList(passes, mock(PassCategory.class), mock(PassOption.class));

        List<PassDto> passDtos = subject.toDto(passList);

        assertTrue(passDtos.stream().anyMatch(pass -> pass.getPassNumber().equals(aPassNumber.getValue())));
        assertTrue(passDtos.stream().anyMatch(pass -> pass.getPassNumber().equals(anotherPassNumber.getValue())));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectCategory() {
        Number aPassNumber = new Number(1L);
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class), mock(Money.class));
        List<Pass> passes = new ArrayList<>(Collections.singletonList(aPass));
        PassCategory passCategory = mock(PassCategory.class);
        String expectedPassCategoryName = "expectedPassCategoryName";
        when(passCategory.getName()).thenReturn(expectedPassCategoryName);
        PassList passList = new PassList(passes, passCategory, mock(PassOption.class));

        List<PassDto> passDtos = subject.toDto(passList);

        assertEquals(expectedPassCategoryName, passDtos.get(0).getPassCategory());
    }

    @Test
    void toDto_shouldSetSameCategoryForAllPasses() {
        Number aPassNumber = new Number(1L);
        Number anotherPassNumber = new Number(2L);
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class), mock(Money.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(EventDate.class), mock(Money.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassCategory passCategory = mock(PassCategory.class);
        String expectedPassCategoryName = "expectedPassCategoryName";
        when(passCategory.getName()).thenReturn(expectedPassCategoryName);
        PassList passList = new PassList(passes, passCategory, mock(PassOption.class));

        List<PassDto> passDtos = subject.toDto(passList);

        assertTrue(passDtos.stream().allMatch(pass -> pass.getPassCategory().equals(expectedPassCategoryName)));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectOption() {
        Number aPassNumber = new Number(1L);
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class), mock(Money.class));
        List<Pass> passes = new ArrayList<>(Collections.singletonList(aPass));
        PassOption passOption = mock(PassOption.class);
        String expectedPassOptionName = "expectedPassOptionName";
        when(passOption.getName()).thenReturn(expectedPassOptionName);
        PassList passList = new PassList(passes, mock(PassCategory.class), passOption);

        List<PassDto> passDtos = subject.toDto(passList);

        assertEquals(expectedPassOptionName, passDtos.get(0).getPassOption());
    }

    @Test
    void toDto_shouldSetSameOptionForAllPasses() {
        Number aPassNumber = new Number(1L);
        Number anotherPassNumber = new Number(2L);
        Pass aPass = new Pass(aPassNumber, mock(EventDate.class), mock(Money.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(EventDate.class), mock(Money.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassOption passOption = mock(PassOption.class);
        String expectedPassOptionName = "expectedPassOptionName";
        when(passOption.getName()).thenReturn(expectedPassOptionName);
        PassList passList = new PassList(passes, mock(PassCategory.class), passOption);

        List<PassDto> passDtos = subject.toDto(passList);

        assertTrue(passDtos.stream().allMatch(pass -> pass.getPassOption().equals(expectedPassOptionName)));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectEventDates() {
        Number aPassNumber = new Number(1L);
        Number anotherPassNumber = new Number(2L);
        EventDate aEventDate = mock(EventDate.class);
        EventDate anotherEventDate = mock(EventDate.class);
        when(aEventDate.toString()).thenReturn(OrderFactory.START_DATE_TIME.toString());
        when(anotherEventDate.toString()).thenReturn(OrderFactory.END_DATE_TIME.toString());
        Pass aPass = new Pass(aPassNumber, aEventDate, mock(Money.class));
        Pass anotherPass = new Pass(anotherPassNumber, anotherEventDate, mock(Money.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassList passList = new PassList(passes, mock(PassCategory.class), mock(PassOption.class));

        List<PassDto> passDtos = subject.toDto(passList);

        assertTrue(passDtos.stream().anyMatch(pass -> aEventDate.toString().equals(pass.getEventDate())));
        assertTrue(passDtos.stream().anyMatch(pass -> anotherEventDate.toString().equals(pass.getEventDate())));
    }

    // TODO : Add tests for money
}