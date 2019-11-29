package ca.ulaval.glo4002.booking.festival;

import ca.ulaval.glo4002.booking.program.events.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FestivalTest {

    private Festival festival;

    @BeforeEach
    void setUpConfiguration() {
        this.festival = new Festival();
    }

    @Test
    void getStartEventDate_shouldReturnDefaultStartEventDate_whenNoneIsSet() {
        EventDate expectedEventDate = EventDate.getDefaultStartEventDate();

        EventDate eventDate = festival.getStartEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getEndEventDate_shouldReturnDefaultEndEventDate_whenNoneIsSet() {
        EventDate expectedEventDate = EventDate.getDefaultEndEventDate();

        EventDate eventDate = festival.getEndEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void setStartEventDate_shouldSetStartEventDate() {
        EventDate expectedEventDate = EventDate.getDefaultStartEventDate().plusDays(1);

        festival.setStartEventDate(expectedEventDate);
        EventDate eventDate = festival.getStartEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void setEndEventDate_shouldSetEndEventDate() {
        EventDate expectedEventDate = EventDate.getDefaultEndEventDate().minusDays(1);

        festival.setEndEventDate(expectedEventDate);
        EventDate eventDate = festival.getEndEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getAllEventDates_shouldReturnEventDatesAfterOrEqualToStartDate() {
        EventDate startDate = EventDate.getDefaultStartEventDate();

        List<EventDate> allEventDates = festival.getAllEventDates();

        assertTrue(allEventDates.stream().allMatch(eventDate -> eventDate.equals(startDate) || eventDate.isAfter(startDate)));
    }

    @Test
    void getAllEventDates_shouldReturnEventDatesBeforeOrEqualToEndDate() {
        EventDate endDate = EventDate.getDefaultEndEventDate();

        List<EventDate> allEventDates = festival.getAllEventDates();

        assertTrue(allEventDates.stream().allMatch(eventDate -> eventDate.equals(endDate) || eventDate.isBefore(endDate)));
    }

    @Test
    void getAllEventDates_shouldReturnUniqueEventDates() {
        List<EventDate> allEventDates = festival.getAllEventDates();

        assertTrue(allEventDates.stream().allMatch(new HashSet<>()::add));
    }

    @Test
    void getMinimumEventDateToOrder_shouldReturnStartEventDateMinusMaximumDaysToOrderBeforeStartEventDate() {
        EventDate expectedEventDate = festival.getStartEventDate().minusDays(Festival.MAXIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE);

        EventDate eventDate = festival.getMinimumEventDateToOrder();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getMaximumEventDateToOrder_shouldReturnStartEventDateMinusMinimumDaysToOrderBeforeStartEventDate() {
        EventDate expectedEventDate = festival.getStartEventDate().minusDays(Festival.MINIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE);

        EventDate eventDate = festival.getMaximumEventDateToOrder();

        assertEquals(expectedEventDate, eventDate);
    }
}