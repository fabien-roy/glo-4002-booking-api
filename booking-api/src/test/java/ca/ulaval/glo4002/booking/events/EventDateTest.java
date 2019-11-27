package ca.ulaval.glo4002.booking.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventDateTest {

    private EventDate eventDate;

    @Test
    void constructing_shouldThrowInvalidEventDateException_whenEventDateIsUnderBounds() {
        LocalDate aUnderBoundEventDate  = EventDate.getStartEventDate().minusDays(1).getValue();

        assertThrows(InvalidEventDateException.class, () -> new EventDate(aUnderBoundEventDate));
    }

    @Test
    void constructing_shouldThrowInvalidEventDateException_whenEventDateIsOverBounds() {
        LocalDate aOverBoundEventDate  = EventDate.getEndEventDate().plusDays(1).getValue();

        assertThrows(InvalidEventDateException.class, () -> new EventDate(aOverBoundEventDate));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void plusDays_shouldAddCorrectNumberOfDays(int days) {
        LocalDate originalValue = EventDate.getStartEventDate().getValue();
        eventDate = new EventDate(originalValue);
        LocalDate expectedValue = originalValue.plusDays(days);

        EventDate newEventDate = eventDate.plusDays(days);

        assertEquals(expectedValue, newEventDate.getValue());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void minusDays_shouldSubtractCorrectNumberOfDays(int days) {
        LocalDate originalValue = EventDate.getEndEventDate().getValue();
        eventDate = new EventDate(originalValue);
        LocalDate expectedValue = originalValue.minusDays(days);

        EventDate newEventDate = eventDate.minusDays(days);

        assertEquals(expectedValue, newEventDate.getValue());
    }

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotEventDate() {
        eventDate = EventDate.getStartEventDate();
        Object object = new Object();

        boolean result = eventDate.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenEventDateHasSameValue() {
        LocalDate aValue = EventDate.getStartEventDate().getValue();
        eventDate = new EventDate(aValue);
        EventDate other = new EventDate(aValue);

        boolean result = eventDate.equals(other);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenEventDateHasDifferentValue() {
        eventDate = EventDate.getStartEventDate();
        EventDate other = eventDate.plusDays(1);

        boolean result = eventDate.equals(other);

        assertFalse(result);
    }

    @Test
    void hashCode_shouldReturnValueHashCode() {
        LocalDate aValue = EventDate.getStartEventDate().getValue();
        int expectedHashCode = aValue.hashCode();
        eventDate = new EventDate(aValue);

        int hashCode = eventDate.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }

    @Test
    void getFullFestivalEventDates_shouldReturnEventDatesAfterOrEqualToStartDate() {
        EventDate startDate = EventDate.getStartEventDate();

        List<EventDate> fullFestivalEventDate = EventDate.getFullFestivalEventDates();

        assertTrue(fullFestivalEventDate.stream().allMatch(eventDate -> eventDate.equals(startDate) || eventDate.getValue().isAfter(startDate.getValue())));
    }

    @Test
    void getFullFestivalEventDates_shouldReturnEventDatesBeforeOrEqualToEndDate() {
        EventDate endDate = EventDate.getEndEventDate();

        List<EventDate> fullFestivalEventDate = EventDate.getFullFestivalEventDates();

        assertTrue(fullFestivalEventDate.stream().allMatch(eventDate -> eventDate.equals(endDate) || eventDate.getValue().isBefore(endDate.getValue())));
    }

    @Test
    void getFullFestivalEventDates_shouldReturnUniqueEventDates() {
        List<EventDate> fullFestivalEventDate = EventDate.getFullFestivalEventDates();

        assertTrue(fullFestivalEventDate.stream().allMatch(new HashSet<>()::add));
    }
}