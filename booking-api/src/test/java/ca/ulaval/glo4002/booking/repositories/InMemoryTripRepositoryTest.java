package ca.ulaval.glo4002.booking.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

class InMemoryTripRepositoryTest {
	
	private TripRepository repository;
	private static Shuttle aShuttle;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryTripRepository();
	}

	@BeforeEach
	void setUpShuttle() {
		aShuttle = new Shuttle(
				ShuttleCategories.ET_SPACESHIP,
				1,
				new Money(new BigDecimal(100000))
		);
	}

	@Test
	void addPassenger_shouldAddPassengerToDepartureShuttle() {
		// TODO
	}
}
