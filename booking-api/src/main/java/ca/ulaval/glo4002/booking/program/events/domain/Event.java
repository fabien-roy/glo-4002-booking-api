package ca.ulaval.glo4002.booking.program.events.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import ca.ulaval.glo4002.booking.program.activities.domain.Activities;

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

    public Artist getArtist() {
        return artist;
    }

	public void updateProfit(ProfitReport profitReport) {
		artist.updateProfit(profitReport);
	}

    public Money getPrice() {
        return artist.getCost();
    }
}
