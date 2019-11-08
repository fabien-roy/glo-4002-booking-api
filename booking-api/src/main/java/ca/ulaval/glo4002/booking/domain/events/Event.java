package ca.ulaval.glo4002.booking.domain.events;

import ca.ulaval.glo4002.booking.domain.BookingArtist;
import ca.ulaval.glo4002.booking.enums.Activities;

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

    public String getArtistName() {
        return bookingArtist.getName();
    }
}
