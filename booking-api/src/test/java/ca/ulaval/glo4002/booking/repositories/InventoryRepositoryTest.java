package ca.ulaval.glo4002.booking.repositories;

import org.junit.jupiter.api.BeforeEach;

class InventoryRepositoryTest {

	private InventoryRepositoryContext context;
	private InventoryRepository subject;

	@BeforeEach
	public void setUp() {
		context = new InventoryRepositoryContext();
		subject = new InventoryRepositoryImpl(context.entityManager);
	}

	// TODO : OXY : Solve this test
	/*
	@Test
	public void findAll_shouldReturnCorrectInventory() {
		List<InventoryEntity> inventory = new ArrayList<>();
		subject.findAll().forEach(inventory::add);

		assertEquals(2, inventory.size());
		assertTrue(inventory.contains(context.AN_INVENTORY));
		assertTrue(inventory.contains(context.AN_OTHER_INVENTORY));
	}
	*/

	// TODO : OXY : Solve this test
	/*
	@Test
	public void save_shouldSaveInventory() {
		context.setUpEntityManagerForSave();
		subject.save(context.NON_EXISTENT_INVENTORY);
		List<InventoryEntity> inventory = new ArrayList<>();
		subject.findAll().forEach(inventory::add);

		assertTrue(inventory.contains(context.NON_EXISTENT_INVENTORY));
	}
	*/
}
