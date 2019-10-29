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

public class InMemoryShuttleRepositoryTest {
	
	private ShuttleRepository subject;
	private static Shuttle aShuttle;
	
	@BeforeAll
	public static void shuttlesSetUp() {
		aShuttle = new Shuttle(new Number(1L),
				ShuttleCategories.ET_SPACESHIP,
				1,
				new Money(new BigDecimal(100000)));

	}
	
	@BeforeEach
	public void setUpSubject() {
		subject = new InMemoryShuttleRepository();
		
	}
	
	@Test
	public void findByShuttleNumber_shouldThrowShuttleNotFoundException_whenThereAreNoShuttle() {
		Number aNonExistingShuttleNumber = new Number(1000000000L);
		assertThrows(ShuttleNotFoundException.class,
				() -> subject.findByShuttleNumber(aNonExistingShuttleNumber));
	}
	
	@Test
	public void findByShuttleNumber_shouldThrowShuttleNotFoundException_whenShuttleIsNotPresent() {
		subject.addShuttle(aShuttle);
		assertThrows(ShuttleNotFoundException.class,
				() -> subject.findByShuttleNumber(new Number(200L)));
	}
	
	@Test
	public void findByShuttleNumber_shouldReturnShuttle() {
		subject.addShuttle(aShuttle);
		Optional<Shuttle> foundShuttle = subject.findByShuttleNumber(aShuttle.getShuttleNumber());
		
		assertTrue(foundShuttle.isPresent());
		assertEquals(foundShuttle.get(), aShuttle);
	}
	
	@Test
	public void findByShuttleNumber_shouldReturnShuttles_whenThereAreMultipleShuttles() {
		Shuttle anotherShuttle = new Shuttle(new Number(2L),
				ShuttleCategories.MILLENNIUM_FALCON,
				20,
				new Money(new BigDecimal(65000)));
		
		subject.addShuttle(aShuttle);
		subject.addShuttle(anotherShuttle);
		
		Optional<Shuttle> foundAShuttle = subject.
				findByShuttleNumber(aShuttle.getShuttleNumber());
		Optional<Shuttle> foundAnotherShuttle = subject.
				findByShuttleNumber(anotherShuttle.getShuttleNumber());
		
		assertTrue(foundAShuttle.isPresent());
		assertTrue(foundAnotherShuttle.isPresent());
		assertEquals(foundAShuttle.get(), aShuttle);
		assertEquals(foundAnotherShuttle.get(), anotherShuttle);
	}
	
	@Test
	public void addShuttle_shouldThrowShuttleAlreadyCreatedException_whenShuttleAlreadyExists() {
		subject.addShuttle(aShuttle);
		
		assertThrows(ShuttleAlreadyCreatedException.class, 
				() -> subject.addShuttle(aShuttle));
	}
	

}
