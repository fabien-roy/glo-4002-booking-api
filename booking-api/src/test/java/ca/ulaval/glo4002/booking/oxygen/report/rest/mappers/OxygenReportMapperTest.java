package ca.ulaval.glo4002.booking.oxygen.report.rest.mappers;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.rest.OxygenHistoryItemDto;
import ca.ulaval.glo4002.booking.oxygen.history.rest.mappers.OxygenHistoryMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.OxygenInventoryItemDto;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.mappers.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.oxygen.report.domain.OxygenReport;
import ca.ulaval.glo4002.booking.oxygen.report.rest.OxygenReportResponse;
import ca.ulaval.glo4002.booking.oxygen.report.rest.mappers.OxygenReportMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class OxygenReportMapperTest {

    private OxygenReportMapper oxygenReportMapper;
    private OxygenInventoryMapper inventoryMapper;
    private OxygenHistoryMapper historyMapper;

    @BeforeEach
    void setUpMapper() {
        inventoryMapper = mock(OxygenInventoryMapper.class);
        historyMapper = mock(OxygenHistoryMapper.class);

        oxygenReportMapper = new OxygenReportMapper(inventoryMapper, historyMapper);
    }

    @Test
    void toResponse_shouldCallInventoryMapperWithInventory() {
        OxygenInventory inventory = mock(OxygenInventory.class);

        oxygenReportMapper.toResponse(inventory, mock(OxygenHistory.class));

        verify(inventoryMapper).toDto(eq(inventory));
    }

    @Test
    void toResponse_shouldCallHistoryMapperWithHistory() {
        OxygenHistory history = mock(OxygenHistory.class);

        oxygenReportMapper.toResponse(mock(OxygenInventory.class), history);

        verify(historyMapper).toDto(eq(history));
    }

    @Test
    void toResponse_shouldReturnReportWithInventory() {
        List<OxygenInventoryItemDto> expectedInventoryResponse = Collections.singletonList(mock(OxygenInventoryItemDto.class));
        when(inventoryMapper.toDto(any())).thenReturn(expectedInventoryResponse);

        OxygenReportResponse response = oxygenReportMapper.toResponse(mock(OxygenInventory.class), mock(OxygenHistory.class));

        assertEquals(expectedInventoryResponse.get(0), response.getInventory().get(0));
    }

    @Test
    void toResponse_shouldReturnReportWithHistory() {
        List<OxygenHistoryItemDto> expectedHistoryResponse = Collections.singletonList(mock(OxygenHistoryItemDto.class));
        when(historyMapper.toDto(any())).thenReturn(expectedHistoryResponse);

        OxygenReportResponse response = oxygenReportMapper.toResponse(mock(OxygenInventory.class), mock(OxygenHistory.class));

        assertEquals(expectedHistoryResponse.get(0), response.getHistory().get(0));
    }
}
