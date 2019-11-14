package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.oxygen.report.OxygenReport;
import ca.ulaval.glo4002.booking.oxygen.report.OxygenReportMapper;

class OxygenReportMapperTest {

    // TODO : OxygenReportMapperTest

    private OxygenReportMapper oxygenReportMapper;
    private OxygenInventoryMapper inventoryMapper;
    private OxygenHistoryMapper historyMapper;
    private OxygenReport oxygenReport;
    private OxygenInventory inventory;
    private OxygenHistory oxygenHistory;

    /*

    @BeforeEach
    void setUpMapper() {
        inventoryMapper = mock(OxygenInventoryMapper.class);
        historyMapper = mock(OxygenHistoryMapper.class);

        oxygenReportMapper = new OxygenReportMapper(inventoryMapper, historyMapper);
    }

    @BeforeEach
    void setUpReport() {
        oxygenReport = mock(OxygenReport.class);
        inventory = mock(OxygenInventory.class);
        when(oxygenReport.getOxygenInventory()).thenReturn(inventory);
        oxygenHistory = mock(OxygenHistory.class);
        when(oxygenReport.getOxygenHistory()).thenReturn(oxygenHistory);
    }

    @Test
    void toDto_shouldCallInventoryMapper_withOxygenTankInventory() {
        oxygenReportMapper.toDto(oxygenReport);

        verify(inventoryMapper).toDto(inventory);
    }

    @Test
    void toDto_shouldCallHistoryMapper_withOxygenTankHistory() {
        oxygenReportMapper.toDto(oxygenReport);

        verify(historyMapper).toDto(oxygenHistory);
    }

    @Test
    void toDto_shouldReturnReportWithCorrectInventory() {
        OxygenInventoryItemDto expectedInventoryItemDto = mock(OxygenInventoryItemDto.class);
        when(inventoryMapper.toDto(inventory)).thenReturn(Collections.singletonList(expectedInventoryItemDto));

        OxygenReportDto reportDto = oxygenReportMapper.toDto(oxygenReport);

        assertEquals(expectedInventoryItemDto, reportDto.getInventory().get(0));
    }

    @Test
    void toDto_shouldReturnReportWithCorrectInventory_whenThereAreMultipleInventoryItems() {
        OxygenInventoryItemDto expectedInventoryItemDto = mock(OxygenInventoryItemDto.class);
        OxygenInventoryItemDto expectedOtherInventoryItemDto = mock(OxygenInventoryItemDto.class);
        when(inventoryMapper.toDto(inventory)).thenReturn(Arrays.asList(expectedInventoryItemDto, expectedOtherInventoryItemDto));

        OxygenReportDto reportDto = oxygenReportMapper.toDto(oxygenReport);

        assertTrue(reportDto.getInventory().stream().anyMatch(expectedInventoryItemDto::equals));
        assertTrue(reportDto.getInventory().stream().anyMatch(expectedOtherInventoryItemDto::equals));
    }

    @Test
    void toDto_shouldReturnReportWithCorrectHistory() {
        OxygenHistoryItemDto expectedOxygenHistoryItemDto = mock(OxygenHistoryItemDto.class);
        when(historyMapper.toDto(oxygenHistory)).thenReturn(Collections.singletonList(expectedOxygenHistoryItemDto));

        OxygenReportDto reportDto = oxygenReportMapper.toDto(oxygenReport);

        assertEquals(expectedOxygenHistoryItemDto, reportDto.getHistory().get(0));
    }

    @Test
    void toDto_shouldReturnReportWithCorrectHistory_whenThereAreMultipleHistoryItems() {
        OxygenHistoryItemDto expectedOxygenHistoryItemDto = mock(OxygenHistoryItemDto.class);
        OxygenHistoryItemDto expectedOtherHisotryItemDto = mock(OxygenHistoryItemDto.class);
        when(historyMapper.toDto(oxygenHistory)).thenReturn(Arrays.asList(expectedOxygenHistoryItemDto, expectedOtherHisotryItemDto));

        OxygenReportDto reportDto = oxygenReportMapper.toDto(oxygenReport);

        assertTrue(reportDto.getHistory().stream().anyMatch(expectedOxygenHistoryItemDto::equals));
        assertTrue(reportDto.getHistory().stream().anyMatch(expectedOtherHisotryItemDto::equals));
    }
    */
}
