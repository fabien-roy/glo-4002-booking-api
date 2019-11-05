package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.*;
import ca.ulaval.glo4002.booking.dto.PassDto;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PassBundleMapperTest {

    private PassBundleMapper mapper;

    @BeforeEach
    void setUpMapper() {
        mapper = new PassBundleMapper();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void toDto_shouldBuildCorrectQuantityOfDtos(int expectedSize) {
        Number aPassNumber = new Number(1L);
        Pass aPass = new Pass(aPassNumber, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.nCopies(expectedSize, aPass));
        PassBundle passBundle = new PassBundle(passes, mock(PassCategory.class), PassOptions.SINGLE_PASS);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertEquals(expectedSize, passDtos.size());
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectPassNumbers() {
        Number aPassNumber = new Number(1L);
        Number anotherPassNumber = new Number(2L);
        Pass aPass = new Pass(aPassNumber, mock(Money.class), mock(EventDate.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassBundle passBundle = new PassBundle(passes, mock(PassCategory.class), PassOptions.SINGLE_PASS);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertTrue(passDtos.stream().anyMatch(pass -> pass.getPassNumber().equals(aPassNumber.getValue())));
        assertTrue(passDtos.stream().anyMatch(pass -> pass.getPassNumber().equals(anotherPassNumber.getValue())));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectCategory() {
        Number aPassNumber = new Number(1L);
        Pass aPass = new Pass(aPassNumber, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.singletonList(aPass));
        PassCategory passCategory = mock(PassCategory.class);
        String expectedPassCategoryName = "expectedPassCategoryName";
        when(passCategory.getName()).thenReturn(expectedPassCategoryName);
        PassBundle passBundle = new PassBundle(passes, passCategory, PassOptions.SINGLE_PASS);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertEquals(expectedPassCategoryName, passDtos.get(0).getPassCategory());
    }

    @Test
    void toDto_shouldSetSameCategoryForAllPasses() {
        Number aPassNumber = new Number(1L);
        Number anotherPassNumber = new Number(2L);
        Pass aPass = new Pass(aPassNumber, mock(Money.class), mock(EventDate.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassCategory passCategory = mock(PassCategory.class);
        String expectedPassCategoryName = "expectedPassCategoryName";
        when(passCategory.getName()).thenReturn(expectedPassCategoryName);
        PassBundle passBundle = new PassBundle(passes, passCategory, PassOptions.SINGLE_PASS);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertTrue(passDtos.stream().allMatch(pass -> pass.getPassCategory().equals(expectedPassCategoryName)));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectOption() {
        Number aPassNumber = new Number(1L);
        Pass aPass = new Pass(aPassNumber, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.singletonList(aPass));
        PassOptions passOption = PassOptions.SINGLE_PASS;
        String expectedPassOptionName = passOption.toString();
        PassBundle passBundle = new PassBundle(passes, mock(PassCategory.class), passOption);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertEquals(expectedPassOptionName, passDtos.get(0).getPassOption());
    }

    @Test
    void toDto_shouldSetSameOptionForAllPasses() {
        Number aPassNumber = new Number(1L);
        Number anotherPassNumber = new Number(2L);
        Pass aPass = new Pass(aPassNumber, mock(Money.class), mock(EventDate.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassOptions passOption = PassOptions.SINGLE_PASS;
        String expectedPassOptionName = passOption.toString();
        PassBundle passBundle = new PassBundle(passes, mock(PassCategory.class), passOption);

        List<PassDto> passDtos = mapper.toDto(passBundle);

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
        Pass aPass = new Pass(aPassNumber, mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(anotherPassNumber, mock(Money.class), anotherEventDate);
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassBundle passBundle = new PassBundle(passes, mock(PassCategory.class), PassOptions.SINGLE_PASS);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertTrue(passDtos.stream().anyMatch(pass -> aEventDate.toString().equals(pass.getEventDate())));
        assertTrue(passDtos.stream().anyMatch(pass -> anotherEventDate.toString().equals(pass.getEventDate())));
    }
}