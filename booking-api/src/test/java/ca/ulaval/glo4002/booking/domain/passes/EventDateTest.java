package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.exceptions.passes.InvalidEventDateFormatException;
import ca.ulaval.glo4002.booking.exceptions.passes.OutOfBoundsEventDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventDateTest {

    @Test
    public void constructing_shouldSetCorrectValue() {
        LocalDate expectedValue  = EventDate.START_DATE.plusDays(1);

        EventDate subject = new EventDate(expectedValue);

        assertEquals(expectedValue, subject.getValue());
    }

    @Test
    public void constructing_shouldThrowInvalidEventDateException_whenEventDateIsInvalid() {
        String anInvalidEventDate = "anInvalidDate";

        assertThrows(
                InvalidEventDateFormatException.class,
                () -> new EventDate(anInvalidEventDate)
        );
    }

    @Test
    public void constructing_shouldThrowOutOfBoundsEventDateException_whenEventDateIsUnderBounds() {
        LocalDate aUnderBoundEventDate  = EventDate.START_DATE.minusDays(1);

        assertThrows(
                OutOfBoundsEventDateException.class,
                () -> new EventDate(aUnderBoundEventDate)
        );
    }

    @Test
    public void constructing_shouldThrowOutOfBoundsEventDateException_whenEventDateIsOverBounds() {
        LocalDate aOverBoundEventDate  = EventDate.END_DATE.plusDays(1);

        assertThrows(
                OutOfBoundsEventDateException.class,
                () -> new EventDate(aOverBoundEventDate)
        );
    }
}