package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

import java.util.List;

public class PassRefactored {

	private long number; // TODO : Use PassNumber
	private List<EventDate> eventDates;
	private PassCategories category;
	private PassOptions option;
	private Money price;

	public PassRefactored(List<EventDate> eventDates, PassCategories category, PassOptions option, Money price) {
		this.eventDates = eventDates;
		this.category = category;
		this.option = option;
		this.price = price;
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

	public PassCategories getCategory() {
		return category;
	}

	public PassOptions getOption() {
		return option;
	}

	public Money getPrice() {
		return price;
	}

	public EventDate getArrivalDate() {
		return eventDates.get(0);
	}

	public EventDate getDepartureDate() {
	    return eventDates.get(eventDates.size() - 1);
	}
}
