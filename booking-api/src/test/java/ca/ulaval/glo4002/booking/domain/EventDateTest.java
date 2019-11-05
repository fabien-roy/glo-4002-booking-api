package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.exceptions.InvalidEventDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EventDateTest {

    @Test
    void constructing_shouldThrowInvalidEventDateException_whenEventDateIsUnderBounds() {
        LocalDate aUnderBoundEventDate  = EventDate.START_DATE.minusDays(1);

        assertThrows(InvalidEventDateException.class, () -> new EventDate(aUnderBoundEventDate));
    }

    @Test
    void constructing_shouldThrowInvalidEventDateException_whenEventDateIsOverBounds() {
        LocalDate aOverBoundEventDate  = EventDate.END_DATE.plusDays(1);

        assertThrows(InvalidEventDateException.class, () -> new EventDate(aOverBoundEventDate));
    }
}