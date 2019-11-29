package ca.ulaval.glo4002.booking.oxygen.history.domain;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistoryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OxygenHistoryItemTest {

	private OxygenHistoryItem oxygenHistoryItem;

	@BeforeEach
	void setupOxygenHistoryItem() {
		oxygenHistoryItem = new OxygenHistoryItem();
	}

	@Test
	void constructing_setQtyOxygenTankBoughtTo0() {
		assertEquals(0, oxygenHistoryItem.getQtyOxygenTankBought());
	}

	@Test
	void constructing_setQtyOxygenTankMadeTo0() {
		assertEquals(0, oxygenHistoryItem.getQtyOxygenTankMade());
	}

	@Test
	void constructing_setQtyWaterUsedTo0() {
		assertEquals(0D, oxygenHistoryItem.getQtyWaterUsed());
	}

	@Test
	void constructing_setQtyCandlesUsedTo0() {
		assertEquals(0, oxygenHistoryItem.getQtyCandlesUsed());
	}

	@Test
	void addTankBought_shouldAddTankBough() {
		Integer nbTanksBought = 5;

		oxygenHistoryItem.addTanksBought(nbTanksBought);

		assertEquals(nbTanksBought, oxygenHistoryItem.getQtyOxygenTankBought());
	}

	@Test
	void addWaterUsed_shouldAddWaterUsed() {
		Double nbTanksBought = 5D;

		oxygenHistoryItem.addWaterUsed(nbTanksBought);

		assertEquals(nbTanksBought, oxygenHistoryItem.getQtyWaterUsed());
	}

	@Test
	void addCandleUsed_shouldAddCandleUsed() {
		Integer nbTanksBought = 5;

		oxygenHistoryItem.addCandleUsed(nbTanksBought);

		assertEquals(nbTanksBought, oxygenHistoryItem.getQtyCandlesUsed());
	}

	@Test
	void addTankMade_shouldAddTankMade() {
		Integer nbTanksBought = 5;

		oxygenHistoryItem.addTanksMade(nbTanksBought);

		assertEquals(nbTanksBought, oxygenHistoryItem.getQtyOxygenTankMade());
	}
}
