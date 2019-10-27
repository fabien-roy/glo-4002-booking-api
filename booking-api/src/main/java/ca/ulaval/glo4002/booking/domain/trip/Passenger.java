package ca.ulaval.glo4002.booking.domain.trip;

public class Passenger {
	
	private Number passNumber;
	
	public Passenger(Number passNumber) {
		this.passNumber = passNumber;
	}
	
	public Number getPassNumber() {
		return passNumber;
	}

}
