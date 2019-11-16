package ca.ulaval.glo4002.booking.oxygen.history;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OxygenHistoryTest {

	private OxygenHistory oxygenHistory;
	private LocalDate aRequestDate = LocalDate.of(2050, 7, 1);

	@BeforeEach
	void setupHistory() {
		oxygenHistory = new OxygenHistory();
	}

	@Test
	void constructing_shouldSetNoHistoryItems() {
		assertTrue(oxygenHistory.getHistoryItems().isEmpty());
	}

	@Test
	void addTanksBought_shouldAddTanksBought() {
		oxygenHistory.addTanksBought(aRequestDate, 5);

		assertEquals(oxygenHistory.getHistoryItems().get(aRequestDate).getQtyOxygenTankBought(), 5);
	}

	@Test
	void addMadeTanks_shouldAddMadeTanks() {
		oxygenHistory.addMadeTanks(aRequestDate, 5);

		assertEquals(oxygenHistory.getHistoryItems().get(aRequestDate).getQtyOxygenTankMade(), 5);
	}

	@Test
	void addWaterUsed_shouldAddWaterUsed() {
		oxygenHistory.addWaterUsed(aRequestDate, 5D);

		assertEquals(oxygenHistory.getHistoryItems().get(aRequestDate).getQtyWaterUsed(), 5D);
	}

	@Test
	void addCandlesUsed_shouldAddCandlesUsed() {
		oxygenHistory.addCandlesUsed(aRequestDate, 5);

		assertEquals(oxygenHistory.getHistoryItems().get(aRequestDate).getQtyCandlesUsed(), 5);
	}
}
