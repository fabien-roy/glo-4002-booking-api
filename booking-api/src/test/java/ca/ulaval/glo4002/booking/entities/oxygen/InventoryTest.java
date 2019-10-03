package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.constants.OxygenConstants.Categories;
import ca.ulaval.glo4002.booking.constants.QualityConstants;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest {

	private Inventory subject;
	private final static Long A_NUMBER_OF_TANK = 5L;
	private final static Long AN_INVALID_CATEGORY = -1L;

	@BeforeEach
	void setup() {
		subject = new Inventory();
	}

	@Test
	void whenInventoryIsCreated_thenItIsEmpty() {
		assertEquals(3L, (long) subject.getOxygenTanks().size());
		assertEquals(0L, (long) subject.getOxygenTanks().get(Categories.E_ID));
		assertEquals(0L, (long) subject.getOxygenTanks().get(Categories.B_ID));
		assertEquals(0L, (long) subject.getOxygenTanks().get(Categories.A_ID));
	}

	@Test
	void whenOxygenTankIsAddedToStoredTank_thenInventoryIsUpdated() {
		subject.addOxygenTanks(Categories.E_ID, 2L);

		Long quantity = subject.getOxygenTanks().get(Categories.E_ID);

		assertEquals(2L, (long) quantity);
	}

	@Test
	void getStoredTanksByCategoryId_shouldReturnTheCorrectNumberOfStoredTankForACategory() {
		fillInventory();

		assertEquals(10L, (long) subject.getOxygenTanks().get(Categories.A_ID));
		assertEquals(20L, (long) subject.getOxygenTanks().get(Categories.B_ID));
		assertEquals(30L, (long) subject.getOxygenTanks().get(Categories.E_ID));
	}

	@Test
	void getInUseTanksByCategoryId_shouldReturnTheCorrectNumberOfInUseTankForACategory() {
		fillInventory();

		assertEquals(1L, (long) subject.getOxygenTanks().get(Categories.A_ID));
		assertEquals(2L, (long) subject.getOxygenTanks().get(Categories.B_ID));
		assertEquals(3L, (long) subject.getOxygenTanks().get(Categories.E_ID));
	}

	@Test
	void StoredTank_shouldContainAllTheCategory() {
		assertTrue(subject.getOxygenTanks().entrySet().stream().anyMatch(tank -> tank.getKey() == QualityConstants.NEBULA_ID));
		assertTrue(subject.getOxygenTanks().entrySet().stream().anyMatch(tank -> tank.getKey() == QualityConstants.SUPERGIANT_ID));
		assertTrue(subject.getOxygenTanks().entrySet().stream().anyMatch(tank -> tank.getKey() == QualityConstants.SUPERNOVA_ID));
	}

	@Test
	void getStoredTanks_shouldReturnAllTheStoredTank() {
		fillInventory();

		assertTrue(subject.getOxygenTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 10L));
		assertTrue(subject.getOxygenTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 20L));
		assertTrue(subject.getOxygenTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 30L));
	}

	@Test
	void getInUseTanks_shouldReturnAllTheStoredTank() {
		fillInventory();

		assertTrue(subject.getOxygenTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 1L));
		assertTrue(subject.getOxygenTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 2L));
		assertTrue(subject.getOxygenTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 3L));
	}

	private void fillInventory() {
		subject.addOxygenTanks(Categories.A_ID, 10L);
		subject.addOxygenTanks(Categories.B_ID, 20L);
		subject.addOxygenTanks(Categories.E_ID, 30L);
	}
}