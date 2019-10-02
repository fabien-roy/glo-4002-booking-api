package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.report.Inventory;
import ca.ulaval.glo4002.booking.dto.InventoryItemDto;
import ca.ulaval.glo4002.booking.entities.InventoryEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryParserTest {

    private InventoryParser subject;
    private Inventory inventory;
    private Inventory anEmptyInventory;
    private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED = 1L;
    private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED = 2L;
    private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED = 3L;
    private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_A_INUSE = 4L;
    private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_B_INUSE = 5L;
    private static final Long A_VALID_NUMBER_OF_TANK_CATEGORY_E_INUSE = 6L;

    @BeforeEach
    void setup() {
        Map<Long, Long> storedTank = new HashMap<>();
        Map<Long, Long> inUseTank = new HashMap<>();

        storedTank.put(OxygenConstants.Categories.A_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED);
        storedTank.put(OxygenConstants.Categories.B_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED);
        storedTank.put(OxygenConstants.Categories.E_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED);
        inUseTank.put(OxygenConstants.Categories.A_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_A_INUSE);
        inUseTank.put(OxygenConstants.Categories.B_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_B_INUSE);
        inUseTank.put(OxygenConstants.Categories.E_ID, A_VALID_NUMBER_OF_TANK_CATEGORY_E_INUSE);

        anEmptyInventory = new Inventory();
        inventory = new Inventory(storedTank, inUseTank);
        subject = new InventoryParser();
    }

    @Test
    void whenParsingEntity_InventoryShouldBeValid() {
        InventoryEntity entity = subject.toEntity(inventory);

        Inventory parsedInventory = subject.parseEntity(entity);

        assertEquals(entity.getInventoryItems().get(0).getQuantityInUse(),
                parsedInventory.getInUseTanksByCategoryId(OxygenConstants.Categories.E_ID));
        assertEquals(entity.getInventoryItems().get(1).getQuantityInUse(),
                parsedInventory.getInUseTanksByCategoryId(OxygenConstants.Categories.B_ID));
        assertEquals(entity.getInventoryItems().get(2).getQuantityInUse(),
                parsedInventory.getInUseTanksByCategoryId(OxygenConstants.Categories.A_ID));

        assertEquals(entity.getInventoryItems().get(0).getQuantityStored(),
                parsedInventory.getStoredTanksByCategoryId(OxygenConstants.Categories.E_ID));
        assertEquals(entity.getInventoryItems().get(1).getQuantityStored(),
                parsedInventory.getStoredTanksByCategoryId(OxygenConstants.Categories.B_ID));
        assertEquals(entity.getInventoryItems().get(2).getQuantityStored(),
                parsedInventory.getStoredTanksByCategoryId(OxygenConstants.Categories.A_ID));
    }

    @Test
    void whenParsingToEntity_entityShouldBeValid() {
        InventoryEntity entity = subject.toEntity(inventory);

        assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_E_STORED,
                entity.getInventoryItems().get(0).getQuantityStored());
        assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_B_STORED,
                entity.getInventoryItems().get(1).getQuantityStored());
        assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_A_STORED,
                entity.getInventoryItems().get(2).getQuantityStored());

        assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_E_INUSE,
                entity.getInventoryItems().get(0).getQuantityInUse());
        assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_B_INUSE,
                entity.getInventoryItems().get(1).getQuantityInUse());
        assertEquals(A_VALID_NUMBER_OF_TANK_CATEGORY_A_INUSE,
                entity.getInventoryItems().get(2).getQuantityInUse());
    }

    @Test
    void whenParsingToDto_dtoShouldBeValid() {
        List<InventoryItemDto> dtos = subject.toDto(inventory);

        assertEquals("E", dtos.get(0).gradeTankOxygen);
        assertEquals(inventory.getStoredTanksByCategoryId(OxygenConstants.Categories.E_ID), dtos.get(0).quantity);
        assertEquals("B", dtos.get(1).gradeTankOxygen);
        assertEquals(inventory.getStoredTanksByCategoryId(OxygenConstants.Categories.B_ID), dtos.get(1).quantity);
        assertEquals("A", dtos.get(2).gradeTankOxygen);
        assertEquals(inventory.getStoredTanksByCategoryId(OxygenConstants.Categories.A_ID), dtos.get(2).quantity);
    }

    @Test
    void whenParsingToDto_andAllCategoryInInventoryIsEmpty_thenItShouldNotBeAddedToTheDto() {
        List<InventoryItemDto> dtos = subject.toDto(anEmptyInventory);

        assertEquals(0, dtos.size());
    }

    @Test
    void whenParsingToDto_andOneCategoryInTheInventoryIsEmpty_thenItShouldNotBeAddedToTheDto() {

    }

    @Test
    void whenParsingDto_ShouldReturnUnusedMethodException() {
        List<InventoryItemDto> dtos = new ArrayList<>();

        UnusedMethodException thrown = assertThrows(
                UnusedMethodException.class,
                () -> subject.parseDto(dtos)
        );

        assertEquals(ExceptionConstants.UNUSED_METHOD_ERROR, thrown.getMessage());
    }


    // TODO : Those tests are copied from InventoryTest (which was a bad place to be) Should be in OxygenTankService ?????
    // not sure they need to be here.
    /*
    @Test
    void whenOxygenTankIsAddedWithWithInvalidCategory_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.replaceStoredTanks(AN_INVALID_CATEGORY, 20L)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenGetInventoryByCategoryIsIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.getStoredTanksByCategoryId(AN_INVALID_CATEGORY)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenGetTankInUseByCategoryIsIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.getInUseTanksByCategoryId(AN_INVALID_CATEGORY)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }
    */
}
