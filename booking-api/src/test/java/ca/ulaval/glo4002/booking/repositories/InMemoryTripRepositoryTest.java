package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.trip.Trip;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryTripRepositoryTest {
	
	private TripRepository repository;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryTripRepository(new ShuttleFactory());
	}

	@Test
	void addPassenger_shouldAddNewDepartureTrip_whenThereIsNone() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		LocalDate aTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);

	    repository.addPassenger(aCategory, aTripDate, aPassNumber);

	    assertEquals(1, repository.getDepartures().size());
	}

	@Test
	void addPassenger_shouldAddToExistingDepartureTrip_whenTripIsNotFull() {
		ShuttleCategories aCategory = ShuttleCategories.MILLENNIUM_FALCON;
		LocalDate aTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);
		Number anotherPassNumber = new Number(1L);
		repository.addPassenger(aCategory, aTripDate, aPassNumber);

		repository.addPassenger(aCategory, aTripDate, anotherPassNumber);

		assertEquals(1, repository.getDepartures().size());
	}

	@Test
	void addPassenger_shouldAddNewDepartureTrip_whenTripIsFull() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		LocalDate aTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);
		Number anotherPassNumber = new Number(1L);
		repository.addPassenger(aCategory, aTripDate, aPassNumber);

		repository.addPassenger(aCategory, aTripDate, anotherPassNumber);

		assertEquals(2, repository.getDepartures().size());
	}

	@Test
	void addPassenger_shouldAddNewDepartureTrip_whenTripIsNotSameCategory() {
		ShuttleCategories aCategory = ShuttleCategories.SPACE_X;
		ShuttleCategories anotherCategory = ShuttleCategories.MILLENNIUM_FALCON;
		LocalDate aTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);
		Number anotherPassNumber = new Number(1L);

		repository.addPassenger(aCategory, aTripDate, aPassNumber);
		repository.addPassenger(anotherCategory, aTripDate, anotherPassNumber);

		assertEquals(2, repository.getDepartures().size());
	}

	@Test
	void addPassenger_shouldAddTripDate_toDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		LocalDate expectedTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);

		repository.addPassenger(aCategory, expectedTripDate, aPassNumber);
		Trip trip = repository.getDepartures().get(0);

		assertEquals(expectedTripDate, trip.getTripDate());
	}

	@Test
	void addPassenger_shouldAddPassenger_toDepartureTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		LocalDate aTripDate = EventDate.START_DATE;
		Number expectedPassNumber = new Number(1L);

		repository.addPassenger(aCategory, aTripDate, expectedPassNumber);
		Number passNumber = repository.getDepartures().get(0).getPassengersPassNumbers().get(0);

		assertEquals(expectedPassNumber, passNumber);
	}

	@Test
	void addPassenger_shouldAddNewArrivalTrip_whenThereIsNone() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		LocalDate aTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);

		repository.addPassenger(aCategory, aTripDate, aPassNumber);

		assertEquals(1, repository.getArrivals().size());
	}

	@Test
	void addPassenger_shouldAddTripDate_toArrivalTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		LocalDate expectedTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);

		repository.addPassenger(aCategory, expectedTripDate, aPassNumber);
		Trip trip = repository.getArrivals().get(0);

		assertEquals(expectedTripDate, trip.getTripDate());
	}

	@Test
	void addPassenger_shouldAddPassenger_toArrivalTrips() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		LocalDate aTripDate = EventDate.START_DATE;
		Number expectedPassNumber = new Number(1L);

		repository.addPassenger(aCategory, aTripDate, expectedPassNumber);
		Number passNumber = repository.getArrivals().get(0).getPassengersPassNumbers().get(0);

		assertEquals(expectedPassNumber, passNumber);
	}

	@Test
	void addPassenger_shouldAddToExistingArrivalTrip_whenTripIsNotFull() {
		ShuttleCategories aCategory = ShuttleCategories.MILLENNIUM_FALCON;
		LocalDate aTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);
		Number anotherPassNumber = new Number(1L);

		repository.addPassenger(aCategory, aTripDate, aPassNumber);
		repository.addPassenger(aCategory, aTripDate, anotherPassNumber);

		assertEquals(1, repository.getArrivals().size());
	}

	@Test
	void addPassenger_shouldAddNewArrivalTrip_whenTripIsFull() {
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		LocalDate aTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);
		Number anotherPassNumber = new Number(1L);

		repository.addPassenger(aCategory, aTripDate, aPassNumber);
		repository.addPassenger(aCategory, aTripDate, anotherPassNumber);

		assertEquals(2, repository.getArrivals().size());
	}

	@Test
	void addPassenger_shouldAddNewArrivalTrip_whenTripIsNotSameCategory() {
		ShuttleCategories aCategory = ShuttleCategories.SPACE_X;
		ShuttleCategories anotherCategory = ShuttleCategories.MILLENNIUM_FALCON;
		LocalDate aTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);
		Number anotherPassNumber = new Number(1L);

		repository.addPassenger(aCategory, aTripDate, aPassNumber);
		repository.addPassenger(anotherCategory, aTripDate, anotherPassNumber);

		assertEquals(2, repository.getArrivals().size());
	}
}
