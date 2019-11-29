package ca.ulaval.glo4002.booking.program.artists;

import ca.ulaval.glo4002.booking.program.events.EventDate;

public class Availability {
	
	private EventDate date;
	
	public Availability(EventDate eventDate) {
		this.date = eventDate;
	}

	public EventDate getDate() {
		return date;
	}

}
