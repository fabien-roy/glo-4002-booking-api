package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.orders.OrderDate;
import ca.ulaval.glo4002.booking.exceptions.InvalidOrderDateFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

// TODO : OrderDate should check if date is in valid order dates

class OrderDateTest {

    @Test
    public void constructing_shouldThrowInvalidOrderDateException_whenOrderDateIsInvalid() {
        String anInvalidOrderDate = "anInvalidDate";

        assertThrows(
                InvalidOrderDateFormatException.class,
                () -> new OrderDate(anInvalidOrderDate)
        );
    }
}