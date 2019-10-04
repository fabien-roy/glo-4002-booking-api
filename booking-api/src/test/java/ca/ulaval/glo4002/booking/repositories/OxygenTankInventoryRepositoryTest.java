package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.OxygenTankInventoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OxygenTankOxygenTankInventoryRepositoryTest {

	private InventoryRepositoryContext context;
	private OxygenTankInventoryRepository subject;

	@BeforeEach
	public void setUp() {
		context = new InventoryRepositoryContext();
		subject = new OxygenTankInventoryRepositoryImpl(context.entityManager);
	}

	@Test
	public void findAll_shouldReturnCorrectInventory() {
		List<OxygenTankInventoryEntity> inventory = new ArrayList<>();

		subject.findAll().forEach(inventory::add);

		assertEquals(1, inventory.size());
		assertTrue(inventory.contains(context.anInventory));
	}

	@Test
	public void save_shouldSaveInventory() {
		List<OxygenTankInventoryEntity> inventory = new ArrayList<>();
		context.setUpEntityManagerForSave();

		subject.save(context.aNonExistentInventory);
		subject.findAll().forEach(inventory::add);

		assertTrue(inventory.contains(context.aNonExistentInventory));
	}
}
