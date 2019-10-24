package ca.ulaval.glo4002.booking.domain.orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderNumberTest {

    private OrderNumber subject;

    @Test
    void constructing_shouldSetNewNumber_whenThereIsNone() {
        subject = new OrderNumber(null, "VENDOR");

        assertNotNull(subject.getNumber());
    }

    @Test
    void constructing_shouldSetUniqueNumbers() {
        subject = new OrderNumber(null, "VENDOR");
        OrderNumber other = new OrderNumber(null, "VENDOR");

        assertNotEquals(subject.getNumber(), other.getNumber());
    }
}