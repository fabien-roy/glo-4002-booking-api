package ca.ulaval.glo4002.booking.events;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDate {

    private static final LocalDate DEFAULT_START_DATE_VALUE = LocalDate.of(2050, 7, 17);
    private static final LocalDate DEFAULT_END_DATE_VALUE = LocalDate.of(2050, 7, 24);
    private LocalDate value;

    public EventDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    // TODO : Delete since used in Configuration
    public static List<EventDate> getFullFestivalEventDates() {
        List<EventDate> fullFestivalEventDates = new ArrayList<>();

        for (LocalDate date = DEFAULT_START_DATE_VALUE; date.isBefore(DEFAULT_END_DATE_VALUE); date = date.plusDays(1)) {
            fullFestivalEventDates.add(new EventDate(date));
        }

        return fullFestivalEventDates;
    }

    public EventDate plusDays(int days) {
        return new EventDate(this.value.plusDays(days));
    }

    public EventDate minusDays(int days) {
        return new EventDate(this.value.minusDays(days));
    }

    public boolean isBefore(EventDate eventDate) {
        return this.value.isBefore(eventDate.getValue());
    }

    public boolean isAfter(EventDate eventDate) {
        return this.value.isAfter(eventDate.getValue());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EventDate)) return false;

        EventDate otherEventDate = (EventDate) other;

        return this.value.equals(otherEventDate.getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public static EventDate getDefaultStartEventDate() {
        return new EventDate(DEFAULT_START_DATE_VALUE);
    }

    public static EventDate getDefaultEndEventDate() {
        return new EventDate(DEFAULT_END_DATE_VALUE);
    }
}
