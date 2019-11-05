package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.exceptions.InvalidEventDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDateTest {

    private EventDate eventDate;

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

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotEventDate() {
        eventDate = new EventDate(EventDate.START_DATE);
        Object object = new Object();

        boolean result = eventDate.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenEventDateHasSameValue() {
        LocalDate aValue = EventDate.START_DATE;
        eventDate = new EventDate(aValue);
        EventDate other = new EventDate(aValue);

        boolean result = eventDate.equals(other);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenEventDateHasDifferentValue() {
        eventDate = new EventDate(EventDate.START_DATE);
        EventDate other = new EventDate(EventDate.START_DATE.plusDays(1));

        boolean result = eventDate.equals(other);

        assertFalse(result);
    }

    @Test
    void hashCode_shouldReturnValueHashCode() {
        LocalDate aValue = EventDate.START_DATE;
        int expectedHashCode = aValue.hashCode();
        eventDate = new EventDate(aValue);

        int hashCode = eventDate.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}