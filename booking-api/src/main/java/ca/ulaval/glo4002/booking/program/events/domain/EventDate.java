package ca.ulaval.glo4002.booking.program.events.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventDate {

    private LocalDate value;

    public EventDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
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

    public boolean isBetweenOrEquals(EventDate lowerDate, EventDate higherDate) {
        return !this.isBefore(lowerDate) && !this.isAfter(higherDate);
    }

    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.of(value, LocalTime.MIDNIGHT);
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
}
