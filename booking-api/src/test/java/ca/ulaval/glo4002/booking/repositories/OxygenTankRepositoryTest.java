package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.entities.OxygenTankEntity;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenTankNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OxygenTankRepositoryTest {

	private OxygenTankRepositoryContext context;
	private OxygenTankRepositoryImpl subject;

	@BeforeEach
	public void setUp() {
		context = new OxygenTankRepositoryContext();
		subject = new OxygenTankRepositoryImpl(context.entityManager);
	}

	@Test
	public void findById_shouldThrowOxygenTankNotFoundException_whenOxygenTankDoesNotExist() {
		OxygenTankNotFoundException thrown = assertThrows(
				OxygenTankNotFoundException.class,
				() -> subject.findById(OxygenTankRepositoryContext.NON_EXISTENT_OXYGEN_ID)
		);

		assertEquals(ExceptionConstants.Oxygen.TANK_NOT_FOUND_MESSAGE, thrown.getMessage());
	}

	@Test
	public void findById_shouldReturnCorrectOxygenTank() {
		OxygenTankEntity oxygenTank = subject.findById(OxygenTankRepositoryContext.A_OXYGEN_ID).get();

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
		subject.save(context.nonExistentOxygenTank);

		OxygenTankEntity tank = subject.findById(OxygenTankRepositoryContext.NON_EXISTENT_OXYGEN_ID).get();

		assertEquals(context.nonExistentOxygenTank, tank);
	}
}
