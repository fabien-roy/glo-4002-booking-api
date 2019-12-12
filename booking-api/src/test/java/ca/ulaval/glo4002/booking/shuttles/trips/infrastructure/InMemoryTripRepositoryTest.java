package ca.ulaval.glo4002.booking.shuttles.trips.infrastructure;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.shuttles.domain.Passenger;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleFactory;
import ca.ulaval.glo4002.booking.shuttles.trips.domain.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryTripRepositoryTest {

	// TODO : When refactoring InMemoryTripRepository, check review comments for tests
	
	private TripRepository repository;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryTripRepository(new ShuttleFactory());
	}

	@Test
	void getDeparturesForDate_shouldReturnDeparturesForDate() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);
		repository.addPassengerToDepartures(aPassenger, aCategory, expectedTripDate);

		List<Trip> trips = repository.getDeparturesForDate(expectedTripDate);

		assertEquals(expectedTripDate, trips.get(0).getTripDate());
	}

	@Test
	void getArrivalsForDate_shouldReturnArrivalsForDate() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);
		repository.addPassengerToArrivals(aPassenger, aCategory, expectedTripDate);

		List<Trip> trips = repository.getArrivalsForDate(expectedTripDate);

		assertEquals(expectedTripDate, trips.get(0).getTripDate());
	}

	@Test
	void addPassengerToDepartures_shouldAddNewDepartureTrip_whenThereIsNone() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);

	    repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);

	    assertEquals(1, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddToExistingDepartureTrip_whenTripIsNotFull() {
		ShuttleCategories aCategory = ShuttleCategories.MILLENNIUM_FALCON;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);
		Passenger anotherPassenger = new Passenger(2L);
		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);

		repository.addPassengerToDepartures(anotherPassenger, aCategory, aTripDate);

		assertEquals(1, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddNewDepartureTrip_whenTripIsFull() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);
		Passenger anotherPassenger = new Passenger(2L);

		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);
		repository.addPassengerToDepartures(anotherPassenger, aCategory, aTripDate);

		assertEquals(2, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddNewDepartureTrip_whenTripIsNotSameCategory() {
		ShuttleCategories aCategory = ShuttleCategories.SPACE_X;
		ShuttleCategories anotherCategory = ShuttleCategories.MILLENNIUM_FALCON;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);
		Passenger anotherPassenger = new Passenger(2L);

		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);
		repository.addPassengerToDepartures(anotherPassenger, anotherCategory, aTripDate);

		assertEquals(2, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddTripDateToDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);

		repository.addPassengerToDepartures(aPassenger, aCategory, expectedTripDate);
		Trip trip = repository.getDeparturesForDate(expectedTripDate).get(0);

		assertEquals(expectedTripDate, trip.getTripDate());
	}

	@Test
	void addPassengerToDepartures_shouldAddPassengerToDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Long expectedPassengerNumber = 1L;
		Passenger aPassenger = new Passenger(expectedPassengerNumber);

		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);
		Long passengerNumber = repository.getDeparturesForDate(aTripDate).get(0).getPassengerNumbers().get(0);

		assertEquals(expectedPassengerNumber, passengerNumber);
	}

	@Test
	void addPassengersToNewDeparture_shouldAddPassengerToNewDeparture() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Long expectedPassNumber = 1L;
		List<Passenger> somePassengers = Collections.singletonList(new Passenger(expectedPassNumber));

		repository.addPassengersToNewDeparture(somePassengers, aCategory, aTripDate);
		Long passengerNumber = repository.getDeparturesForDate(aTripDate).get(0).getPassengerNumbers().get(0);

		assertEquals(expectedPassNumber, passengerNumber);
	}

	@Test
	void addPassengerToArrivals_shouldAddNewDepartureTrip_whenThereIsNone() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);

		assertEquals(1, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddToExistingDepartureTrip_whenTripIsNotFull() {
		ShuttleCategories aCategory = ShuttleCategories.MILLENNIUM_FALCON;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);
		Passenger anotherPassenger = new Passenger(2L);
		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);

		repository.addPassengerToArrivals(anotherPassenger, aCategory, aTripDate);

		assertEquals(1, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddNewDepartureTrip_whenTripIsFull() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);
		Passenger anotherPassenger = new Passenger(2L);

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);
		repository.addPassengerToArrivals(anotherPassenger, aCategory, aTripDate);

		assertEquals(2, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddNewDepartureTrip_whenTripIsNotSameCategory() {
		ShuttleCategories aCategory = ShuttleCategories.SPACE_X;
		ShuttleCategories anotherCategory = ShuttleCategories.MILLENNIUM_FALCON;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);
		Passenger anotherPassenger = new Passenger(2L);

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);
		repository.addPassengerToArrivals(anotherPassenger, anotherCategory, aTripDate);

		assertEquals(2, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddTripDateToDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Passenger aPassenger = new Passenger(1L);

		repository.addPassengerToArrivals(aPassenger, aCategory, expectedTripDate);
		Trip trip = repository.getArrivalsForDate(expectedTripDate).get(0);

		assertEquals(expectedTripDate, trip.getTripDate());
	}

	@Test
	void addPassengerToArrivals_shouldAddPassengerToDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Long expectedPassengerNumber = 1L;
		Passenger aPassenger = new Passenger(expectedPassengerNumber);

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);
		Long passengerNumber = repository.getArrivalsForDate(aTripDate).get(0).getPassengerNumbers().get(0);

		assertEquals(expectedPassengerNumber, passengerNumber);
	}

	@Test
	void addPassengersToNewArrival_shouldAddPassengerToNewArrival() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = FestivalConfiguration.getDefaultStartEventDate();
		Long expectedPassengerNumber = 1L;
		List<Passenger> somePassengers = Collections.singletonList(new Passenger(expectedPassengerNumber));

		repository.addPassengersToNewArrival(somePassengers, aCategory, aTripDate);
		Long passengerNumber = repository.getArrivalsForDate(aTripDate).get(0).getPassengerNumbers().get(0);

		assertEquals(expectedPassengerNumber, passengerNumber);
	}
}
