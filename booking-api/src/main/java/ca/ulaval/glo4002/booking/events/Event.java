package ca.ulaval.glo4002.booking.events;

import ca.ulaval.glo4002.booking.artists.BookingArtist;
import ca.ulaval.glo4002.booking.activities.Activities;

public class Event {

    private EventDate eventDate;
    private Activities activity;
    private BookingArtist bookingArtist;

    public Event(EventDate eventDate, Activities activity, BookingArtist bookingArtist) {
        this.eventDate = eventDate;
        this.activity = activity;
        this.bookingArtist = bookingArtist;
    }

    public EventDate getEventDate() {
        return eventDate;
    }

    public Activities getActivity() {
        return activity;
    }

    public BookingArtist getArtist() {
        return bookingArtist;
    }
}
