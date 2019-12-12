package ca.ulaval.glo4002.booking.shuttles.trips.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.shuttles.domain.Passenger;
import ca.ulaval.glo4002.booking.shuttles.domain.Shuttle;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TripTest {

	private static Shuttle aShuttle;
	private static Passenger aPassenger;

	@BeforeEach
	void setUpPassengers() {
		aPassenger = mock(Passenger.class);
		when(aPassenger.getNumber()).thenReturn(10000000L);
		Passenger anotherPassenger = mock(Passenger.class);
		when(anotherPassenger.getNumber()).thenReturn(10000001L);
	}

	@BeforeEach
	void setUpShuttle() {
		ShuttleFactory shuttleFactory = new ShuttleFactory();
		aShuttle = shuttleFactory.create(ShuttleCategories.ET_SPACESHIP);
	}
	
	@Test
	void addPassenger_whenShuttleHasPlacesEmpty_passengerShouldBeAdded() {
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Trip trip = new Trip(aTripDate, aShuttle);

		trip.addPassenger(aPassenger);
		
		assertEquals(aPassenger.getNumber(), trip.getPassengerNumbers().get(0));
	}
}