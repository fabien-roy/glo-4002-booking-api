package ca.ulaval.glo4002.booking.oxygen;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OxygenDateTest {

    private OxygenDate oxygenDate;

    @Test
    public void constructing_shouldThrowInvalidOxygenDateException_whenOxygenDateIsInvalid() {
        String anInvalidOxygenDate = "anInvalidDate";

        assertThrows(InvalidOxygenDateFormatException.class, () -> new OxygenDate(anInvalidOxygenDate));
    }

    @Test
    public void constructingWithString_shouldCreateOxygenDate_whenOxygenIsValid() {
        LocalDate expectedValue = LocalDate.of(2050, 7, 1);

        oxygenDate = new OxygenDate(expectedValue.toString());

        assertEquals(expectedValue, oxygenDate.getValue());
    }

    @Test
    public void constructingWithDate_shouldCreateOxygenDate_whenOxygenIsValid() {
        LocalDate expectedValue = LocalDate.of(2050, 7, 1);

        oxygenDate = new OxygenDate(expectedValue);

        assertEquals(expectedValue, oxygenDate.getValue());
    }

    @Test
    public void addDays_shouldReturnDateWithNumberDaysLater() {
        LocalDate beginningDate = LocalDate.of(2050, 7, 1);
        LocalDate expectedDate = beginningDate;
        expectedDate = expectedDate.plusDays(10);

        oxygenDate = new OxygenDate(beginningDate);
        oxygenDate.addDays(10);

        assertEquals(expectedDate, oxygenDate.getValue());
    }
}
