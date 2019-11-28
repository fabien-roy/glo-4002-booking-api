package ca.ulaval.glo4002.booking.passes.bundles;

import ca.ulaval.glo4002.booking.events.Event;
import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundle;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleMapper;
import ca.ulaval.glo4002.booking.profits.Money;
import ca.ulaval.glo4002.booking.passes.*;
import ca.ulaval.glo4002.booking.orders.OrderFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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
        PassCategory aPassCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, aPassCategory, PassOptions.SINGLE_PASS);

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
        PassCategory aPassCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, aPassCategory, PassOptions.SINGLE_PASS);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertTrue(passDtos.stream().anyMatch(pass -> pass.getPassNumber().equals(aPassNumber.getValue())));
        assertTrue(passDtos.stream().anyMatch(pass -> pass.getPassNumber().equals(anotherPassNumber.getValue())));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectCategory() {
        Number aPassNumber = new Number(1L);
        Pass aPass = new Pass(aPassNumber, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.singletonList(aPass));
        PassCategory passCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, passCategory, PassOptions.SINGLE_PASS);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertEquals(passCategory.getCategory().toString(), passDtos.get(0).getPassCategory());
    }

    @Test
    void toDto_shouldSetSameCategoryForAllPasses() {
        Number aPassNumber = new Number(1L);
        Number anotherPassNumber = new Number(2L);
        Pass aPass = new Pass(aPassNumber, mock(Money.class), mock(EventDate.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassCategory passCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, passCategory, PassOptions.SINGLE_PASS);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertTrue(passDtos.stream().allMatch(pass -> pass.getPassCategory().equals(passCategory.getCategory().toString())));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectOption() {
        Number aPassNumber = new Number(1L);
        Pass aPass = new Pass(aPassNumber, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.singletonList(aPass));
        PassOptions passOption = PassOptions.SINGLE_PASS;
        String expectedPassOptionName = passOption.toString();
        PassCategory aPassCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, aPassCategory, passOption);

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
        PassCategory aPassCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassOptions passOption = PassOptions.SINGLE_PASS;
        String expectedPassOptionName = passOption.toString();
        PassBundle passBundle = new PassBundle(passes, aPassCategory, passOption);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertTrue(passDtos.stream().allMatch(pass -> pass.getPassOption().equals(expectedPassOptionName)));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectEventDates() {
        Number aPassNumber = new Number(1L);
        Number anotherPassNumber = new Number(2L);
        EventDate aEventDate = mock(EventDate.class);
        EventDate anotherEventDate = mock(EventDate.class);
        when(aEventDate.toString()).thenReturn(EventDate.getDefaultStartEventDate().toLocalDateTime().toString());
        when(anotherEventDate.toString()).thenReturn(EventDate.getDefaultEndEventDate().toLocalDateTime().toString());
        Pass aPass = new Pass(aPassNumber, mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(anotherPassNumber, mock(Money.class), anotherEventDate);
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassCategory aPassCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, aPassCategory, PassOptions.SINGLE_PASS);

        List<PassDto> passDtos = mapper.toDto(passBundle);

        assertTrue(passDtos.stream().anyMatch(pass -> aEventDate.toString().equals(pass.getEventDate())));
        assertTrue(passDtos.stream().anyMatch(pass -> anotherEventDate.toString().equals(pass.getEventDate())));
    }
}