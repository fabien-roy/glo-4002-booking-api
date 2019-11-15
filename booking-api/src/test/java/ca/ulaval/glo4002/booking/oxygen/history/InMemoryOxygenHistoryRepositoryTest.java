package ca.ulaval.glo4002.booking.oxygen.history;

import ca.ulaval.glo4002.booking.oxygen.history.InMemoryOxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryRepository;
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