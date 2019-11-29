package ca.ulaval.glo4002.booking.program.events;

import ca.ulaval.glo4002.booking.program.artists.BookingArtist;
import ca.ulaval.glo4002.booking.program.activities.Activities;
import ca.ulaval.glo4002.booking.profits.domain.Profit;

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

	public void updateProfit(Profit profit) {
		bookingArtist.updateProfit(profit);
	}
}
