package ca.ulaval.glo4002.booking.domain.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleFullException;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;

class TripTest {

	private static Shuttle aShuttle;
	private static Passenger aPassenger;
	private static Passenger anotherPassenger;

	@BeforeEach
	void setUpPassengers() {
		aPassenger = mock(Passenger.class);
		when(aPassenger.getPassNumber()).thenReturn(new Number(10000000L));
		anotherPassenger = mock(Passenger.class);
		when(anotherPassenger.getPassNumber()).thenReturn(new Number(10000001L));
	}

	@BeforeEach
	void setUpShuttle() {
		ShuttleFactory shuttleFactory = new ShuttleFactory();
		aShuttle = shuttleFactory.build(ShuttleCategories.ET_SPACESHIP);
	}
	
	@Test
	void addPassenger_whenShuttleHasPlacesEmpty_passengerShouldBeAdded() {
		LocalDate aTripDate = EventDate.START_DATE;
		Trip trip = new Trip(aTripDate, aShuttle);

		trip.addPassenger(aPassenger);
		
		assertEquals(aPassenger.getPassNumber(), trip.getPassengersPassNumbers().get(0));
	}
}