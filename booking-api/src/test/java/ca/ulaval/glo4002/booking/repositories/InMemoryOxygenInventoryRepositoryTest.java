package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.oxygen.inventory.InMemoryOxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class InMemoryOxygenInventoryRepositoryTest {

    private OxygenInventoryRepository repository;

    @BeforeEach
    void setUpRepository() {
        repository = new InMemoryOxygenInventoryRepository();
    }

    @Test
    void setInventory_shouldSetInventory() {
        OxygenInventory expectedInventory = new OxygenInventory();

        repository.setInventory(expectedInventory);
        OxygenInventory inventory = repository.getInventory();

        assertEquals(expectedInventory, inventory);
    }
}
