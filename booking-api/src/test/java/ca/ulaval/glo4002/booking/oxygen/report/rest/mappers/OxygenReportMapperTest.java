package ca.ulaval.glo4002.booking.oxygen.report.rest.mappers;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.rest.mappers.OxygenHistoryMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.mappers.OxygenInventoryMapper;
import ca.ulaval.glo4002.booking.oxygen.report.domain.OxygenReport;
import ca.ulaval.glo4002.booking.oxygen.report.rest.mappers.OxygenReportMapper;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

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

    // TODO : OxygenReportMapperTest
}
