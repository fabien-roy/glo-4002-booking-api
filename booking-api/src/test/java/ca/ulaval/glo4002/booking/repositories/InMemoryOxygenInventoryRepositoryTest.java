package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// TODO  OXY : not sure if these kind of test are part of unit testing, for example if Repo where a BD we should'nt test this in unit testing

class InMemoryOxygenInventoryRepositoryTest {

    private OxygenInventoryRepository inMemoryRepository;
    private OxygenInventory inventory;

    @BeforeEach
    void setupRepositoryTest() {
        this.inMemoryRepository = new InMemoryOxygenInventoryRepository();
        inventory = new OxygenInventory();
        inMemoryRepository.setInventory(inventory);
    }

    @Test
    void getInventory_shouldReturnInventory() {
        OxygenInventory getInventory = inMemoryRepository.getInventory();

        assertEquals(inventory, getInventory);
    }

    @Test
    void getInventory_shouldReturnInventory_whenOneWasPassedAtConstruction() {
        OxygenInventory newInventory = new OxygenInventory();
        InMemoryOxygenInventoryRepository newInMemoryOxygenTankInventoryRepository= new InMemoryOxygenInventoryRepository(newInventory);

        assertEquals(newInventory, newInMemoryOxygenTankInventoryRepository.getInventory());
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
