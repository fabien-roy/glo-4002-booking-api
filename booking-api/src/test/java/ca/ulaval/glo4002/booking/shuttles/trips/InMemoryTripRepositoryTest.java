package ca.ulaval.glo4002.booking.shuttles.trips;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.shuttles.*;
import ca.ulaval.glo4002.booking.shuttles.trips.InMemoryTripRepository;
import ca.ulaval.glo4002.booking.shuttles.trips.Trip;
import ca.ulaval.glo4002.booking.shuttles.trips.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
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
		EventDate expectedTripDate = EventDate.getStartEventDate();
		Passenger aPassenger = new Passenger(new Number(1L));
		repository.addPassengerToDepartures(aPassenger, aCategory, expectedTripDate);

		List<Trip> trips = repository.getDeparturesForDate(expectedTripDate);

		assertEquals(expectedTripDate, trips.get(0).getTripDate());
	}

	@Test
	void getArrivalsForDate_shouldReturnArrivalsForDate() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = EventDate.getStartEventDate();
		Passenger aPassenger = new Passenger(new Number(1L));
		repository.addPassengerToArrivals(aPassenger, aCategory, expectedTripDate);

		List<Trip> trips = repository.getArrivalsForDate(expectedTripDate);

		assertEquals(expectedTripDate, trips.get(0).getTripDate());
	}

	@Test
	void addPassengerToDepartures_shouldAddNewDepartureTrip_whenThereIsNone() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = EventDate.getStartEventDate();
		Passenger aPassenger = new Passenger(new Number(1L));

	    repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);

	    assertEquals(1, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddToExistingDepartureTrip_whenTripIsNotFull() {
		ShuttleCategories aCategory = ShuttleCategories.MILLENNIUM_FALCON;
		EventDate aTripDate = EventDate.getStartEventDate();
		Passenger aPassenger = new Passenger(new Number(1L));
		Passenger anotherPassenger = new Passenger(new Number(2L));
		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);

		repository.addPassengerToDepartures(anotherPassenger, aCategory, aTripDate);

		assertEquals(1, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddNewDepartureTrip_whenTripIsFull() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = EventDate.getStartEventDate();
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
		EventDate aTripDate = EventDate.getStartEventDate();
		Passenger aPassenger = new Passenger(new Number(1L));
		Passenger anotherPassenger = new Passenger(new Number(2L));

		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);
		repository.addPassengerToDepartures(anotherPassenger, anotherCategory, aTripDate);

		assertEquals(2, repository.getDeparturesForDate(aTripDate).size());
	}

	@Test
	void addPassengerToDepartures_shouldAddTripDateToDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = EventDate.getStartEventDate();
		Passenger aPassenger = new Passenger(new Number(1L));

		repository.addPassengerToDepartures(aPassenger, aCategory, expectedTripDate);
		Trip trip = repository.getDeparturesForDate(expectedTripDate).get(0);

		assertEquals(expectedTripDate, trip.getTripDate());
	}

	@Test
	void addPassengerToDepartures_shouldAddPassengerToDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = EventDate.getStartEventDate();
		Number expectedPassNumber = new Number(1L);
		Passenger aPassenger = new Passenger(expectedPassNumber);

		repository.addPassengerToDepartures(aPassenger, aCategory, aTripDate);
		Number passNumber = repository.getDeparturesForDate(aTripDate).get(0).getPassengersPassNumbers().get(0);

		assertEquals(expectedPassNumber, passNumber);
	}

	@Test
	void addPassengersToNewDeparture_shouldAddPassengerToNewDeparture() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = EventDate.getStartEventDate();
		Number expectedPassNumber = new Number(1L);
		List<Passenger> somePassengers = Collections.singletonList(new Passenger(expectedPassNumber));

		repository.addPassengersToNewDeparture(somePassengers, aCategory, aTripDate);
		Number passNumber = repository.getDeparturesForDate(aTripDate).get(0).getPassengersPassNumbers().get(0);

		assertEquals(expectedPassNumber, passNumber);
	}

	@Test
	void addPassengerToArrivals_shouldAddNewDepartureTrip_whenThereIsNone() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = EventDate.getStartEventDate();
		Passenger aPassenger = new Passenger(new Number(1L));

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);

		assertEquals(1, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddToExistingDepartureTrip_whenTripIsNotFull() {
		ShuttleCategories aCategory = ShuttleCategories.MILLENNIUM_FALCON;
		EventDate aTripDate = EventDate.getStartEventDate();
		Passenger aPassenger = new Passenger(new Number(1L));
		Passenger anotherPassenger = new Passenger(new Number(2L));
		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);

		repository.addPassengerToArrivals(anotherPassenger, aCategory, aTripDate);

		assertEquals(1, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddNewDepartureTrip_whenTripIsFull() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = EventDate.getStartEventDate();
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
		EventDate aTripDate = EventDate.getStartEventDate();
		Passenger aPassenger = new Passenger(new Number(1L));
		Passenger anotherPassenger = new Passenger(new Number(2L));

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);
		repository.addPassengerToArrivals(anotherPassenger, anotherCategory, aTripDate);

		assertEquals(2, repository.getArrivalsForDate(aTripDate).size());
	}

	@Test
	void addPassengerToArrivals_shouldAddTripDateToDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate expectedTripDate = EventDate.getStartEventDate();
		Passenger aPassenger = new Passenger(new Number(1L));

		repository.addPassengerToArrivals(aPassenger, aCategory, expectedTripDate);
		Trip trip = repository.getArrivalsForDate(expectedTripDate).get(0);

		assertEquals(expectedTripDate, trip.getTripDate());
	}

	@Test
	void addPassengerToArrivals_shouldAddPassengerToDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = EventDate.getStartEventDate();
		Number expectedPassNumber = new Number(1L);
		Passenger aPassenger = new Passenger(expectedPassNumber);

		repository.addPassengerToArrivals(aPassenger, aCategory, aTripDate);
		Number passNumber = repository.getArrivalsForDate(aTripDate).get(0).getPassengersPassNumbers().get(0);

		assertEquals(expectedPassNumber, passNumber);
	}

	@Test
	void addPassengersToNewArrival_shouldAddPassengerToNewArrival() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		EventDate aTripDate = EventDate.getStartEventDate();
		Number expectedPassNumber = new Number(1L);
		List<Passenger> somePassengers = Collections.singletonList(new Passenger(expectedPassNumber));

		repository.addPassengersToNewArrival(somePassengers, aCategory, aTripDate);
		Number passNumber = repository.getArrivalsForDate(aTripDate).get(0).getPassengersPassNumbers().get(0);

		assertEquals(expectedPassNumber, passNumber);
	}
}
