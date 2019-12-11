package ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTank;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class InMemoryOxygenInventoryRepositoryTest {

	private OxygenInventoryRepository repository;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryOxygenInventoryRepository();
	}

	@Test
	void updateInventory_shouldUpdateInventory() {
		OxygenInventory expectedInventory = new OxygenInventory();

		repository.updateInventory(expectedInventory);
		OxygenInventory inventory = repository.getInventory();

		assertEquals(expectedInventory, inventory);
	}

	@Test
	void findAll_shouldReturnAllTanks() {
	    OxygenTank expectedOxygenTank = mock(OxygenTank.class);
		OxygenInventory inventory = new OxygenInventory();
		inventory.addTanksToInventory(OxygenCategories.E, Collections.singletonList(expectedOxygenTank));

		repository.updateInventory(inventory);
		List<OxygenTank> oxygenTanks = repository.findAll();

		assertTrue(oxygenTanks.stream().allMatch(expectedOxygenTank::equals));
	}
}
