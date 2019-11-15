package ca.ulaval.glo4002.booking.oxygen.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
