package ca.ulaval.glo4002.booking.domain.events;

public class Event {

    private EventDate eventDate;

    public Event(EventDate eventDate) {
        this.eventDate = eventDate;
    }

    public EventDate getEventDate() {
        return eventDate;
    }
}
