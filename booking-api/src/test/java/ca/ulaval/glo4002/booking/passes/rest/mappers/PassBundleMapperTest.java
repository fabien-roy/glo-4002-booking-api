package ca.ulaval.glo4002.booking.passes.rest.mappers;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.passes.domain.*;
import ca.ulaval.glo4002.booking.passes.rest.PassResponse;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void toResponse_shouldBuildCorrectQuantityOfResponses(int expectedSize) {
        Pass aPass = new Pass(1L, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.nCopies(expectedSize, aPass));
        PassCategory aPassCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, aPassCategory, PassOptions.SINGLE_PASS);

        List<PassResponse> passResponses = mapper.toResponse(passBundle);

        assertEquals(expectedSize, passResponses.size());
    }

    @Test
    void toResponse_shouldBuildResponseWithCorrectPassNumbers() {
        Long aPassNumber = 1L;
        Long anotherPassNumber = 2L;
        Pass aPass = new Pass(aPassNumber, mock(Money.class), mock(EventDate.class));
        Pass anotherPass = new Pass(anotherPassNumber, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassCategory aPassCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, aPassCategory, PassOptions.SINGLE_PASS);

        List<PassResponse> passResponses = mapper.toResponse(passBundle);

        assertTrue(passResponses.stream().anyMatch(pass -> aPassNumber.equals(pass.getPassNumber())));
        assertTrue(passResponses.stream().anyMatch(pass -> anotherPassNumber.equals(pass.getPassNumber())));
    }

    @Test
    void toResponse_shouldBuildResponseWithCorrectCategory() {
        Pass aPass = new Pass(1L, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.singletonList(aPass));
        PassCategory passCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, passCategory, PassOptions.SINGLE_PASS);

        List<PassResponse> passResponses = mapper.toResponse(passBundle);

        assertEquals(passCategory.getCategory().toString(), passResponses.get(0).getPassCategory());
    }

    @Test
    void toResponse_shouldSetSameCategoryForAllPasses() {
        Pass aPass = new Pass(1L, mock(Money.class), mock(EventDate.class));
        Pass anotherPass = new Pass(2L, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassCategory passCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, passCategory, PassOptions.SINGLE_PASS);

        List<PassResponse> passResponses = mapper.toResponse(passBundle);

        assertTrue(passResponses.stream().allMatch(pass -> pass.getPassCategory().equals(passCategory.getCategory().toString())));
    }

    @Test
    void toResponse_shouldBuildResponseWithCorrectOption() {
        Pass aPass = new Pass(1L, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Collections.singletonList(aPass));
        PassOptions passOption = PassOptions.SINGLE_PASS;
        String expectedPassOptionName = passOption.toString();
        PassCategory aPassCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, aPassCategory, passOption);

        List<PassResponse> passResponses = mapper.toResponse(passBundle);

        assertEquals(expectedPassOptionName, passResponses.get(0).getPassOption());
    }

    @Test
    void toResponse_shouldSetSameOptionForAllPasses() {
        Pass aPass = new Pass(1L, mock(Money.class), mock(EventDate.class));
        Pass anotherPass = new Pass(2L, mock(Money.class), mock(EventDate.class));
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassCategory aPassCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassOptions passOption = PassOptions.SINGLE_PASS;
        String expectedPassOptionName = passOption.toString();
        PassBundle passBundle = new PassBundle(passes, aPassCategory, passOption);

        List<PassResponse> passResponses = mapper.toResponse(passBundle);

        assertTrue(passResponses.stream().allMatch(pass -> pass.getPassOption().equals(expectedPassOptionName)));
    }

    @Test
    void toResponse_shouldBuildResponseWithCorrectEventDates() {
        EventDate aEventDate = mock(EventDate.class);
        EventDate anotherEventDate = mock(EventDate.class);
        when(aEventDate.toString()).thenReturn(FestivalConfiguration.getDefaultStartEventDate().toLocalDateTime().toString());
        when(anotherEventDate.toString()).thenReturn(FestivalConfiguration.getDefaultEndEventDate().toLocalDateTime().toString());
        Pass aPass = new Pass(1L, mock(Money.class), aEventDate);
        Pass anotherPass = new Pass(2L, mock(Money.class), anotherEventDate);
        List<Pass> passes = new ArrayList<>(Arrays.asList(aPass, anotherPass));
        PassCategory aPassCategory = new PassCategory(PassCategories.SUPERNOVA, new HashMap<>());
        PassBundle passBundle = new PassBundle(passes, aPassCategory, PassOptions.SINGLE_PASS);

        List<PassResponse> passResponses = mapper.toResponse(passBundle);

        assertTrue(passResponses.stream().anyMatch(pass -> aEventDate.toString().equals(pass.getEventDate())));
        assertTrue(passResponses.stream().anyMatch(pass -> anotherEventDate.toString().equals(pass.getEventDate())));
    }
}