package ca.ulaval.glo4002.booking.shuttles.domain;

public class Passenger {

	private long number; // Not using a value object, since Passenger is nothing but a number

	public Passenger(long number) {
		this.number = number;
	}
	
	public long getNumber() {
		return number;
	}
}
