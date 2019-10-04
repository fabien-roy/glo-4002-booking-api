package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryServiceTest {

	private InventoryService subject;
	private InventoryServiceContext context;

	// TODO : Mock Inventory ? start below :

	@BeforeEach
	public void setUp() {
		context = new InventoryServiceContext();
		subject = new InventoryServiceImpl(context.repository, context.inventoryItemService);
	}

	@Test
	void whenOxygenTankIsRequested_thenTheyAreAddedToTheInventory(){
		subject.requestOxygenTanks(context.anOxygenTank);

		Long producedTanks = context.anOxygenTank.getCategory().getProduction().getProducedTanks();
		assertEquals(producedTanks, context.anInventory.getInUseTanksByCategoryId(context.anOxygenTank.getCategory().getId()));
	}

	// TODO : OXY : Solve this test
	@Test
	void whenOxygenTankIsRequestedAndInventoryIsInSurplus_thenTankInUseIsUpdated() {
		//subjext.addTank(context.anOxygenTank.getCategory().getId(), 20L);

		subject.requestOxygenTanks(context.anOxygenTank);
		Long inUseTanks = context.anInventory
				.getInUseTanksByCategoryId(context.anOxygenTank.getCategory().getId());
		Long expectedInUseTanks = context.anOxygenTank.getCategory().getProduction().getProducedTanks();

		assertEquals(expectedInUseTanks, inUseTanks);
	}

	// TODO : OXY : Solve test
	@Test
	void whenOxygenTankIsRequestedAndInventoryHaveAPortionInSurplus_thenTankInUseIsUpdated() {
		//subjext.addTank(context.anOxygenTank.getCategory().getId(), 3L);

		subject.requestOxygenTanks(context.anOxygenTank);
		Long inUseTanks = context.anInventory.getInUseTanksByCategoryId(context.anOxygenTank.getCategory().getId());
        Long expectedInUseTanks = context.anOxygenTank.getCategory().getProduction().getProducedTanks();

		assertEquals(3, (long) context.anInventory.getInUseTanksByCategoryId(OxygenConstants.Categories.E_ID));
	}

	// TODO Do/update this test (Or delete if we don't need it anymore)
	@Test
	void whenOxygenTankIsRequestedAndInventoryHaveAPortionInSurplus_thenShouldReturnTheNumberThatRemainsToBeCovered_andTankInUseIsUpdated() {
//        subject.addTankInInventory(Categories.E_ID, 3L);
//        Long numberStillNeeded = subject.requestOxygenTank(Categories.E_ID, 5L);
//
//        assertTrue(numberStillNeeded == (5-3));
//        assertTrue(subject.getTankInUseByCategoryID(Categories.E_ID) == 3);
	}

	// TODO Do/update/solve this test (Or delete if we don't need it anymore)
	@Test
	void whenOxygenTankIsRequestedAndThereIsNoSurplus_thenShouldReturnTheQuantityRequested() {
// We had two version of this test
// version 1 :
//        Long numberStillNeeded = subject.requestOxygenTank(Categories.E_ID, 10L);
//
//        assertTrue(numberStillNeeded == 10L);
// version 2 :
//		context.subject.requestOxygenTanks(context.anOxygenTank);
//		 
//		 assertEquals(10, (long) context.anInventory.?); 
	}

	@Test
	void get_shouldReturnTheInventory() {
		subject.get();
		// TODO Do this test
	}

	@Test
	void save_shouldSaveTheInventory() {
		// TODO Do this test
	}

	// TODO Do/update this test (Or delete if we don't need it anymore)
	@Test
	void whenOxygenTankIsAddedWithWithInvalidCategory_thenShouldThrowOxygenCategoryNotFoundException() {
//        OxygenCategoryNotFoundException thrown = assertThrows(
//                OxygenCategoryNotFoundException.class,
//                () -> subject.addTankInInventory(AN_INVALID_CATEGORY, 20L)
//        );
//
//        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_ERROR, thrown.getMessage());
	}

	// TODO Do/update this test (Or delete if we don't need it anymore)
	@Test
	void whenGetInventoryByCategoryIsIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
//	        OxygenCategoryNotFoundException thrown = assertThrows(
//	                OxygenCategoryNotFoundException.class,
//	                () -> subject.getInventoryByCategoryID(AN_INVALID_CATEGORY)
//	        );
//
//	        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_ERROR, thrown.getMessage());
	}

	// TODO Do/update this test (Or delete if we don't need it anymore)
	@Test
	void whenGetTankInUseByCategoryIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.getTankInUseByCategoryID(InventoryServiceContext.AN_INVALID_CATEGORY)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_ERROR, thrown.getMessage());
	}
}
