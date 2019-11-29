package ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.InMemoryOxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.OxygenInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryOxygenInventoryRepositoryTest {

	private OxygenInventoryRepository repository;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryOxygenInventoryRepository();
	}

	@Test
	void setInventory_shouldSetInventory() {
		OxygenInventory expectedInventory = new OxygenInventory();

		repository.setInventory(expectedInventory);
		OxygenInventory inventory = repository.getInventory();

		assertEquals(expectedInventory, inventory);
	}
}
