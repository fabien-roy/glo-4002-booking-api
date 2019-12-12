package ca.ulaval.glo4002.booking.shuttles.domain;

import ca.ulaval.glo4002.booking.passes.domain.PassNumber;

public class Passenger {

    // TODO : Have "PassengerNumber"
	private PassNumber passNumber;

	public Passenger(PassNumber passNumber) {
		this.passNumber = passNumber;
	}
	
	public PassNumber getPassNumber() {
		return passNumber;
	}
}
