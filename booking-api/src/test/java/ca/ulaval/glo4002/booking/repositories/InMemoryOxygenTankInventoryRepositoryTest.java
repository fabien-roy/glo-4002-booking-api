package ca.ulaval.glo4002.booking.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryOxygenTankInventoryRepositoryTest {

	private OxygenTankInventoryRepository inventoryRepository;

	@BeforeEach
	void initRepositoryTest() {
		this.inventoryRepository = new InMemoryOxygenTankInventoryRepository();
	}

	@Test
	void whenGetInventory_inventoryIsReturn() {
		// TODO this test
	}

	@Test
	void whensetInventory_inventoryIsSet() {
		// TODO this test
	}
}
