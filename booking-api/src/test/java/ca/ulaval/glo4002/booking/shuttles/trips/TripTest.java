package ca.ulaval.glo4002.booking.shuttles.trips;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.booking.shuttles.Passenger;
import ca.ulaval.glo4002.booking.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.shuttles.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.ShuttleFactory;
import ca.ulaval.glo4002.booking.shuttles.trips.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.numbers.Number;

class TripTest {

	private static Shuttle aShuttle;
	private static Passenger aPassenger;

	@BeforeEach
	void setUpPassengers() {
		aPassenger = mock(Passenger.class);
		when(aPassenger.getPassNumber()).thenReturn(new Number(10000000L));
		Passenger anotherPassenger = mock(Passenger.class);
		when(anotherPassenger.getPassNumber()).thenReturn(new Number(10000001L));
	}

	@BeforeEach
	void setUpShuttle() {
		ShuttleFactory shuttleFactory = new ShuttleFactory();
		aShuttle = shuttleFactory.build(ShuttleCategories.ET_SPACESHIP);
	}
	
	@Test
	void addPassenger_whenShuttleHasPlacesEmpty_passengerShouldBeAdded() {
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Trip trip = new Trip(aTripDate, aShuttle);

		trip.addPassenger(aPassenger);
		
		assertEquals(aPassenger.getPassNumber(), trip.getPassengersPassNumbers().get(0));
	}
}