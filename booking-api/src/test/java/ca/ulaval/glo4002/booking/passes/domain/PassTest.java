package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class PassTest {

    private Pass pass;

    @Test
    void getArrivalDate_shouldReturnFirstEventDate() {
        EventDate expectedArrivalDate = FestivalConfiguration.getDefaultStartEventDate();
        EventDate departureDate = expectedArrivalDate.plusDays(1);
        List<EventDate> eventDates = Arrays.asList(expectedArrivalDate, departureDate);

        pass = new Pass(eventDates, PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, mock(Money.class));

        assertEquals(expectedArrivalDate, pass.getArrivalDate());
    }

    @Test
    void getDepartureDate_shouldReturnLastEventDate() {
        EventDate arrivalDate = FestivalConfiguration.getDefaultStartEventDate();
        EventDate expectedDepartureDate = arrivalDate.plusDays(1);
        List<EventDate> eventDates = Arrays.asList(arrivalDate, expectedDepartureDate);

        pass = new Pass(eventDates, PassCategories.SUPERNOVA, PassOptions.SINGLE_PASS, mock(Money.class));

        assertEquals(expectedDepartureDate, pass.getDepartureDate());
    }
}