package ca.ulaval.glo4002.booking.domain.trip;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleFullException;

class TripTest {

	private static Passenger aPassenger;
	private static Passenger anotherPassenger;
	private static Shuttle aShuttle;
	private static EventDate anEventDate;

	@BeforeAll
	public static void tripSetUp() {
		anEventDate = new EventDate(LocalDate.of(2050, 7, 21));
		aPassenger = mock(Passenger.class);
		when(aPassenger.getPassNumber()).thenReturn(new Number(10000000L));
		when(aPassenger.getPassCategory()).thenReturn(PassCategories.SUPERNOVA);
		anotherPassenger = mock(Passenger.class);
		when(anotherPassenger.getPassNumber()).thenReturn(new Number(10000001L));
		when(anotherPassenger.getPassCategory()).thenReturn(PassCategories.SUPERNOVA);
		aShuttle = new Shuttle(ShuttleCategories.ET_SPACESHIP, 1, new Money(new BigDecimal(100000)));
	}
	
	@Test
	public void addPassenger_whenShuttleHasPlacesEmpty_passengerShouldBeAdded() {
		Trip trip = new Trip(anEventDate, aShuttle);
		trip.addPassenger(aPassenger);
		
		assertEquals(aPassenger.getPassNumber(), trip.getPassengersPassNumbers().get(0));
	}
	
	@Test
	public void addPassenger_whenShuttleIsFull_shuttleFullExceptionShouldBeThrown() {
		Trip trip = new Trip(anEventDate, aShuttle);
		trip.addPassenger(aPassenger);

		assertThrows(ShuttleFullException.class, () -> {
			trip.addPassenger(anotherPassenger);
		});
	}
}