package ca.ulaval.glo4002.booking.domain.events;

import ca.ulaval.glo4002.booking.enums.Activities;

public class Event {

    private EventDate eventDate;
    private Activities activity;

    public Event(EventDate eventDate, Activities activity) {
        this.eventDate = eventDate;
        this.activity = activity;
    }

    public EventDate getEventDate() {
        return eventDate;
    }

    public Activities getActivity() {
        return activity;
    }
}
