package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.constants.OxygenConstants.Categories;

import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OxygenTankInventoryTest {

    private OxygenTankInventory subject;
    private final static Long AN_INVALID_CATEGORY = -1L;

    @BeforeEach
    void setup() {
        subject = new OxygenTankInventory();
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
    }

    @Test
    void whenOxygenTankIsAddedWithWithInvalidCategory_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.addTankInInventory(AN_INVALID_CATEGORY, 20L)
        );


    }

}
