package ca.ulaval.glo4002.booking.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenTankNotFoundException;

class OxygenTankRepositoryTest {

	OxygenTankRepositoryContext context;
	OxygenRepositoryImpl subject;

	@BeforeEach
	public void setUp() {
		context = new OxygenTankRepositoryContext();
		subject = new OxygenRepositoryImpl(context.entityManager);
	}

	@Test
	public void findById_shouldThrowOxygenTankNotFoundException_whenOxygenTankDoesNotExist() {
		OxygenTankNotFoundException thrown = assertThrows(OxygenTankNotFoundException.class,
				() -> subject.findById(context.NON_EXISTANT_OXYGEN_ID));

		assertEquals(ExceptionConstants.OXYGEN_TANK_NOT_FOUND_MESSAGE, thrown.getMessage());
	}

	@Test
	public void findById_shouldReturnCorrectOxygenTank() {
		OxygenTankEntity oxygenTank = subject.findById(context.A_OXYGEN_ID).get();

		assertEquals(context.oxygenTankA, oxygenTank);
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

	@Test
	public void save_shouldSaveOxygen() {
		context.setUpEntityManagerForSave();
		subject.save(context.oxygenTankNotExist);
		OxygenTankEntity tank = subject.findById(context.NON_EXISTANT_OXYGEN_ID).get();

		assertEquals(context.oxygenTankNotExist, tank);
	}
}
