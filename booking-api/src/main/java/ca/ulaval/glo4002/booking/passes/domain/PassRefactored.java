package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

import java.util.List;

public class PassRefactored {

	private PassCategory category;
	private PassOptions option;
	private Money price;
	private List<EventDate> eventDates;
	private List<EventDate> arrivalDates;
	private List<EventDate> departureDates;

	public PassRefactored(PassCategory category, PassOptions option, Money price, List<EventDate> eventDates, List<EventDate> arrivalDates, List<EventDate> departureDates) {
		this.category = category;
		this.option = option;
		this.price = price;
		this.eventDates = eventDates;
		this.arrivalDates = arrivalDates;
		this.departureDates = departureDates;
	}

	// TODO : Use correctly updateProfit
	public void updateProfit(ProfitReport profitReport) {
		profitReport.addRevenue(price);
	}
}
