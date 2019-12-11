package ca.ulaval.glo4002.booking.oxygen.history.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OxygenHistoryItemTest {

	private OxygenHistoryItem oxygenHistoryItem;

	@BeforeEach
	void setupOxygenHistoryItem() {
		oxygenHistoryItem = new OxygenHistoryItem();
	}

	@Test
	void addTankBought_shouldAddTankBough() {
		Integer nbTanksBought = 5;

		oxygenHistoryItem.addTanksBought(nbTanksBought);

		assertEquals(nbTanksBought, oxygenHistoryItem.getQtyOxygenTankBought());
	}

	@Test
	void addWaterUsed_shouldAddWaterUsed() {
		Double nbWaterUsed = 5D;

		oxygenHistoryItem.addWaterUsed(nbWaterUsed);

		assertEquals(nbWaterUsed, oxygenHistoryItem.getQtyWaterUsed());
	}

	@Test
	void addCandleUsed_shouldAddCandleUsed() {
		Integer nbCandleUsed = 5;

		oxygenHistoryItem.addCandleUsed(nbCandleUsed);

		assertEquals(nbCandleUsed, oxygenHistoryItem.getQtyCandlesUsed());
	}

	@Test
	void addTankMade_shouldAddTankMade() {
		Integer nbTanksMade = 5;

		oxygenHistoryItem.addTanksMade(nbTanksMade);

		assertEquals(nbTanksMade, oxygenHistoryItem.getQtyOxygenTankMade());
	}
}
