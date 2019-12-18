package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

import java.util.List;

public class PassList {

	// TODO : Have a list of PassRefactored
	private long number; // TODO : Use PassNumber
	private PassCategories category;
	private PassOptions option;
	private Money price;
	private List<EventDate> eventDates;
	private List<EventDate> arrivalDates;
	private List<EventDate> departureDates;

	public PassList(PassCategories category, PassOptions option, Money price, List<EventDate> eventDates, List<EventDate> arrivalDates, List<EventDate> departureDates) {
		this.category = category;
		this.option = option;
		this.price = price;
		this.eventDates = eventDates;
		this.arrivalDates = arrivalDates;
		this.departureDates = departureDates;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public long getNumber() {
		return number;
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

	public List<EventDate> getEventDates() {
		return eventDates;
	}

	public List<EventDate> getArrivalDates() {
		return arrivalDates;
	}

	public List<EventDate> getDepartureDates() {
		return departureDates;
	}

	// TODO : Use correctly updateProfit
	public void updateProfit(ProfitReport profitReport) {
		profitReport.addRevenue(price);
	}
}
