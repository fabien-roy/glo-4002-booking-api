package ca.ulaval.glo4002.booking.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;

public class OxygenTankInventoryTest {

    private OxygenTankInventory inventory;
    private OxygenTankInventory emptyInventory;

    private OxygenTank mockedTankCategoryA;
    private OxygenTank mockedTankCategoryB;
    private OxygenTank mockedTankCategoryE;

    private final Integer CATEGORY_A_QUANTITY = 25;
    private final Integer CATEGORY_B_QUANTITY = 12;
    private final Integer CATEGORY_E_QUANTITY = 5;

    @BeforeEach
    void setupOxygenTankInventory() {
        inventory = new OxygenTankInventory();

        mockedTankCategoryA = mock(OxygenTank.class);
        mockedTankCategoryB = mock(OxygenTank.class);
        mockedTankCategoryE = mock(OxygenTank.class);

        inventory.addTankToInventory(OxygenTankCategory.CATEGORY_A, Collections.nCopies(CATEGORY_A_QUANTITY, mockedTankCategoryA));
        inventory.addTankToInventory(OxygenTankCategory.CATEGORY_B, Collections.nCopies(CATEGORY_B_QUANTITY, mockedTankCategoryB));
        inventory.addTankToInventory(OxygenTankCategory.CATEGORY_E, Collections.nCopies(CATEGORY_E_QUANTITY, mockedTankCategoryE));
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
        Integer addedQuantity = 5;

        inventory.addTankToInventory(OxygenTankCategory.CATEGORY_A, Collections.nCopies(addedQuantity, mockedTankCategoryA));
        Integer currentQuantity = inventory.getNotInUseQuantityByCategory(OxygenTankCategory.CATEGORY_A);

        assertTrue(currentQuantity == CATEGORY_A_QUANTITY + addedQuantity);
    }

    @Test
    void requestTank_updateQuantityInUse() {
        Integer requestQuantity = 10;

        inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_B, requestQuantity);
        Integer currentQuantity = inventory.getInUseQuantityByCategory(OxygenTankCategory.CATEGORY_B);

        assertTrue(currentQuantity == requestQuantity);
    }

    @Test
    void requestTank_updateQuantityNotInUse() {
        Integer requestQuantity = 10;

        inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_B, requestQuantity);
        Integer currentQuantity = inventory.getNotInUseQuantityByCategory(OxygenTankCategory.CATEGORY_B);

        assertTrue(currentQuantity == CATEGORY_B_QUANTITY - requestQuantity);
    }

    @Test
    void whenEnoughTankNotInUse_requestTankShouldReturnZero() {
        Integer requestQuantity = 5;

        Integer quantityNeeded = inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_E, requestQuantity);

        assertTrue(quantityNeeded == 0);
    }

    @Test
    void whenNotEnoughNotInUseTank_requestTankShouldReturnTheNumberStillNeededToProduce() {
        Integer requestQuantity = 30;

        Integer quantity = inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_A, requestQuantity);

        assertTrue(quantity == Math.abs(CATEGORY_A_QUANTITY - requestQuantity));
    }
}