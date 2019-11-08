package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportTest {

	Report report;
	OxygenHistory mockOxygenHistory;
	OxygenTankInventory mockOxygenTankInventory;

	@BeforeEach
	void setUpReport() {
		mockOxygenHistory = mock(OxygenHistory.class);
		mockOxygenTankInventory = mock(OxygenTankInventory.class);
		report = new Report(mockOxygenHistory, mockOxygenTankInventory);
	}

	@Test
	void constructing_shouldSetHistory() {
		report = new Report();
		assertTrue(report.getOxygenHistory() instanceof OxygenHistory);
	}

	@Test
	void constructing_shouldSetOxygenTankInventory() {
		report = new Report();
		assertTrue(report.getOxygenTankInventory() instanceof OxygenTankInventory);
	}

	@Test
	void getHistory_shouldReturnHistory() {
		assertEquals(report.getOxygenHistory(), mockOxygenHistory);
	}

	@Test
	void getOxygenTankInventory_shouldReturnOxygenTankInventory() {
		assertEquals(report.getOxygenTankInventory(), mockOxygenTankInventory);
	}
}
