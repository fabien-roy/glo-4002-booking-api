package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

import java.util.List;

public class PassRefactored {

	private long number; // TODO : Use PassNumber
	private List<EventDate> eventDates;

	public PassRefactored(List<EventDate> eventDates) {
		this.eventDates = eventDates;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public long getNumber() {
		return number;
	}

	public List<EventDate> getEventDates() {
		return eventDates;
	}
}
