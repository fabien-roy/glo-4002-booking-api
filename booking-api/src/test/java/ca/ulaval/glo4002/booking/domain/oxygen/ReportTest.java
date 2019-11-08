package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportTest {

	Report report;
	OxygenHistory mockOxygenHistory;
	OxygenInventory mockOxygenInventory;

	@BeforeEach
	void setUpReport() {
		mockOxygenHistory = mock(OxygenHistory.class);
		mockOxygenInventory = mock(OxygenInventory.class);
		report = new Report(mockOxygenHistory, mockOxygenInventory);
	}

	@Test
	void constructing_shouldSetHistory() {
		report = new Report();
		assertTrue(report.getOxygenHistory() instanceof OxygenHistory);
	}

	@Test
	void constructing_shouldSetOxygenTankInventory() {
		report = new Report();
		assertTrue(report.getOxygenInventory() instanceof OxygenInventory);
	}

	@Test
	void getHistory_shouldReturnHistory() {
		assertEquals(report.getOxygenHistory(), mockOxygenHistory);
	}

	@Test
	void getOxygenTankInventory_shouldReturnOxygenTankInventory() {
		assertEquals(report.getOxygenInventory(), mockOxygenInventory);
	}
}
