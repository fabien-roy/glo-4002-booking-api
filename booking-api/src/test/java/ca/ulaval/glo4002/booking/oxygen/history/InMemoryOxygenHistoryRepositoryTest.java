package ca.ulaval.glo4002.booking.oxygen.history;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryOxygenHistoryRepositoryTest {

    private OxygenHistoryRepository repository;

    @BeforeEach
    void setUpRepository() {
        repository = new InMemoryOxygenHistoryRepository();
    }

    @Test
    void setInventory_shouldSetInventory() {
        OxygenHistory expectedHistory = new OxygenHistory();

        repository.setHistory(expectedHistory);
        OxygenHistory history = repository.getHistory();

        assertEquals(expectedHistory, history);
    }
}