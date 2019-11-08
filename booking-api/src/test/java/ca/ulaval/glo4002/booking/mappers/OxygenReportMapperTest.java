package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.dto.oxygen.HistoryItemDto;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenTankInventoryItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.domain.oxygen.Report;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;

import java.util.Arrays;
import java.util.Collections;

class OxygenReportMapperTest {

    private OxygenReportMapper oxygenReportMapper;
    private OxygenTankInventoryMapper inventoryMapper;
    private OxygenTankHistoryMapper historyMapper;
    private Report report;
    private OxygenInventory inventory;
    private OxygenHistory oxygenHistory;

    @BeforeEach
    void setUpMapper() {
        inventoryMapper = mock(OxygenTankInventoryMapper.class);
        historyMapper = mock(OxygenTankHistoryMapper.class);

        oxygenReportMapper = new OxygenReportMapper(inventoryMapper, historyMapper);
    }

    @BeforeEach
    void setUpReport() {
        report = mock(Report.class);
        inventory = mock(OxygenInventory.class);
        when(report.getOxygenInventory()).thenReturn(inventory);
        oxygenHistory = mock(OxygenHistory.class);
        when(report.getOxygenHistory()).thenReturn(oxygenHistory);
    }

    @Test
    void toDto_shouldCallInventoryMapper_withOxygenTankInventory() {
        oxygenReportMapper.toDto(report);

        verify(inventoryMapper).toDto(inventory);
    }

    @Test
    void toDto_shouldCallHistoryMapper_withOxygenTankHistory() {
        oxygenReportMapper.toDto(report);

        verify(historyMapper).toDto(oxygenHistory);
    }

    @Test
    void toDto_shouldReturnReportWithCorrectInventory() {
        OxygenTankInventoryItemDto expectedInventoryItemDto = mock(OxygenTankInventoryItemDto.class);
        when(inventoryMapper.toDto(inventory)).thenReturn(Collections.singletonList(expectedInventoryItemDto));

        OxygenReportDto reportDto = oxygenReportMapper.toDto(report);

        assertEquals(expectedInventoryItemDto, reportDto.getInventory().get(0));
    }

    @Test
    void toDto_shouldReturnReportWithCorrectInventory_whenThereAreMultipleInventoryItems() {
        OxygenTankInventoryItemDto expectedInventoryItemDto = mock(OxygenTankInventoryItemDto.class);
        OxygenTankInventoryItemDto expectedOtherInventoryItemDto = mock(OxygenTankInventoryItemDto.class);
        when(inventoryMapper.toDto(inventory)).thenReturn(Arrays.asList(expectedInventoryItemDto, expectedOtherInventoryItemDto));

        OxygenReportDto reportDto = oxygenReportMapper.toDto(report);

        assertTrue(reportDto.getInventory().stream().anyMatch(expectedInventoryItemDto::equals));
        assertTrue(reportDto.getInventory().stream().anyMatch(expectedOtherInventoryItemDto::equals));
    }

    @Test
    void toDto_shouldReturnReportWithCorrectHistory() {
        HistoryItemDto expectedHistoryItemDto = mock(HistoryItemDto.class);
        when(historyMapper.toDto(oxygenHistory)).thenReturn(Collections.singletonList(expectedHistoryItemDto));

        OxygenReportDto reportDto = oxygenReportMapper.toDto(report);

        assertEquals(expectedHistoryItemDto, reportDto.getHistory().get(0));
    }

    @Test
    void toDto_shouldReturnReportWithCorrectHistory_whenThereAreMultipleHistoryItems() {
        HistoryItemDto expectedHistoryItemDto = mock(HistoryItemDto.class);
        HistoryItemDto expectedOtherHisotryItemDto = mock(HistoryItemDto.class);
        when(historyMapper.toDto(oxygenHistory)).thenReturn(Arrays.asList(expectedHistoryItemDto, expectedOtherHisotryItemDto));

        OxygenReportDto reportDto = oxygenReportMapper.toDto(report);

        assertTrue(reportDto.getHistory().stream().anyMatch(expectedHistoryItemDto::equals));
        assertTrue(reportDto.getHistory().stream().anyMatch(expectedOtherHisotryItemDto::equals));
    }
}
