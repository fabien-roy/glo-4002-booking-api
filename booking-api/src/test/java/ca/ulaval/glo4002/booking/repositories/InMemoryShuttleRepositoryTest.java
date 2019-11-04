package ca.ulaval.glo4002.booking.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleNotFoundException;

class InMemoryShuttleRepositoryTest {
	
	private ShuttleRepository repository;
	private static Shuttle aShuttle;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryShuttleRepository();
	}

	@BeforeEach
	void setUpShuttle() {
		aShuttle = new Shuttle(
				new Number(1L),
				ShuttleCategories.ET_SPACESHIP,
				1,
				new Money(new BigDecimal(100000))
		);
	}

	@Test
	void findByShuttleNumber_shouldThrowShuttleNotFoundException_whenThereAreNoShuttle() {
		Number aNonExistingShuttleNumber = new Number(1000000000L);

		assertThrows(ShuttleNotFoundException.class, () -> repository.findByShuttleNumber(aNonExistingShuttleNumber));
	}
	
	@Test
	void findByShuttleNumber_shouldThrowShuttleNotFoundException_whenShuttleIsNotPresent() {
		repository.addShuttle(aShuttle);

		assertThrows(ShuttleNotFoundException.class, () -> repository.findByShuttleNumber(new Number(200L)));
	}
	
	@Test
	void findByShuttleNumber_shouldReturnShuttle() {
		repository.addShuttle(aShuttle);

		Optional<Shuttle> foundShuttle = repository.findByShuttleNumber(aShuttle.getShuttleNumber());
		
		assertTrue(foundShuttle.isPresent());
		assertEquals(foundShuttle.get(), aShuttle);
	}
	
	@Test
	void findByShuttleNumber_shouldReturnShuttles_whenThereAreMultipleShuttles() {
		Shuttle anotherShuttle = new Shuttle(
				new Number(2L),
				ShuttleCategories.MILLENNIUM_FALCON,
				20,
				new Money(new BigDecimal(65000))
		);
		repository.addShuttle(aShuttle);
		repository.addShuttle(anotherShuttle);
		
		Optional<Shuttle> foundAShuttle = repository.findByShuttleNumber(aShuttle.getShuttleNumber());
		Optional<Shuttle> foundAnotherShuttle = repository.findByShuttleNumber(anotherShuttle.getShuttleNumber());
		
		assertTrue(foundAShuttle.isPresent());
		assertTrue(foundAnotherShuttle.isPresent());
		assertEquals(foundAShuttle.get(), aShuttle);
		assertEquals(foundAnotherShuttle.get(), anotherShuttle);
	}
	
	@Test
	void addShuttle_shouldThrowShuttleAlreadyCreatedException_whenShuttleAlreadyExists() {
		repository.addShuttle(aShuttle);
		
		assertThrows(ShuttleAlreadyCreatedException.class, () -> repository.addShuttle(aShuttle));
	}
}
