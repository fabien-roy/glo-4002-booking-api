package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OxygenReportTest {

	OxygenReport oxygenReport;
	OxygenHistory mockOxygenHistory;
	OxygenInventory mockOxygenInventory;

	@BeforeEach
	void setUpReport() {
		mockOxygenHistory = mock(OxygenHistory.class);
		mockOxygenInventory = mock(OxygenInventory.class);
		oxygenReport = new OxygenReport(mockOxygenHistory, mockOxygenInventory);
	}

	@Test
	void constructing_shouldSetHistory() {
		oxygenReport = new OxygenReport();
		assertTrue(oxygenReport.getOxygenHistory() instanceof OxygenHistory);
	}

	@Test
	void constructing_shouldSetOxygenTankInventory() {
		oxygenReport = new OxygenReport();
		assertTrue(oxygenReport.getOxygenInventory() instanceof OxygenInventory);
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
