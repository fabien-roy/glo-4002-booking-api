package ca.ulaval.glo4002.booking.domain.shuttles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.Number;

class PassengerTest {

	Passenger passenger;

	@Test
	void constructing_shouldSetCorrectPassNumber() {
		Number passNumber = new Number(5L);

		passenger = new Passenger(passNumber);

		assertEquals(passNumber, passenger.getPassNumber());
	}

}
