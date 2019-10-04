package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.entities.InventoryItemEntity;
import ca.ulaval.glo4002.booking.exceptions.report.InventoryItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ConstantException;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryItemRepositoryTest {

    private InventoryItemRepository subject;
    private InventoryItemRepositoryContext context;

    @BeforeEach
    void setup() {
        context = new InventoryItemRepositoryContext();
        subject = new InventoryItemRepositoryImpl(context.entityManager);
    }

    @Test
    void findById_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        InventoryItemNotFoundException thrown = assertThrows(
                InventoryItemNotFoundException.class,
                () -> subject.findById(OrderRepositoryContext.A_NON_EXISTENT_ORDER_ID)
        );

        assertEquals(ExceptionConstants.InventoryItem.NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    void findById_shouldReturnCorrectInventoryItem() {
        InventoryItemEntity inventoryItem = subject.findById(InventoryItemRepositoryContext.AN_INVENTORYITEM_ID).get();

        assertEquals(context.anInventoryItem, inventoryItem);
    }

    @Test
    void findAll_shouldReturnCorrectInventoryItems() {
        List<InventoryItemEntity> inventoryItems = new ArrayList<>();

        subject.findAll().forEach(inventoryItems::add);

        assertEquals(2, inventoryItems.size());
        assertTrue(inventoryItems.contains(context.anInventoryItem));
        assertTrue(inventoryItems.contains(context.anotherInventoryItem));
    }

    @Test
    void save_shouldSaveInventoryItems() {
        context.setupEntityManagerForSave();

        subject.save(context.anInventoryItem);
        InventoryItemEntity inventoryItem =
                subject.findById(InventoryItemRepositoryContext.A_NON_EXISTENT_INVENTORYITEM_ID).get();

        assertEquals(context.aNonExistentInventoryItem, inventoryItem);
    }

}
