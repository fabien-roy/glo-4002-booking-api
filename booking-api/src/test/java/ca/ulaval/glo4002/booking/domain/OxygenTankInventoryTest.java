package ca.ulaval.glo4002.booking.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

import static org.junit.jupiter.api.Assertions.*;

public class OxygenTankInventoryTest {

    private OxygenTankInventory inventory;
    OxygenTankInventory emptyInventory;

    private final Long CATEGORY_A_QUANTITY = 25L;
    private final Long CATEGORY_B_QUANTITY = 12L;
    private final Long CATEGORY_E_QUANTITY = 5L;

    @BeforeEach
    void setupOxygenTankInventory() {
        inventory = new OxygenTankInventory();

        inventory.addTankToInventory(OxygenTankCategory.CATEGORY_A, CATEGORY_A_QUANTITY);
        inventory.addTankToInventory(OxygenTankCategory.CATEGORY_B, CATEGORY_B_QUANTITY);
        inventory.addTankToInventory(OxygenTankCategory.CATEGORY_E, CATEGORY_E_QUANTITY);
    }

    @Test
    void WhenCreated_notInUseTankInInventoryIsEmpty() {
        emptyInventory = new OxygenTankInventory();

        assertTrue(emptyInventory.getNotInUseQuantityByCategory(OxygenTankCategory.CATEGORY_A) == 0);
        assertTrue(emptyInventory.getNotInUseQuantityByCategory(OxygenTankCategory.CATEGORY_A) == 0);
        assertTrue(emptyInventory.getNotInUseQuantityByCategory(OxygenTankCategory.CATEGORY_A) == 0);
    }

    @Test
    void whenCreated_inUseTankInInventoryIsEmpty() {
        emptyInventory = new OxygenTankInventory();

        assertTrue(inventory.getInUseQuantityByCategory(OxygenTankCategory.CATEGORY_A) == 0);
        assertTrue(inventory.getInUseQuantityByCategory(OxygenTankCategory.CATEGORY_B) == 0);
        assertTrue(inventory.getInUseQuantityByCategory(OxygenTankCategory.CATEGORY_E) == 0);
    }

    @Test
    void addTankToInventory_updateNumberOfTankInNotInUse() {
        Long addedQuantity = 5L;

        inventory.addTankToInventory(OxygenTankCategory.CATEGORY_A, addedQuantity);
        Long currentQuantity = inventory.getNotInUseQuantityByCategory(OxygenTankCategory.CATEGORY_A);

        assertTrue(currentQuantity == CATEGORY_A_QUANTITY + addedQuantity);
    }

    @Test
    void requestTank_updateQuantityInUse() {
        Long requestQuantity = 10L;

        inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_B, requestQuantity);
        Long currentQuantity = inventory.getInUseQuantityByCategory(OxygenTankCategory.CATEGORY_B);

        assertTrue(currentQuantity == requestQuantity);
    }

    @Test
    void requestTank_updateQuantityNotInUse() {
        Long requestQuantity = 10L;

        inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_B, requestQuantity);
        Long currentQuantity = inventory.getNotInUseQuantityByCategory(OxygenTankCategory.CATEGORY_B);

        assertTrue(currentQuantity == CATEGORY_B_QUANTITY - requestQuantity);
    }

    @Test
    void whenEnoughTankNotInUse_requestTankShouldReturnZero() {
        Long requestQuantity = 5L;

        Long quantityNeeded = inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_E, requestQuantity);

        assertTrue(quantityNeeded == 0);
    }

    @Test
    void whenNotEnoughNotInUseTank_requestTankShouldReturnTheNumberStillNeededToProduce() {
        Long requestQuantity = 30L;

        Long quantity = inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_A, requestQuantity);

        assertTrue(quantity == Math.abs(CATEGORY_A_QUANTITY - requestQuantity));
    }
}
