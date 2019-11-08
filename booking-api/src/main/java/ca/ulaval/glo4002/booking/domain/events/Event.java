package ca.ulaval.glo4002.booking.domain.events;

import ca.ulaval.glo4002.booking.domain.Artist;
import ca.ulaval.glo4002.booking.enums.Activities;

public class Event {

    private EventDate eventDate;
    private Activities activity;
    private Artist artist;

    public Event(EventDate eventDate, Activities activity, Artist artist) {
        this.eventDate = eventDate;
        this.activity = activity;
        this.artist = artist;
    }

    public EventDate getEventDate() {
        return eventDate;
    }

    public Activities getActivity() {
        return activity;
    }

    public String getArtistName() {
        return artist.getName();
    }
}
