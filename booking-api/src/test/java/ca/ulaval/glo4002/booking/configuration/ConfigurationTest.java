package ca.ulaval.glo4002.booking.configuration;

import ca.ulaval.glo4002.booking.program.events.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    private Configuration configuration;

    @BeforeEach
    void setUpConfiguration() {
        this.configuration = new Configuration();
    }

    @Test
    void getStartEventDate_shouldReturnDefaultStartEventDate_whenNoneIsSet() {
        EventDate expectedEventDate = EventDate.getDefaultStartEventDate();

        EventDate eventDate = configuration.getStartEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getEndEventDate_shouldReturnDefaultEndEventDate_whenNoneIsSet() {
        EventDate expectedEventDate = EventDate.getDefaultEndEventDate();

        EventDate eventDate = configuration.getEndEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void setStartEventDate_shouldSetStartEventDate() {
        EventDate expectedEventDate = EventDate.getDefaultStartEventDate().plusDays(1);

        configuration.setStartEventDate(expectedEventDate);
        EventDate eventDate = configuration.getStartEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void setEndEventDate_shouldSetEndEventDate() {
        EventDate expectedEventDate = EventDate.getDefaultEndEventDate().minusDays(1);

        configuration.setEndEventDate(expectedEventDate);
        EventDate eventDate = configuration.getEndEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getAllEventDates_shouldReturnEventDatesAfterOrEqualToStartDate() {
        EventDate startDate = EventDate.getDefaultStartEventDate();

        List<EventDate> allEventDates = configuration.getAllEventDates();

        assertTrue(allEventDates.stream().allMatch(eventDate -> eventDate.equals(startDate) || eventDate.isAfter(startDate)));
    }

    @Test
    void getAllEventDates_shouldReturnEventDatesBeforeOrEqualToEndDate() {
        EventDate endDate = EventDate.getDefaultEndEventDate();

        List<EventDate> allEventDates = configuration.getAllEventDates();

        assertTrue(allEventDates.stream().allMatch(eventDate -> eventDate.equals(endDate) || eventDate.isBefore(endDate)));
    }

    @Test
    void getAllEventDates_shouldReturnUniqueEventDates() {
        List<EventDate> allEventDates = configuration.getAllEventDates();

        assertTrue(allEventDates.stream().allMatch(new HashSet<>()::add));
    }

    @Test
    void getMinimumEventDateToOrder_shouldReturnStartEventDateMinusMaximumDaysToOrderBeforeStartEventDate() {
        EventDate expectedEventDate = configuration.getStartEventDate().minusDays(Configuration.MAXIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE);

        EventDate eventDate = configuration.getMinimumEventDateToOrder();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getMaximumEventDateToOrder_shouldReturnStartEventDateMinusMinimumDaysToOrderBeforeStartEventDate() {
        EventDate expectedEventDate = configuration.getStartEventDate().minusDays(Configuration.MINIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE);

        EventDate eventDate = configuration.getMaximumEventDateToOrder();

        assertEquals(expectedEventDate, eventDate);
    }
}