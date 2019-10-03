package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.constants.OxygenConstants.Categories;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		assertEquals(0L, (long) subject.getNotInUseTanksByCategoryId(Categories.E_ID));
		assertEquals(0L, (long) subject.getNotInUseTanksByCategoryId(Categories.B_ID));
		assertEquals(0L, (long) subject.getNotInUseTanksByCategoryId(Categories.A_ID));
		assertEquals(0L, (long) subject.getInUseTanksByCategoryId(Categories.E_ID));
		assertEquals(0L, (long) subject.getInUseTanksByCategoryId(Categories.B_ID));
		assertEquals(0L, (long) subject.getInUseTanksByCategoryId(Categories.A_ID));
	}

	@Test
	void whenOxygenTankIsAddedToStoredTank_thenInventoryIsUpdated() {
		subject.replaceNotInUseTanks(Categories.E_ID, 2L);

		assertEquals(Long.valueOf(2L), subject.getNotInUseTanksByCategoryId(Categories.E_ID));
	}

	@Test
	void getNotInUseTanksByCategoryId_shouldReturnTheCorrectNumberOfStoredTankForACategory() {
		fillInventory();

		assertEquals(Long.valueOf(10L), subject.getNotInUseTanksByCategoryId(Categories.A_ID));
		assertEquals(Long.valueOf(20L), subject.getNotInUseTanksByCategoryId(Categories.B_ID));
		assertEquals(Long.valueOf(30L), subject.getNotInUseTanksByCategoryId(Categories.E_ID));
	}

	@Test
	void getInUseTanksByCategoryId_shouldReturnTheCorrectNumberOfInUseTankForACategory() {
		fillInventory();

		assertEquals(Long.valueOf(1L), subject.getInUseTanksByCategoryId(Categories.A_ID));
		assertEquals(Long.valueOf(2L), subject.getInUseTanksByCategoryId(Categories.B_ID));
		assertEquals(Long.valueOf(3L), subject.getInUseTanksByCategoryId(Categories.E_ID));
	}

	private void fillInventory() {
		subject.replaceNotInUseTanks(Categories.A_ID, 10L);
		subject.replaceNotInUseTanks(Categories.B_ID, 20L);
		subject.replaceNotInUseTanks(Categories.E_ID, 30L);
		subject.replaceInUseTanks(Categories.A_ID, 1L);
		subject.replaceInUseTanks(Categories.B_ID, 2L);
		subject.replaceInUseTanks(Categories.E_ID, 3L);
	}
}