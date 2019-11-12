package ca.ulaval.glo4002.booking.domain.artist;

import ca.ulaval.glo4002.booking.domain.events.EventDate;

public class Availability {
	
	private BookingArtist artist;
	private EventDate date;
	
	public Availability(BookingArtist artist, EventDate date) {
		this.artist = artist;
		this.date = date;
	}

	public EventDate getDate() {
		return date;
	}
	
	

}
