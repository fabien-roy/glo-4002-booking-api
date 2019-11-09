package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.shuttles.Passenger;
import ca.ulaval.glo4002.booking.domain.shuttles.Trip;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryTripRepositoryTest {
	
	private TripRepository repository;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryTripRepository(new ShuttleFactory());
	}

	@Test
	void getDeparturesForDate_shouldReturnDeparturesForDate() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));
		repository.addPassengerToDepartures(aPassenger, aCategory, expectedTripDate);

		List<Trip> trips = repository.getDeparturesForDate(expectedTripDate);

		assertEquals(expectedTripDate, trips.get(0).getTripDate());
	}

	@Test
	void getArrivalsForDate_shouldReturnArrivalsForDate() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));
		repository.addPassengerToArrivals(aPassenger, aCategory, expectedTripDate);

		List<Trip> trips = repository.getArrivalsForDate(expectedTripDate);

		assertEquals(expectedTripDate, trips.get(0).getTripDate());
	}

	@Test
	void addPassengerToDepartures_shouldAddNewDepartureTrip_whenThereIsNone() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));

	    repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);

	    assertEquals(1, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddToExistingDepartureTrip_whenTripIsNotFull() {
		ShuttleCategories aCategory = ShuttleCategories.MILLENNIUM_FALCON;
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));
		Passenger anotherPassenger = new Passenger(new Number(2L));
		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);

		repository.addPassengerToDepartures(anotherPassenger, aCategory, aTripDate);

		assertEquals(1, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddNewDepartureTrip_whenTripIsFull() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));
		Passenger anotherPassenger = new Passenger(new Number(2L));

		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);
		repository.addPassengerToDepartures(anotherPassenger, aCategory, aTripDate);

		assertEquals(2, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddNewDepartureTrip_whenTripIsNotSameCategory() {
		ShuttleCategories aCategory = ShuttleCategories.SPACE_X;
		ShuttleCategories anotherCategory = ShuttleCategories.MILLENNIUM_FALCON;
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));
		Passenger anotherPassenger = new Passenger(new Number(2L));

		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);
		repository.addPassengerToDepartures(anotherPassenger, anotherCategory, aTripDate);

		assertEquals(2, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddTripDate_toDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));

		repository.addPassengerToDepartures(aPassenger, aCategory, expectedTripDate);
		Trip trip = repository.getDeparturesForDate(expectedTripDate).get(0);

		assertEquals(expectedTripDate, trip.getTripDate());
	}

	@Test
	void addPassengerToDepartures_shouldAddPassenger_toDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Number expectedPassNumber = new Number(1L);
		Passenger aPassenger = new Passenger(expectedPassNumber);

		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);
		Number passNumber = repository.getDeparturesForDate(aTripDate).get(0).getPassengersPassNumbers().get(0);

		assertEquals(expectedPassNumber, passNumber);
	}

	@Test
	void addPassengerToArrivals_shouldAddNewDepartureTrip_whenThereIsNone() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);

		assertEquals(1, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddToExistingDepartureTrip_whenTripIsNotFull() {
		ShuttleCategories aCategory = ShuttleCategories.MILLENNIUM_FALCON;
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));
		Passenger anotherPassenger = new Passenger(new Number(2L));
		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);

		repository.addPassengerToArrivals(anotherPassenger, aCategory, aTripDate);

		assertEquals(1, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddNewDepartureTrip_whenTripIsFull() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));
		Passenger anotherPassenger = new Passenger(new Number(2L));

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);
		repository.addPassengerToArrivals(anotherPassenger, aCategory, aTripDate);

		assertEquals(2, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddNewDepartureTrip_whenTripIsNotSameCategory() {
		ShuttleCategories aCategory = ShuttleCategories.SPACE_X;
		ShuttleCategories anotherCategory = ShuttleCategories.MILLENNIUM_FALCON;
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));
		Passenger anotherPassenger = new Passenger(new Number(2L));

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);
		repository.addPassengerToArrivals(anotherPassenger, anotherCategory, aTripDate);

		assertEquals(2, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddTripDate_toDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = new EventDate(EventDate.START_DATE);
		Passenger aPassenger = new Passenger(new Number(1L));

		repository.addPassengerToArrivals(aPassenger, aCategory, expectedTripDate);
		Trip trip = repository.getArrivalsForDate(expectedTripDate).get(0);

		assertEquals(expectedTripDate, trip.getTripDate());
	}

	@Test
	void addPassengerToArrivals_shouldAddPassenger_toDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = new EventDate(EventDate.START_DATE);
		Number expectedPassNumber = new Number(1L);
		Passenger aPassenger = new Passenger(expectedPassNumber);

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);
		Number passNumber = repository.getArrivalsForDate(aTripDate).get(0).getPassengersPassNumbers().get(0);

		assertEquals(expectedPassNumber, passNumber);
	}

	// TODO : Tests for add to new trip
}
