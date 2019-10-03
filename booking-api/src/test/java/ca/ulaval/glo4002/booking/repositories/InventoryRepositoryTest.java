package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InventoryRepositoryTest {

	private InventoryRepositoryContext context;
	private InventoryRepository subject;

	@BeforeEach
	public void setUp() {
		context = new InventoryRepositoryContext();
		subject = new InventoryRepositoryImpl(context.entityManager);
	}

	@Test
	public void findAll_shouldReturnCorrectInventory() {
		List<InventoryEntity> inventory = new ArrayList<>();

		subject.findAll().forEach(inventory::add);

		assertEquals(1, inventory.size());
		assertTrue(inventory.contains(context.anInventory));
	}

	@Test
	public void save_shouldSaveInventory() {
		List<InventoryEntity> inventory = new ArrayList<>();
		context.setUpEntityManagerForSave();

		subject.save(context.aNonExistentInventory);
		subject.findAll().forEach(inventory::add);

		assertTrue(inventory.contains(context.aNonExistentInventory));
	}
}
