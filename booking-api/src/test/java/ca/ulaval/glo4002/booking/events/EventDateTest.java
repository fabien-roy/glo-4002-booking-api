package ca.ulaval.glo4002.booking.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventDateTest {

    private EventDate eventDate;

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void plusDays_shouldAddCorrectNumberOfDays(int days) {
        LocalDate originalValue = EventDate.getDefaultStartEventDate().getValue();
        eventDate = new EventDate(originalValue);
        LocalDate expectedValue = originalValue.plusDays(days);

        EventDate newEventDate = eventDate.plusDays(days);

        assertEquals(expectedValue, newEventDate.getValue());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void minusDays_shouldSubtractCorrectNumberOfDays(int days) {
        LocalDate originalValue = EventDate.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(originalValue);
        LocalDate expectedValue = originalValue.minusDays(days);

        EventDate newEventDate = eventDate.minusDays(days);

        assertEquals(expectedValue, newEventDate.getValue());
    }

    @Test
    void isBetweenOrEquals_shouldReturnFalse_whenEventDateIsBeforeLowerDate() {
        LocalDate value = EventDate.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(value);
        EventDate lowerDate = eventDate.plusDays(1);
        EventDate higherDate = eventDate.plusDays(2);

        boolean result = eventDate.isBetweenOrEquals(lowerDate, higherDate);

        assertFalse(result);
    }

    @Test
    void isBetweenOrEquals_shouldReturnFalse_whenEventDateIsAfterHigherDate() {
        LocalDate value = EventDate.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(value);
        EventDate lowerDate = eventDate.minusDays(2);
        EventDate higherDate = eventDate.minusDays(1);

        boolean result = eventDate.isBetweenOrEquals(lowerDate, higherDate);

        assertFalse(result);
    }

    @Test
    void isBetweenOrEquals_shouldReturnTrue_whenEventDateEqualsLowerDate() {
        LocalDate value = EventDate.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(value);
        EventDate lowerDate = eventDate;
        EventDate higherDate = eventDate.plusDays(1);

        boolean result = eventDate.isBetweenOrEquals(lowerDate, higherDate);

        assertTrue(result);
    }

    @Test
    void isBetweenOrEquals_shouldReturnTrue_whenEventDateEqualsHigherDate() {
        LocalDate value = EventDate.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(value);
        EventDate lowerDate = eventDate.minusDays(1);
        EventDate higherDate = eventDate;

        boolean result = eventDate.isBetweenOrEquals(lowerDate, higherDate);

        assertTrue(result);
    }

    @Test
    void isBetweenOrEquals_shouldReturnTrue_whenEventDateIsBetweenLowerDateAndHigherDate() {
        LocalDate value = EventDate.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(value);
        EventDate lowerDate = eventDate.minusDays(1);
        EventDate higherDate = eventDate.plusDays(1);

        boolean result = eventDate.isBetweenOrEquals(lowerDate, higherDate);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotEventDate() {
        eventDate = EventDate.getDefaultStartEventDate();
        Object object = new Object();

        boolean result = eventDate.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenEventDateHasSameValue() {
        LocalDate aValue = EventDate.getDefaultStartEventDate().getValue();
        eventDate = new EventDate(aValue);
        EventDate other = new EventDate(aValue);

        boolean result = eventDate.equals(other);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenEventDateHasDifferentValue() {
        eventDate = EventDate.getDefaultStartEventDate();
        EventDate other = eventDate.plusDays(1);

        boolean result = eventDate.equals(other);

        assertFalse(result);
    }

    @Test
    void hashCode_shouldReturnValueHashCode() {
        LocalDate aValue = EventDate.getDefaultStartEventDate().getValue();
        int expectedHashCode = aValue.hashCode();
        eventDate = new EventDate(aValue);

        int hashCode = eventDate.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}