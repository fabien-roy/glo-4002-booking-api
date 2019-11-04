package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.exceptions.InvalidOrderDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderDateTest {

    @Test
    void constructing_shouldSetCorrectValue() {
        LocalDateTime expectedValue  = OrderDate.START_DATE_TIME.plusDays(1);
        ZonedDateTime expectedZonedValue = ZonedDateTime.of(expectedValue, ZoneId.systemDefault());

        OrderDate date = new OrderDate(expectedZonedValue.toString());

        assertEquals(expectedValue, date.getValue());
    }

    @Test
    void constructing_shouldThrowInvalidFormatException_whenOrderDateIsInvalid() {
        String anInvalidOrderDate = "anInvalidDate";

        assertThrows(InvalidFormatException.class, () -> new OrderDate(anInvalidOrderDate));
    }

    @Test
    void constructing_shouldThrowInvalidOrderDateException_whenOrderDateIsUnderBounds() {
        LocalDateTime aUnderBoundValue  = OrderDate.START_DATE_TIME.minusDays(1);
        ZonedDateTime aUnderBoundZonedValue = ZonedDateTime.of(aUnderBoundValue, ZoneId.systemDefault());

        assertThrows(InvalidOrderDateException.class, () -> new OrderDate(aUnderBoundZonedValue.toString()));
    }

    @Test
    void constructing_shouldThrowInvalidOrderDateException_whenOrderDateIsOverBounds() {
        LocalDateTime aOverBoundValue  = OrderDate.END_DATE_TIME.plusDays(1);
        ZonedDateTime aOverBoundZonedValue = ZonedDateTime.of(aOverBoundValue, ZoneId.systemDefault());

        assertThrows(InvalidOrderDateException.class, () -> new OrderDate(aOverBoundZonedValue.toString()));
    }
}