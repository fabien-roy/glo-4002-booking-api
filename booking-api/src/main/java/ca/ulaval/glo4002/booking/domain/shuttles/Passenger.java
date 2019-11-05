package ca.ulaval.glo4002.booking.domain.shuttles;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.enums.PassCategories;

public class Passenger {
	
	private Number passNumber;

	public Passenger(Number passNumber) {
		this.passNumber = passNumber;
	}
	
	public Number getPassNumber() {
		return passNumber;
	}
}
