package ca.ulaval.glo4002.booking.events;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDate {

    // TODO : Change START_DATE and EVENT_DATE to EventDate
    private static final LocalDate START_DATE = LocalDate.of(2050, 7, 17);
    private static final LocalDate END_DATE = LocalDate.of(2050, 7, 24);
    private LocalDate value;

    public EventDate(LocalDate value) {
        validateEventDate(value);

        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    public static List<EventDate> getFullFestivalEventDates() {
        List<EventDate> fullFestivalEventDates = new ArrayList<>();

        for (LocalDate date = START_DATE; date.isBefore(END_DATE); date = date.plusDays(1)) {
            fullFestivalEventDates.add(new EventDate(date));
        }

        return fullFestivalEventDates;
    }

    // TODO : Use EventDate.plusDays where necessary
    public EventDate plusDays(int days) {
        return new EventDate(this.value.plusDays(days));
    }

    // TODO : Use EventDate.minusDays where necessary
    public EventDate minusDays(int days) {
        return new EventDate(this.value.minusDays(days));
    }

    // TODO : Use EventDate.isBefore where necessary
    public boolean isBefore(EventDate eventDate) {
        return this.value.isBefore(eventDate.getValue());
    }

    // TODO : Use EventDate.isAfter where necessary
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

    // TODO : Move EventDate validation in factories, since it is now configurable
    private void validateEventDate(LocalDate value) {
        if (value.isBefore(START_DATE) || value.isAfter(END_DATE)) {
            throw new InvalidEventDateException();
        }
    }

    // TODO : Delete temporary method EventDate.getStartEventDate
    public static EventDate getStartEventDate() {
        return new EventDate(START_DATE);
    }

    // TODO : Delete temporary method EventDate.getEndEventDate
    public static EventDate getEndEventDate() {
        return new EventDate(END_DATE);
    }
}
