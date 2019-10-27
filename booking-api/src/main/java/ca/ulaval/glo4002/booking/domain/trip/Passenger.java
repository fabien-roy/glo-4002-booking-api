package ca.ulaval.glo4002.booking.domain.trip;

import ca.ulaval.glo4002.booking.domain.Number;

public class Passenger {
	
	private Number passNumber;
	
	public Passenger(Number passNumber) {
		this.passNumber = passNumber;
	}
	
	public Number getPassNumber() {
		return passNumber;
	}

}
