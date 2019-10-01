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
}
