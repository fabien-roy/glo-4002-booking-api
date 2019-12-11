package ca.ulaval.glo4002.booking.program.events.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class EventDateTest {

    private EventDate eventDate;

    @Test
    void plusDays_shouldAddCorrectNumberOfDays() {
        int days = 2;
        LocalDate originalValue = FestivalConfiguration.getDefaultStartEventDate().getValue();
        eventDate = new EventDate(originalValue);
        LocalDate expectedValue = originalValue.plusDays(days);

        EventDate newEventDate = eventDate.plusDays(days);

        assertEquals(expectedValue, newEventDate.getValue());
    }

    @Test
    void minusDays_shouldSubtractCorrectNumberOfDays() {
        int days = 2;
        LocalDate originalValue = FestivalConfiguration.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(originalValue);
        LocalDate expectedValue = originalValue.minusDays(days);

        EventDate newEventDate = eventDate.minusDays(days);

        assertEquals(expectedValue, newEventDate.getValue());
    }

    @Test
    void isBetweenOrEquals_shouldReturnFalse_whenEventDateIsBeforeLowerDate() {
        LocalDate value = FestivalConfiguration.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(value);
        EventDate lowerDate = eventDate.plusDays(1);
        EventDate higherDate = eventDate.plusDays(2);

        boolean result = eventDate.isBetweenOrEquals(lowerDate, higherDate);

        assertFalse(result);
    }

    @Test
    void isBetweenOrEquals_shouldReturnFalse_whenEventDateIsAfterHigherDate() {
        LocalDate value = FestivalConfiguration.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(value);
        EventDate lowerDate = eventDate.minusDays(2);
        EventDate higherDate = eventDate.minusDays(1);

        boolean result = eventDate.isBetweenOrEquals(lowerDate, higherDate);

        assertFalse(result);
    }

    @Test
    void isBetweenOrEquals_shouldReturnTrue_whenEventDateEqualsLowerDate() {
        LocalDate value = FestivalConfiguration.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(value);
        EventDate lowerDate = eventDate;
        EventDate higherDate = eventDate.plusDays(1);

        boolean result = eventDate.isBetweenOrEquals(lowerDate, higherDate);

        assertTrue(result);
    }

    @Test
    void isBetweenOrEquals_shouldReturnTrue_whenEventDateEqualsHigherDate() {
        LocalDate value = FestivalConfiguration.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(value);
        EventDate lowerDate = eventDate.minusDays(1);
        EventDate higherDate = eventDate;

        boolean result = eventDate.isBetweenOrEquals(lowerDate, higherDate);

        assertTrue(result);
    }

    @Test
    void isBetweenOrEquals_shouldReturnTrue_whenEventDateIsBetweenLowerDateAndHigherDate() {
        LocalDate value = FestivalConfiguration.getDefaultEndEventDate().getValue();
        eventDate = new EventDate(value);
        EventDate lowerDate = eventDate.minusDays(1);
        EventDate higherDate = eventDate.plusDays(1);

        boolean result = eventDate.isBetweenOrEquals(lowerDate, higherDate);

        assertTrue(result);
    }

    @Test
    void toLocalDateTime_shouldReturnValueAsLocalDateTime() {
        LocalDate value = FestivalConfiguration.getDefaultEndEventDate().getValue();
        LocalDateTime expectedLocalDateTime = LocalDateTime.of(value, LocalTime.MIDNIGHT);
        eventDate = new EventDate(value);

        LocalDateTime localDateTime = eventDate.toLocalDateTime();

        assertEquals(expectedLocalDateTime, localDateTime);
    }

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotEventDate() {
        eventDate = FestivalConfiguration.getDefaultStartEventDate();
        Object object = new Object();

        boolean result = eventDate.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenEventDateHasSameValue() {
        LocalDate aValue = FestivalConfiguration.getDefaultStartEventDate().getValue();
        eventDate = new EventDate(aValue);
        EventDate other = new EventDate(aValue);

        boolean result = eventDate.equals(other);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenEventDateHasDifferentValue() {
        eventDate = FestivalConfiguration.getDefaultStartEventDate();
        EventDate other = eventDate.plusDays(1);

        boolean result = eventDate.equals(other);

        assertFalse(result);
    }
}