package ca.ulaval.glo4002.booking.entities.oxygen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.constants.OxygenConstants.Categories;
import ca.ulaval.glo4002.booking.constants.QualityConstants;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;

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
		assertEquals(0L, (long) subject.getStoredTanksByCategoryId(Categories.E_ID));
		assertEquals(0L, (long) subject.getStoredTanksByCategoryId(Categories.B_ID));
		assertEquals(0L, (long) subject.getStoredTanksByCategoryId(Categories.A_ID));
	}

	@Test
	void whenOxygenTankIsAddedToStoredTank_thenInventoryIsUpdated() {
		subject.replaceStoredTanks(Categories.E_ID, 2L);

		Long storedTanksQuantity = subject.getStoredTanksByCategoryId(Categories.E_ID);

		assertEquals(2L, (long) storedTanksQuantity);
	}

	@Test
	void whenOxygenTankIsAddedToInUseTank_thenInventoryIsUpdated() {
		subject.replaceInUseTanks(Categories.E_ID, 2L);

		Long storedTanksQuantity = subject.getInUseTanksByCategoryId(Categories.E_ID);

		assertEquals(2L, (long) storedTanksQuantity);
	}

	@Test
	void getStoredTanksByCategoryId_shouldReturnTheCorrectNumberOfStoredTankForACategory() {
		fillInventory();

		assertEquals(10L, (long) subject.getStoredTanksByCategoryId(Categories.A_ID));
		assertEquals(20L, (long) subject.getStoredTanksByCategoryId(Categories.B_ID));
		assertEquals(30L, (long) subject.getStoredTanksByCategoryId(Categories.E_ID));
	}

	@Test
	void getInUseTanksByCategoryId_shouldReturnTheCorrectNumberOfInUseTankForACategory() {
		fillInventory();

		assertEquals(1L, (long) subject.getInUseTanksByCategoryId(Categories.A_ID));
		assertEquals(2L, (long) subject.getInUseTanksByCategoryId(Categories.B_ID));
		assertEquals(3L, (long) subject.getInUseTanksByCategoryId(Categories.E_ID));
	}

	@Test
	void StoredTank_shouldContainAllTheCategory() {
		assertTrue(subject.getStoredTanks().entrySet().stream()
				.anyMatch(tank -> tank.getKey() == QualityConstants.NEBULA_ID));
		assertTrue(subject.getStoredTanks().entrySet().stream()
				.anyMatch(tank -> tank.getKey() == QualityConstants.SUPERGIANT_ID));
		assertTrue(subject.getStoredTanks().entrySet().stream()
				.anyMatch(tank -> tank.getKey() == QualityConstants.SUPERNOVA_ID));
	}

	@Test
	void getStoredTanks_shouldReturnAllTheStoredTank() {
		fillInventory();

		assertTrue(subject.getStoredTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 10L));
		assertTrue(subject.getStoredTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 20L));
		assertTrue(subject.getStoredTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 30L));
	}

	@Test
	void getInUseTanks_shouldReturnAllTheStoredTank() {
		fillInventory();

		assertTrue(subject.getInUseTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 1L));
		assertTrue(subject.getInUseTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 2L));
		assertTrue(subject.getInUseTanks().entrySet().stream().anyMatch(tank -> tank.getValue() == 3L));
	}

	private void fillInventory() {
		subject.replaceStoredTanks(Categories.A_ID, 10L);
		subject.replaceStoredTanks(Categories.B_ID, 20L);
		subject.replaceStoredTanks(Categories.E_ID, 30L);
		subject.replaceInUseTanks(Categories.A_ID, 1L);
		subject.replaceInUseTanks(Categories.B_ID, 2L);
		subject.replaceInUseTanks(Categories.E_ID, 3L);
	}
}