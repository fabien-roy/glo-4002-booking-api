package ca.ulaval.glo4002.booking.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryOxygenTankInventoryRepositoryTest {

	private OxygenTankInventoryRepository subject;

	@BeforeEach
	void initRepositoryTest() {
		this.subject = new InMemoryOxygenTankInventoryRepository();
	}

	@Test
	void getInventory_shouldReturnInventory() {
		// TODO this test
	}

	@Test
	void getInventory_shouldReturnNewInventory_whenNoneWasCreated() {
		// TODO this test
	}

	@Test
	void setInventory_shouldSetInventory() {
		// TODO this test
	}
}
