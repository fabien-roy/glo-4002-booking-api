package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.exceptions.InvalidOrderDateFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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