package ca.ulaval.glo4002.booking.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;

public class InventoryServiceTest {

	private InventoryServiceContext context;

	// TODO : Mock Inventory ? start below :

	@BeforeEach
	public void setUp() {
		context = new InventoryServiceContext();
	}

	// TODO : Solve this test
	@Test
	void whenOxygenTankIsRequestedAndInventoryIsInSurplus_thenTankInUseIsUpdated() {
		context.subject.addTank(context.anOxygenTank.getOxygenTankCategory().getId(), 20L);

		context.subject.requestOxygenTanks(context.anOxygenTank);
		Long inUseTanks = context.anInventory
				.getInUseTanksByCategoryId(context.anOxygenTank.getOxygenTankCategory().getId());
		Long expectedInUseTanks = context.anOxygenTank.getOxygenTankCategory().getProduction().getProducedTanks();

		assertEquals(expectedInUseTanks, inUseTanks);
	}

	// TODO : Solve test
	@Test
	void whenOxygenTankIsRequestedAndInventoryHaveAPortionInSurplus_thenTankInUseIsUpdated() {
		context.subject.addTank(context.anOxygenTank.getOxygenTankCategory().getId(), 3L);

		context.subject.requestOxygenTanks(context.anOxygenTank);
		/*
		 * Long inUseTanks =
		 * context.anInventory.getInUseTanksByCategoryId(context.anOxygenTank.
		 * getOxygenTankCategory().getId()); Long expectedInUseTanks =
		 * context.anOxygenTank.getOxygenTankCategory().getProduction().getProducedTanks
		 * ();
		 */

		assertEquals(3, (long) context.anInventory.getInUseTanksByCategoryId(OxygenConstants.Categories.E_ID));
	}

	@Test
	void whenOxygenTankIsAdded_thenInventoryOfStoredTankIsUpdated() {
		context.subject.addTank(context.anOxygenTank.getOxygenTankCategory().getId(), 5L);
	}

	@Test
	void whenGetIsCalled_thenShouldReturnAnTheInventory() {
		context.subject.addTank(context.anOxygenTank.getOxygenTankCategory().getId(), 5L);

	}

	@Test
	void get_shouldReturnTheInventory() {
		context.subject.get();
	}

	@Test
	void save_shouldSaveTheInventory() {

	}

	@Test
	void whenGetInventoryByCategoryIsIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
		// TODO Do this test
	}

	// TODO : Solve this test
	/*
	 * @Test void
	 * whenOxygenTankIsRequestedAndThereIsNoSurplus_thenShouldReturnTheQuantityRequested
	 * () { context.subject.requestOxygenTanks(context.anOxygenTank);
	 * 
	 * assertEquals(10, (long) context.anInventory.?); }
	 */
}
