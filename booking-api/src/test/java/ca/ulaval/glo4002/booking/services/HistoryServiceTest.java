package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.report.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HistoryServiceTest {

    private HistoryService subject;
    private HistoryServiceContext context;

    @BeforeEach
    public void setUp() {
        context = new HistoryServiceContext();
        subject = new HistoryServiceImpl(context.oxygenTankService);
    }

    @Test
    public void get_shouldGetCorrectHistory() {
        History history = subject.get();

        assertEquals(2, history.getProducedOxygenTanks().size());
        assertEquals(2, history.getRequestedOxygenTanks().size());
        assertTrue(history.getRequestedOxygenTanks().get(context.anOxygenTank.getRequestDate()).stream().anyMatch(oxygenTank -> oxygenTank.getId().equals(context.anOxygenTank.getId())));
        assertTrue(history.getRequestedOxygenTanks().get(context.anotherOxygenTank.getRequestDate()).stream().anyMatch(oxygenTank -> oxygenTank.getId().equals(context.anotherOxygenTank.getId())));
        assertTrue(history.getProducedOxygenTanks().get(context.anOxygenTank.getReadyDate()).stream().anyMatch(oxygenTank -> oxygenTank.getId().equals(context.anOxygenTank.getId())));
        assertTrue(history.getProducedOxygenTanks().get(context.anotherOxygenTank.getReadyDate()).stream().anyMatch(oxygenTank -> oxygenTank.getId().equals(context.anotherOxygenTank.getId())));
    }

    @Test
    public void get_shouldGetCorrectHistory_whenThereIsNoTank() {
        context.setUpRepositoryForNoTank();

        History history = subject.get();

        assertEquals(0, history.getProducedOxygenTanks().size());
        assertEquals(0, history.getRequestedOxygenTanks().size());
    }
}
