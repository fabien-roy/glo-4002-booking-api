package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO  OXY : not sure if these kind of test are part of unit testing, for example if Repo where a BD we should'nt test this in unit testing

class InMemoryOxygenInventoryRepositoryTest {

    private OxygenTankInventoryRepository inMemoryRepository;
    private OxygenInventory inventory;

    @BeforeEach
    void initRepositoryTest() {
        this.inMemoryRepository = new InMemoryOxygenTankInventoryRepository();
        inventory = new OxygenInventory();
        inMemoryRepository.setInventory(inventory);
    }

    @Test
    void getInventory_shouldReturnInventory() {
        OxygenInventory getInventory = inMemoryRepository.getInventory();

        assertEquals(inventory, getInventory);
    }

    @Test
    void getInventory_shouldReturnNewInventory_whenNoneWasCreated() {
        OxygenInventory inventory = inMemoryRepository.getInventory();

        assertNotNull(inventory);
        assertTrue(inventory instanceof OxygenInventory);
    }

    @Test
    void setInventory_shouldSetInventory() {
        OxygenInventory newInventory = new OxygenInventory();

        inMemoryRepository.setInventory(newInventory);
        OxygenInventory getInventory = inMemoryRepository.getInventory();

        assertEquals(newInventory, getInventory);
        assertNotEquals(inventory, getInventory);
    }
}
