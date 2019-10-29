package ca.ulaval.glo4002.booking.domain.trip;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.enums.PassCategories;

public class Passenger {
	
	private Number passNumber;
	private PassCategories passCategory;
	
	public Passenger(Number passNumber, PassCategories passCategory) {
		this.passNumber = passNumber;
		this.passCategory = passCategory;
	}
	
	public Number getPassNumber() {
		return passNumber;
	}
	
	public PassCategories getPassCategory() {
		return passCategory;
	}
}
