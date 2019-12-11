package ca.ulaval.glo4002.booking.oxygen.history.domain;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OxygenHistoryTest {

	private OxygenHistory oxygenHistory;
	private LocalDate aRequestDate = LocalDate.of(2050, 7, 1); // TODO : Use OxygenDate or something else

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
		Integer expectedNumberOfTanksBought = 5;

		oxygenHistory.addTanksBought(aRequestDate, expectedNumberOfTanksBought);

		assertEquals(expectedNumberOfTanksBought, oxygenHistory.getHistoryItems().get(aRequestDate).getQtyOxygenTankBought());
	}

	@Test
	void addMadeTanks_shouldAddMadeTanks() {
		Integer expectedNumberOfTanksMade = 5;

		oxygenHistory.addMadeTanks(aRequestDate, expectedNumberOfTanksMade);

		assertEquals(expectedNumberOfTanksMade, oxygenHistory.getHistoryItems().get(aRequestDate).getQtyOxygenTankMade());
	}

	@Test
	void addWaterUsed_shouldAddWaterUsed() {
		Double expectedNumberOfWaterUsed = 5.0;

		oxygenHistory.addWaterUsed(aRequestDate, expectedNumberOfWaterUsed);

		assertEquals(expectedNumberOfWaterUsed, oxygenHistory.getHistoryItems().get(aRequestDate).getQtyWaterUsed());
	}

	@Test
	void addCandlesUsed_shouldAddCandlesUsed() {
		Integer expectedNumberOfCandlesUsed = 5;

		oxygenHistory.addCandlesUsed(aRequestDate, expectedNumberOfCandlesUsed);

		assertEquals(expectedNumberOfCandlesUsed, oxygenHistory.getHistoryItems().get(aRequestDate).getQtyCandlesUsed());
	}

	@Test
	void addCategoryProduction_shouldAddWaterUsed_whenCategoryIsB() {
		OxygenCategories categoryB = OxygenCategories.B;
		Integer expectedNumberOfWaterUsed = 5;

		oxygenHistory.addCategoryProduction(aRequestDate, categoryB, expectedNumberOfWaterUsed);

		assertEquals(expectedNumberOfWaterUsed.doubleValue(), oxygenHistory.getHistoryItems().get(aRequestDate).getQtyWaterUsed());
	}

	@Test
	void addCategoryProduction_shouldAddCandlesUsed_whenCategoryIsA() {
		OxygenCategories categoryA = OxygenCategories.A;
		Integer expectedNumberOfCandlesUsed = 5;

		oxygenHistory.addCategoryProduction(aRequestDate, categoryA, expectedNumberOfCandlesUsed);

		assertEquals(expectedNumberOfCandlesUsed, oxygenHistory.getHistoryItems().get(aRequestDate).getQtyCandlesUsed());
	}
}
