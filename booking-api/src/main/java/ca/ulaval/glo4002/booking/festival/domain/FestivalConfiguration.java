package ca.ulaval.glo4002.booking.festival.domain;

import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FestivalConfiguration {

    private static final LocalDate DEFAULT_START_EVENT_DATE_VALUE = LocalDate.of(2050, 7, 17);
    private static final LocalDate DEFAULT_END_EVENT_DATE_VALUE = LocalDate.of(2050, 7, 24);
    private static final LocalDateTime DEFAULT_START_ORDER_DATE_VALUE = LocalDateTime.of(2050, 1, 1, 0, 0, 0);
    private static final LocalDateTime DEFAULT_END_ORDER_DATE_VALUE = LocalDateTime.of(2050, 7, 17, 0, 0, 0);
    static final Integer MAXIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE = 180;
    static final Integer MINIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE = 0;

    private EventDate startEventDate;
    private EventDate endEventDate;
    private OrderDate startOrderDate;
    private OrderDate endOrderDate;

    public FestivalConfiguration() {
        startEventDate = getDefaultStartEventDate();
        endEventDate = getDefaultEndEventDate();
        startOrderDate = getDefaultStartOrderDate();
        endOrderDate = getDefaultEndOrderDate();
    }

    public EventDate getStartEventDate() {
        return startEventDate;
    }

    public void setStartEventDate(EventDate startEventDate) {
        this.startEventDate = startEventDate;

        setStartOrderDate(startEventDate);
        setEndOrderDate(startEventDate);
    }

    public EventDate getEndEventDate() {
        return endEventDate;
    }

    public void setEndEventDate(EventDate endEventDate) {
        this.endEventDate = endEventDate;
    }

    public OrderDate getStartOrderDate() {
        return startOrderDate;
    }

    private void setStartOrderDate(EventDate startEventDate) {
        EventDate startOrderDateAsEventDate = startEventDate.minusDays(MAXIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE);
        LocalDateTime startOrderDateValue = LocalDateTime.of(startOrderDateAsEventDate.getValue(), LocalTime.MIDNIGHT);

        startOrderDate = new OrderDate(startOrderDateValue);
    }

    public OrderDate getEndOrderDate() {
        return endOrderDate;
    }

    private void setEndOrderDate(EventDate startEventDate) {
        EventDate endOrderDateAsEventDate = startEventDate.minusDays(MINIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE);
        LocalDateTime endOrderDateValue = LocalDateTime.of(endOrderDateAsEventDate.getValue(), LocalTime.MIDNIGHT);

        endOrderDate =  new OrderDate(endOrderDateValue);
    }

    public List<EventDate> getAllEventDates() {
        List<EventDate> allEventDates = new ArrayList<>();

        for (EventDate date = startEventDate; date.isBefore(endEventDate); date = date.plusDays(1)) {
            allEventDates.add(date);
        }

        return allEventDates;
    }

    public static EventDate getDefaultStartEventDate() {
        return new EventDate(DEFAULT_START_EVENT_DATE_VALUE);
    }

    public static EventDate getDefaultEndEventDate() {
        return new EventDate(DEFAULT_END_EVENT_DATE_VALUE);
    }

    public static OrderDate getDefaultStartOrderDate() {
        return new OrderDate(DEFAULT_START_ORDER_DATE_VALUE);
    }

    public static OrderDate getDefaultEndOrderDate() {
        return new OrderDate(DEFAULT_END_ORDER_DATE_VALUE);
    }
}
