package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.constants.OxygenConstants.Categories;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {

    private Inventory subject;
    private final static Long AN_INVALID_CATEGORY = -1L;

    @BeforeEach
    void setup() {
        subject = new Inventory();
    }

    @Test
    void whenInventoryIsCreated_thenItIsEmpty() {
        assertEquals(0L, (long) subject.getStoredTanksByCategoryId(Categories.E_ID));
        assertEquals(0L, (long) subject.getStoredTanksByCategoryId(Categories.B_ID));
        assertEquals(0L, (long) subject.getStoredTanksByCategoryId(Categories.A_ID));
    }

    @Test
    void whenOxygenTankIsAdded_thenInventoryIsUpdated() {
        subject.replaceStoredTanks(Categories.E_ID, 2L);

        Long storedTanksQuantity = subject.getStoredTanksByCategoryId(Categories.E_ID);

        assertEquals(2L, (long) storedTanksQuantity);
    }

    /*
    @Test
    void whenOxygenTankIsRequestedAndInventoryHaveAPortionInSurplus_thenShouldReturnTheNumberThatRemainsToBeCovered_andTankInUseIsUpdated() {
        subject.addTankInInventory(Categories.E_ID, 3L);
        Long numberStillNeeded = subject.requestOxygenTank(Categories.E_ID, 5L);

        assertTrue(numberStillNeeded == (5-3));
        assertTrue(subject.getTankInUseByCategoryID(Categories.E_ID) == 3);
    }

    @Test
    void whenOxygenTankIsRequestedAndThereIsNoSurplus_thenShouldReturnTheQuantityRequested() {
        Long numberStillNeeded = subject.requestOxygenTank(Categories.E_ID, 10L);

        assertTrue(numberStillNeeded == 10L);
    }

    @Test
    void whenOxygenTankIsAddedWithWithInvalidCategory_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.addTankInInventory(AN_INVALID_CATEGORY, 20L)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void whenGetInventoryByCategoryIsIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.getInventoryByCategoryID(AN_INVALID_CATEGORY)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void whenGetTankInUseByCategoryIsIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.getTankInUseByCategoryID(AN_INVALID_CATEGORY)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_ERROR, thrown.getMessage());
    }*/
}
