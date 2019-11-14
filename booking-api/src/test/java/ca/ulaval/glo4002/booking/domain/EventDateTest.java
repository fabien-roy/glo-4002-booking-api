package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.events.InvalidEventDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

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

    @Test
    void getFullFestivalEventDates_shouldReturnEventDatesAfterOrEqualToStartDate() {
        EventDate startDate = new EventDate(EventDate.START_DATE);

        List<EventDate> fullFestivalEventDate = EventDate.getFullFestivalEventDates();

        assertTrue(fullFestivalEventDate.stream().allMatch(eventDate -> eventDate.equals(startDate) || eventDate.getValue().isAfter(startDate.getValue())));
    }

    @Test
    void getFullFestivalEventDates_shouldReturnEventDatesBeforeOrEqualToEndDate() {
        EventDate endDate = new EventDate(EventDate.END_DATE);

        List<EventDate> fullFestivalEventDate = EventDate.getFullFestivalEventDates();

        assertTrue(fullFestivalEventDate.stream().allMatch(eventDate -> eventDate.equals(endDate) || eventDate.getValue().isBefore(endDate.getValue())));
    }

    @Test
    void getFullFestivalEventDates_shouldReturnUniqueEventDates() {
        List<EventDate> fullFestivalEventDate = EventDate.getFullFestivalEventDates();

        assertTrue(fullFestivalEventDate.stream().allMatch(new HashSet<>()::add));
    }
}