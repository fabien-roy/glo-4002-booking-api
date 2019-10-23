package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.exceptions.orders.InvalidOrderDateFormatException;
import ca.ulaval.glo4002.booking.exceptions.orders.OutOfBoundsOrderDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderDateTest {

    @Test
    void constructing_shouldSetCorrectValue() {
        LocalDateTime expectedValue  = OrderDate.START_DATE_TIME.plusDays(1);

        OrderDate subject = new OrderDate(expectedValue);

        assertEquals(expectedValue, subject.getValue());
    }

    @Test
    void constructing_shouldThrowInvalidOrderDateException_whenOrderDateIsInvalid() {
        String anInvalidOrderDate = "anInvalidDate";

        assertThrows(
                InvalidOrderDateFormatException.class,
                () -> new OrderDate(anInvalidOrderDate)
        );
    }

    @Test
    void constructing_shouldThrowOutOfBoundsOrderDateException_whenOrderDateIsUnderBounds() {
        LocalDateTime aUnderBoundOrderDate  = OrderDate.START_DATE_TIME.minusDays(1);

        assertThrows(
                OutOfBoundsOrderDateException.class,
                () -> new OrderDate(aUnderBoundOrderDate)
        );
    }

    @Test
    void constructing_shouldThrowOutOfBoundsOrderDateException_whenOrderDateIsOverBounds() {
        LocalDateTime aOverBoundOrderDate  = OrderDate.END_DATE_TIME.plusDays(1);

        assertThrows(
                OutOfBoundsOrderDateException.class,
                () -> new OrderDate(aOverBoundOrderDate)
        );
    }
}