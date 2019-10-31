package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;
import ca.ulaval.glo4002.booking.repositories.OxygenTankInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class OxygenTankInventoryServiceTest {

    private OxygenTankInventoryService oxygenTankInventoryService;

    @BeforeEach
    void setUpSubject() {
        OxygenTankInventoryRepository repository = mock(OxygenTankInventoryRepository.class);
        OxygenTankFactory factory = mock(OxygenTankFactory.class);

        oxygenTankInventoryService = new OxygenTankInventoryService(repository, factory);
    }

    @Test
    void orderOxygenTank_shouldOrderOxygenTanks() {
        // TODO : OxygenTankInventoryService.orderOxygenTanks tests
    }
}
