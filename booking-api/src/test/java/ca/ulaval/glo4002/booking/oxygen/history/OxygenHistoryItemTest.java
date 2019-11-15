package ca.ulaval.glo4002.booking.oxygen.history;

import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryItem;
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
        Integer numTanksBough = 5;

        oxygenHistoryItem.addTanksBought(numTanksBough);

        assertEquals(numTanksBough, oxygenHistoryItem.getQtyOxygenTankBought());
    }

    @Test
    void addWaterUsed_shouldAddWaterUsed() {
        Integer numTanksBough = 5;

        oxygenHistoryItem.addWaterUsed(numTanksBough);

        assertEquals(numTanksBough, oxygenHistoryItem.getQtyWaterUsed());
    }

    @Test
    void addCandleUsed_shouldAddCandleUsed() {
        Integer numTanksBough = 5;

        oxygenHistoryItem.addCandleUsed(numTanksBough);

        assertEquals(numTanksBough, oxygenHistoryItem.getQtyCandlesUsed());
    }

    @Test
    void addTankMade_shouldAddTankMade() {
        Integer numTanksBough = 5;

        oxygenHistoryItem.addTanksMade(numTanksBough);

        assertEquals(numTanksBough, oxygenHistoryItem.getQtyOxygenTankMade());
    }
}
