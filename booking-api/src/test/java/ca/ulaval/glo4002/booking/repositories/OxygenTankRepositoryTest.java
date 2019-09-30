package ca.ulaval.glo4002.booking.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;

class OxygenTankRepositoryTest {

	OxygenTankRepositoryContext context;
	OxygenRepositoryImpl subject;

	@BeforeEach
	public void setUp() {
		context = new OxygenTankRepositoryContext();
		subject = new OxygenRepositoryImpl(context.entityManager);
	}

	@Test
	public void findAll_shouldReturnCorrectOxygens() {
		List<OxygenTankEntity> oxygenTank = new ArrayList<>();

		subject.findAll().forEach(oxygenTank::add);

		assertEquals(3, oxygenTank.size());
		assertTrue(oxygenTank.contains(context.oxygenTankA));
		assertTrue(oxygenTank.contains(context.oxygenTankB));
		assertTrue(oxygenTank.contains(context.oxygenTankE));
	}
}
