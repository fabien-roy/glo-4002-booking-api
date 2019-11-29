package ca.ulaval.glo4002.booking.program.artists.domain;

import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

public class Availability {
	
	private EventDate date;
	
	public Availability(EventDate eventDate) {
		this.date = eventDate;
	}

	public EventDate getDate() {
		return date;
	}

}
