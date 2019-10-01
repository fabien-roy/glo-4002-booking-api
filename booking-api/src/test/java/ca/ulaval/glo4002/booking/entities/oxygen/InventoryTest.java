package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants.Categories;

import ca.ulaval.glo4002.booking.domainObjects.report.Inventory;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory subject;
    private final static Long AN_INVALID_CATEGORY = -1L;

    @BeforeEach
    void setup() {
        subject = new Inventory();
    }

    @Test
    void whenInventoryIsCreated_thenItIsEmpty() {
        assertTrue(subject.getInventoryByCategoryID(Categories.E_ID) == 0L);
        assertTrue(subject.getInventoryByCategoryID(Categories.B_ID) == 0L);
        assertTrue(subject.getInventoryByCategoryID(Categories.A_ID) == 0L);
    }

    @Test
    void whenOxygenTankIsAdded_thenInventoryIsUpdated() {
        subject.addTankInInventory(Categories.E_ID, 2L);

        assertTrue(subject.getInventoryByCategoryID(Categories.E_ID) == 2L);
    }

    @Test
    void whenOxygenTankIsRequestedAndInventoryIsInSurplus_thenTankInUseIsUpdated() {
        subject.addTankInInventory(Categories.E_ID, 20L);
        subject.requestOxygenTank(Categories.E_ID, 5L);

        assertTrue(subject.getTankInUseByCategoryID(Categories.E_ID) == 5L);
    }

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

        assertEquals(ExceptionConstants.OXYGEN_CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenGetInventoryByCategoryIsIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.getInventoryByCategoryID(AN_INVALID_CATEGORY)
        );

        assertEquals(ExceptionConstants.OXYGEN_CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenGetTankInUseByCategoryIsIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.getTankInUseByCategoryID(AN_INVALID_CATEGORY)
        );

        assertEquals(ExceptionConstants.OXYGEN_CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

}
