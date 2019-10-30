package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.exceptions.passes.OutOfBoundsEventDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EventDateTest {

    @Test
    void constructing_shouldThrowOutOfBoundsEventDateException_whenEventDateIsUnderBounds() {
        LocalDate aUnderBoundEventDate  = EventDate.START_DATE.minusDays(1);

        assertThrows(
                OutOfBoundsEventDateException.class,
                () -> new EventDate(aUnderBoundEventDate)
        );
    }

    @Test
    void constructing_shouldThrowOutOfBoundsEventDateException_whenEventDateIsOverBounds() {
        LocalDate aOverBoundEventDate  = EventDate.END_DATE.plusDays(1);

        assertThrows(
                OutOfBoundsEventDateException.class,
                () -> new EventDate(aOverBoundEventDate)
        );
    }
}