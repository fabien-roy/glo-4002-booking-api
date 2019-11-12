package ca.ulaval.glo4002.booking.domain.oxygen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OxygenHistoryItemTest {

    private OxygenHistoryItem oxygenHistoryItem;

    private static final LocalDate A_VALID_DATE = LocalDate.of(2050, 06, 20);

    @BeforeEach
    void setupOxygenHistoryItem() {
        oxygenHistoryItem = new OxygenHistoryItem(A_VALID_DATE);
    }

    @Test
    void addTankBought_shouldAddTankBough() {
        Integer numTanksBough = 5;

        oxygenHistoryItem.addTankBought(numTanksBough);
        oxygenHistoryItem.addTankBought(numTanksBough);

        assertEquals(numTanksBough * 2, oxygenHistoryItem.getQtyOxygenTankBought());
    }

    @Test
    void addWaterUsed_shouldAddWaterUsed() {
        Integer numTanksBough = 5;

        oxygenHistoryItem.addWaterUsed(numTanksBough);
        oxygenHistoryItem.addWaterUsed(numTanksBough);

        assertEquals(numTanksBough * 2, oxygenHistoryItem.getQtyWaterUsed());
    }

    @Test
    void addCandleUsed_shouldAddCandleUsed() {
        Integer numTanksBough = 5;

        oxygenHistoryItem.addCandleUsed(numTanksBough);
        oxygenHistoryItem.addCandleUsed(numTanksBough);

        assertEquals(numTanksBough * 2, oxygenHistoryItem.getQtyCandlesUsed());
    }

    @Test
    void addTankMade_shouldAddTankMade() {
        Integer numTanksBough = 5;

        oxygenHistoryItem.addTankMade(numTanksBough);
        oxygenHistoryItem.addTankMade(numTanksBough);

        assertEquals(numTanksBough * 2, oxygenHistoryItem.getQtyOxygenTankMade());
    }

}
