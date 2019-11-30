package ca.ulaval.glo4002.booking.festival.domain;

import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FestivalConfigurationTest {

    private FestivalConfiguration festivalConfiguration;

    @BeforeEach
    void setUpConfiguration() {
        this.festivalConfiguration = new FestivalConfiguration();
    }

    @Test
    void getStartEventDate_shouldReturnDefaultStartEventDate_whenNoneIsSet() {
        EventDate expectedEventDate = FestivalConfiguration.getDefaultStartEventDate();

        EventDate eventDate = festivalConfiguration.getStartEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getEndEventDate_shouldReturnDefaultEndEventDate_whenNoneIsSet() {
        EventDate expectedEventDate = FestivalConfiguration.getDefaultEndEventDate();

        EventDate eventDate = festivalConfiguration.getEndEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getStartOrderDate_shouldReturnDefaultStartOrderDate_whenNoneIsSet() {
        // TODO
    }

    @Test
    void getEndOrderDate_shouldReturnDefaultEndOrderDate_whenNoneIsSet() {
        // TODO
    }

    @Test
    void setStartEventDate_shouldSetStartEventDate() {
        EventDate expectedEventDate = FestivalConfiguration.getDefaultStartEventDate().plusDays(1);

        festivalConfiguration.setStartEventDate(expectedEventDate);
        EventDate eventDate = festivalConfiguration.getStartEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void setStartEventDate_shouldSetStartOrderDate() {
        // TODO
    }

    @Test
    void setStartEventDate_shouldSetEndOrderDate() {
        // TODO
    }

    @Test
    void setEndEventDate_shouldSetEndEventDate() {
        EventDate expectedEventDate = FestivalConfiguration.getDefaultEndEventDate().minusDays(1);

        festivalConfiguration.setEndEventDate(expectedEventDate);
        EventDate eventDate = festivalConfiguration.getEndEventDate();

        assertEquals(expectedEventDate, eventDate);
    }

    @Test
    void getAllEventDates_shouldReturnEventDatesAfterOrEqualToStartDate() {
        EventDate startDate = FestivalConfiguration.getDefaultStartEventDate();

        List<EventDate> allEventDates = festivalConfiguration.getAllEventDates();

        assertTrue(allEventDates.stream().allMatch(eventDate -> eventDate.equals(startDate) || eventDate.isAfter(startDate)));
    }

    @Test
    void getAllEventDates_shouldReturnEventDatesBeforeOrEqualToEndDate() {
        EventDate endDate = FestivalConfiguration.getDefaultEndEventDate();

        List<EventDate> allEventDates = festivalConfiguration.getAllEventDates();

        assertTrue(allEventDates.stream().allMatch(eventDate -> eventDate.equals(endDate) || eventDate.isBefore(endDate)));
    }

    @Test
    void getAllEventDates_shouldReturnUniqueEventDates() {
        List<EventDate> allEventDates = festivalConfiguration.getAllEventDates();

        assertTrue(allEventDates.stream().allMatch(new HashSet<>()::add));
    }

    // TODO : Outdated?
    @Test
    void getStartOrderDate_shouldReturnStartEventDateMinusMaximumDaysToOrderBeforeStartEventDate() {
        EventDate expectedEventDate = festivalConfiguration.getStartEventDate().minusDays(FestivalConfiguration.MAXIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE);
        OrderDate expectedOrderDate = new OrderDate(LocalDateTime.of(expectedEventDate.getValue(), LocalTime.MIDNIGHT));

        OrderDate orderDate = festivalConfiguration.getStartOrderDate();

        assertEquals(expectedOrderDate, orderDate);
    }

    // TODO : Outdated?
    @Test
    void getEndOrderDate_shouldReturnStartEventDateMinusMinimumDaysToOrderBeforeStartEventDate() {
        EventDate expectedEventDate = festivalConfiguration.getStartEventDate().minusDays(FestivalConfiguration.MINIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE);
        OrderDate expectedOrderDate = new OrderDate(LocalDateTime.of(expectedEventDate.getValue(), LocalTime.MIDNIGHT));

        OrderDate orderDate = festivalConfiguration.getEndOrderDate();

        assertEquals(expectedOrderDate, orderDate);
    }
}