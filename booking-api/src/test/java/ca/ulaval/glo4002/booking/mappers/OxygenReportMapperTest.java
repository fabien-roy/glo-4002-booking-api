package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.booking.dto.oxygen.OxygenTankInventoryItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.oxygen.History;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.domain.oxygen.Report;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenReportDto;

import java.util.Arrays;
import java.util.Collections;

class OxygenReportMapperTest {

    private OxygenReportMapper oxygenReportMapper;
    private OxygenTankInventoryMapper inventoryMapper;
    private OxygenTankHistoryMapper historyMapper;
    private Report report;
    private OxygenTankInventory inventory;
    private History history;

    @BeforeEach
    void setUpMapper() {
        inventoryMapper = mock(OxygenTankInventoryMapper.class);
        historyMapper = mock(OxygenTankHistoryMapper.class);

        oxygenReportMapper = new OxygenReportMapper(inventoryMapper, historyMapper);
    }

    @BeforeEach
    void setUpReport() {
        report = mock(Report.class);
        inventory = mock(OxygenTankInventory.class);
        when(report.getOxygenTankInventory()).thenReturn(inventory);
        history = mock(History.class);
        when(report.getHistory()).thenReturn(history);
    }

    @Test
    void toDto_shouldCallInventoryMapper_withOxygenTankInventory() {
        oxygenReportMapper.toDto(report);

        verify(inventoryMapper).toDto(inventory);
    }

    @Test
    void toDto_shouldCallHistoryMapper_withOxygenTankHistory() {
        oxygenReportMapper.toDto(report);

        verify(historyMapper).toDto(history);
    }

    @Test
    void toDto_shouldReturnReportWithCorrectInventory() {
        String expectedGradeTankOxygen = "expectedGradeTankOxygen";
        Long expectedQuantity = 1L;
        OxygenTankInventoryItemDto inventoryItemDto = mock(OxygenTankInventoryItemDto.class);
        when(inventoryItemDto.getGradeTankOxygen()).thenReturn(expectedGradeTankOxygen);
        when(inventoryItemDto.getQuantity()).thenReturn(expectedQuantity);
        when(inventoryMapper.toDto(inventory)).thenReturn(Collections.singletonList(inventoryItemDto));

        OxygenReportDto reportDto = oxygenReportMapper.toDto(report);

        assertEquals(expectedGradeTankOxygen, reportDto.getInventory().get(0).getGradeTankOxygen());
        assertEquals(expectedQuantity, reportDto.getInventory().get(0).getQuantity());
    }

    @Test
    void toDto_shouldReturnReportWithCorrectInventory_whenThereAreMultipleInventoryItems() {
        String expectedGradeTankOxygen = "expectedGradeTankOxygen";
        String expectedOtherGradeTankOxygen = "expectedOtherGradeTankOxygen";
        Long expectedQuantity = 1L;
        Long expectedOtherQuantity = 2L;
        OxygenTankInventoryItemDto inventoryItemDto = mock(OxygenTankInventoryItemDto.class);
        when(inventoryItemDto.getGradeTankOxygen()).thenReturn(expectedGradeTankOxygen);
        when(inventoryItemDto.getQuantity()).thenReturn(expectedQuantity);
        OxygenTankInventoryItemDto otherInventoryItemDto = mock(OxygenTankInventoryItemDto.class);
        when(otherInventoryItemDto.getGradeTankOxygen()).thenReturn(expectedOtherGradeTankOxygen);
        when(otherInventoryItemDto.getQuantity()).thenReturn(expectedOtherQuantity);
        when(inventoryMapper.toDto(inventory)).thenReturn(Arrays.asList(inventoryItemDto, otherInventoryItemDto));

        OxygenReportDto reportDto = oxygenReportMapper.toDto(report);

        assertTrue(reportDto.getInventory().stream().anyMatch(item -> expectedGradeTankOxygen.equals(item.getGradeTankOxygen())));
        assertTrue(reportDto.getInventory().stream().anyMatch(item -> expectedOtherGradeTankOxygen.equals(item.getGradeTankOxygen())));
        assertTrue(reportDto.getInventory().stream().anyMatch(item -> expectedQuantity.equals(item.getQuantity())));
        assertTrue(reportDto.getInventory().stream().anyMatch(item -> expectedOtherQuantity.equals(item.getQuantity())));
    }

    // TODO : History tests
}
