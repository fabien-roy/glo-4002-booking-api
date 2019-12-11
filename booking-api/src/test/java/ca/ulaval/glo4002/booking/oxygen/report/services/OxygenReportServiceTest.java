package ca.ulaval.glo4002.booking.oxygen.report.services;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.report.rest.mappers.OxygenReportMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OxygenReportServiceTest {

    private OxygenReportService service;
    private OxygenInventoryRepository inventoryRepository;
    private OxygenHistoryRepository historyRepository;
    private OxygenReportMapper mapper;

    @BeforeEach
    void setUpService() {
        inventoryRepository = mock(OxygenInventoryRepository.class);
        historyRepository = mock(OxygenHistoryRepository.class);
        mapper = mock(OxygenReportMapper.class);

        service = new OxygenReportService(inventoryRepository, historyRepository, mapper);
    }

    @Test
    void getOxygenReport_shouldGetInventory() {
        service.getOxygenReport();

        verify(inventoryRepository).getInventory();
    }

    @Test
    void getOxygenReport_shouldGetHistory() {
        service.getOxygenReport();

        verify(historyRepository).getHistory();
    }

    @Test
    void getOxygenReport_shouldMapOxygenReport() {
        service.getOxygenReport();

        verify(mapper).toResponse(any(), any());
    }

    @Test
    void getOxygenReport_shouldMapOxygenReportWithInventory() {
        OxygenInventory expectedInventory = mock(OxygenInventory.class);
        when(inventoryRepository.getInventory()).thenReturn(expectedInventory);

        service.getOxygenReport();

        verify(mapper).toResponse(eq(expectedInventory), any());
    }

    @Test
    void getOxygenReport_shouldMapOxygenReportWithHistory() {
        OxygenHistory expectedHistory = mock(OxygenHistory.class);
        when(historyRepository.getHistory()).thenReturn(expectedHistory);

        service.getOxygenReport();

        verify(mapper).toResponse(any(), eq(expectedHistory));
    }
}