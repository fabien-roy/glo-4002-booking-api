package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

public class PassRefactored {

	private long number; // TODO : Use PassNumber
	private EventDate eventDate; // TODO : Use a List of EventDate (will help with departures and arrivals)

	public PassRefactored(EventDate eventDate) {
		this.eventDate = eventDate;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public long getNumber() {
		return number;
	}

	public EventDate getEventDate() {
		return eventDate;
	}
}
