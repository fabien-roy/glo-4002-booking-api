package ca.ulaval.glo4002.booking.shuttles.domain;

import ca.ulaval.glo4002.booking.numbers.Number;

public class Passenger {
	
	private Number passNumber;

	public Passenger(Number passNumber) {
		this.passNumber = passNumber;
	}
	
	public Number getPassNumber() {
		return passNumber;
	}
}
