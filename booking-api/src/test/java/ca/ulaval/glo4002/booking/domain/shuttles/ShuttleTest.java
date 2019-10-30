package ca.ulaval.glo4002.booking.domain.shuttles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.trip.Trip;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

public class ShuttleTest {
	
	private static Shuttle shuttle;
	private static Trip aTrip;
	private static final ShuttleCategories A_SHUTTLE_CATEGORY = ShuttleCategories.MILLENNIUM_FALCON;
	private static final Integer A_MAX_CAPACITY = 20;
	private static final Money A_PRICE = new Money(new BigDecimal(65000));
	private static final EventDate AN_EVENT_DATE = new EventDate(LocalDate.of(2050, 7, 21));
	private static final EventDate ANOTHER_EVENT_DATE = new EventDate(LocalDate.of(2050, 7, 22));
	private static NumberGenerator numberGenerator = new NumberGenerator();
	
	@BeforeAll
	public static void tripSetUp() {
		aTrip = mock(Trip.class);
		when(aTrip.getTripDate()).thenReturn(AN_EVENT_DATE);		
	}
	@BeforeEach
	public void shuttleSetUp() {
		shuttle = new Shuttle(numberGenerator.generate(), 
				A_SHUTTLE_CATEGORY, A_MAX_CAPACITY, A_PRICE);
	}
	
	@Test
	public void getDeparturesByDate_whenThereAreNoDepartures_shouldReturnEmptyList() {
		List<Trip> departuresForTheDate = shuttle.getDeparturesByDate(AN_EVENT_DATE);
		
		assertEquals(0, departuresForTheDate.size());
	}
	
	@Test
	public void getDeparturesByDate_whenThereIsNoDepartureForTheDate_shouldReturnEmptyList() {
		shuttle.addDeparture(aTrip);
		List<Trip> departuresForTheDate = shuttle.getDeparturesByDate(ANOTHER_EVENT_DATE);
		
		assertEquals(0, departuresForTheDate.size());
		
	}
	
	@Test
	public void getDeparturesByDate_whenThereIsADepartureForTheDate_shouldReturnTheDeparture() {
		shuttle.addDeparture(aTrip);
		List<Trip> departuresForTheDate = shuttle.getDeparturesByDate(AN_EVENT_DATE);
		
		assertEquals(1, departuresForTheDate.size());
		assertTrue(departuresForTheDate.contains(aTrip));
	}
	
	@Test
	public void getDeparturesByDate_whenThereIsMultipleDepartureForTheDate_shouldReturnTheDepartures() {
		Trip anotherTrip = mock(Trip.class);
		when(anotherTrip.getTripDate()).thenReturn(AN_EVENT_DATE);
		Trip aThirdTrip = mock(Trip.class);
		when(aThirdTrip.getTripDate()).thenReturn(ANOTHER_EVENT_DATE);
		
		shuttle.addDeparture(aTrip);
		shuttle.addDeparture(anotherTrip);
		shuttle.addDeparture(aThirdTrip);

		List<Trip> departuresForTheDate = shuttle.getDeparturesByDate(AN_EVENT_DATE);
		
		assertEquals(2, departuresForTheDate.size());
		assertTrue(departuresForTheDate.contains(aTrip));
		assertTrue(departuresForTheDate.contains(anotherTrip));
		assertFalse(departuresForTheDate.contains(aThirdTrip));
	}
	
	@Test
	public void getArrivalsByDate_whenThereAreNoArrivals_shouldReturnEmptyList() {
		List<Trip> arrivalsForTheDate = shuttle.getArrivalsByDate(AN_EVENT_DATE);
		
		assertEquals(0, arrivalsForTheDate.size());
	}
	
	@Test
	public void getArrivalsByDate_whenThereIsNoArrivalForTheDate_shouldReturnEmptyList() {
		shuttle.addArrival(aTrip);
		List<Trip> arrivalsForTheDate = shuttle.getArrivalsByDate(ANOTHER_EVENT_DATE);
		
		assertEquals(0, arrivalsForTheDate.size());
		
	}
	
	@Test
	public void getArrivalsByDate_whenThereIsAnArrivalForTheDate_shouldReturnTheArrival() {
		shuttle.addArrival(aTrip);
		List<Trip> arrivalsForTheDate = shuttle.getArrivalsByDate(AN_EVENT_DATE);
		
		assertEquals(1, arrivalsForTheDate.size());
		assertTrue(arrivalsForTheDate.contains(aTrip));
	}
	
	@Test
	public void getArrivalsByDate_whenThereAreMultipleArrivalsForTheDate_shouldReturnTheArrivals() {
		Trip anotherTrip = mock(Trip.class);
		when(anotherTrip.getTripDate()).thenReturn(AN_EVENT_DATE);
		Trip aThirdTrip = mock(Trip.class);
		when(aThirdTrip.getTripDate()).thenReturn(ANOTHER_EVENT_DATE);
		
		shuttle.addArrival(aTrip);
		shuttle.addArrival(anotherTrip);
		shuttle.addArrival(aThirdTrip);

		List<Trip> arrivalsForTheDate = shuttle.getArrivalsByDate(AN_EVENT_DATE);
		
		assertEquals(2, arrivalsForTheDate.size());
		assertTrue(arrivalsForTheDate.contains(aTrip));
		assertTrue(arrivalsForTheDate.contains(anotherTrip));
		assertFalse(arrivalsForTheDate.contains(aThirdTrip));
	}

}
