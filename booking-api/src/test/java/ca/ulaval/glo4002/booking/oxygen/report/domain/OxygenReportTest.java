package ca.ulaval.glo4002.booking.oxygen.report.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.report.domain.OxygenReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OxygenReportTest {

	private OxygenReport oxygenReport;
	private OxygenHistory mockOxygenHistory;
	private OxygenInventory mockOxygenInventory;

	@BeforeEach
	void setUpReport() {
		mockOxygenHistory = mock(OxygenHistory.class);
		mockOxygenInventory = mock(OxygenInventory.class);
		oxygenReport = new OxygenReport(mockOxygenHistory, mockOxygenInventory);
	}

	@Test
	void getHistory_shouldReturnHistory() {
		assertEquals(oxygenReport.getOxygenHistory(), mockOxygenHistory);
	}

	@Test
	void getOxygenTankInventory_shouldReturnOxygenTankInventory() {
		assertEquals(oxygenReport.getOxygenInventory(), mockOxygenInventory);
	}
}