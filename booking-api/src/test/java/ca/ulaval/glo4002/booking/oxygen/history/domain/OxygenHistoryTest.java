package ca.ulaval.glo4002.booking.oxygen.history.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OxygenHistoryTest {

	private OxygenHistory oxygenHistory;
	private LocalDate aRequestDate = FestivalConfiguration.getDefaultStartOrderDate().toLocalDate();

	@BeforeEach
	void setupHistory() {
		oxygenHistory = new OxygenHistory();
	}

	@Test
	void addTanksBought_shouldAddTanksBought() {
	    int amountTanksBought = 5;

		oxygenHistory.addTanksBought(aRequestDate, amountTanksBought);

		assertEquals(amountTanksBought, oxygenHistory.getHistoryItems().get(aRequestDate).getQtyOxygenTankBought());
	}

	@Test
	void addMadeTanks_shouldAddMadeTanks() {
	    int amountTanksMade = 5;

		oxygenHistory.addMadeTanks(aRequestDate, amountTanksMade);

		assertEquals(amountTanksMade, oxygenHistory.getHistoryItems().get(aRequestDate).getQtyOxygenTankMade());
	}

	@Test
	void addWaterUsed_shouldAddWaterUsed() {
		double amountWaterUsed = 5D;

		oxygenHistory.addWaterUsed(aRequestDate, amountWaterUsed);

		assertEquals(amountWaterUsed, oxygenHistory.getHistoryItems().get(aRequestDate).getQtyWaterUsed());
	}

	@Test
	void addCandlesUsed_shouldAddCandlesUsed() {
	    int amountCandleUsed = 5;

		oxygenHistory.addCandlesUsed(aRequestDate, amountCandleUsed);

		assertEquals(amountCandleUsed, oxygenHistory.getHistoryItems().get(aRequestDate).getQtyCandlesUsed());
	}
}
