package ca.ulaval.glo4002.booking.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import ca.ulaval.glo4002.booking.domain.EventDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

class InMemoryTripRepositoryTest {
	
	private TripRepository repository;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryTripRepository();
	}

	@Test
	void addPassenger_shouldAddNewDeparture_whenThereIsNone() {
		// TODO
		/*
		ShuttleCategories aCategory = ShuttleCategories.ET_SPACESHIP;
		LocalDate aTripDate = EventDate.START_DATE;
		Number aPassNumber = new Number(1L);

	    repository.addPassenger(aCategory, aTripDate, aPassNumber);

	    assertEquals(1, repository.getDepartures().size());
	    assertEquals(aTripDate, repository.getDepartures().get(0).getTripDate());
	    */
	}
}
