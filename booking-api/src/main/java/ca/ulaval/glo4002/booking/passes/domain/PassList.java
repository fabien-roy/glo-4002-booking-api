package ca.ulaval.glo4002.booking.passes.domain;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

import java.util.List;

public class PassList {

    List<PassRefactored> passes;
	private PassCategories category;
	private PassOptions option;
	private Money price;
	private List<EventDate> arrivalDates;
	private List<EventDate> departureDates;

	public PassList(List<PassRefactored> passes, PassCategories category, PassOptions option, Money price, List<EventDate> arrivalDates, List<EventDate> departureDates) {
		this.passes = passes;
		this.category = category;
		this.option = option;
		this.price = price;
		this.arrivalDates = arrivalDates;
		this.departureDates = departureDates;
	}

	public List<PassRefactored> getPasses() {
		return passes;
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
