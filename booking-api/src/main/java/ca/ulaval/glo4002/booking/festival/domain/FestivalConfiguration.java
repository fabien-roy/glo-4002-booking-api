package ca.ulaval.glo4002.booking.festival.domain;

import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FestivalConfiguration {

    private static final LocalDate DEFAULT_START_DATE_VALUE = LocalDate.of(2050, 7, 17);
    private static final LocalDate DEFAULT_END_DATE_VALUE = LocalDate.of(2050, 7, 24);
    static final Integer MAXIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE = 180;
    static final Integer MINIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE = 1;

    private EventDate startEventDate;
    private EventDate endEventDate;

    public FestivalConfiguration() {
        startEventDate = getDefaultStartEventDate();
        endEventDate = getDefaultEndEventDate();
    }

    public EventDate getStartEventDate() {
        return startEventDate;
    }

    public void setStartEventDate(EventDate startEventDate) {
        this.startEventDate = startEventDate;
    }

    public EventDate getEndEventDate() {
        return endEventDate;
    }

    public void setEndEventDate(EventDate endEventDate) {
        this.endEventDate = endEventDate;
    }

    public List<EventDate> getAllEventDates() {
        List<EventDate> allEventDates = new ArrayList<>();

        for (EventDate date = startEventDate; date.isBefore(endEventDate); date = date.plusDays(1)) {
            allEventDates.add(date);
        }

        return allEventDates;
    }

    public EventDate getMinimumEventDateToOrder() {
        return startEventDate.minusDays(MAXIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE);
    }

    public EventDate getMaximumEventDateToOrder() {
        return startEventDate.minusDays(MINIMUM_DAYS_TO_ORDER_BEFORE_START_EVENT_DATE);
    }

    public static EventDate getDefaultStartEventDate() {
        return new EventDate(DEFAULT_START_DATE_VALUE);
    }

    public static EventDate getDefaultEndEventDate() {
        return new EventDate(DEFAULT_END_DATE_VALUE);
    }
}
