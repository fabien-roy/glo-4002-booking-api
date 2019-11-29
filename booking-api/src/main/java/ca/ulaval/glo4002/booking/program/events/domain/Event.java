package ca.ulaval.glo4002.booking.program.events.domain;

import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import ca.ulaval.glo4002.booking.program.artists.domain.BookingArtist;
import ca.ulaval.glo4002.booking.program.activities.domain.Activities;

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

	public void updateProfit(ProfitReport profitReport) {
		bookingArtist.updateProfit(profitReport);
	}
}