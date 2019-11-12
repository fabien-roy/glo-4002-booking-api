package ca.ulaval.glo4002.booking.domain.artist;

import ca.ulaval.glo4002.booking.domain.events.EventDate;

public class Availability {
	
	private EventDate date;
	
	public Availability(EventDate eventDate) {
		this.date = eventDate;
	}

	public EventDate getDate() {
		return date;
	}
	
	

}
