package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderIdentifierTest {

    private OrderIdentifier identifier;

    @Test
    void constructing_shouldThrowInvalidFormatException_whenIdentifierIsInvalid() {
        assertThrows(InvalidFormatException.class, () -> new OrderIdentifier("anInvalidFormat"));
    }

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotOrderIdentifier() {
        identifier = new OrderIdentifier(1L);
        Object anObject = new Object();

        boolean isSame = identifier.equals(anObject);

        assertFalse(isSame);
    }

    @Test
    void equals_shouldReturnTrue_whenNumberHasSameValue() {
        Long aValue = 1L;
        identifier = new OrderIdentifier(aValue);
        OrderIdentifier otherIdentifier = new OrderIdentifier(aValue);

        boolean isSame = identifier.equals(otherIdentifier);

        assertTrue(isSame);
    }

    @Test
    void equals_shouldReturnFalse_whenNumberHasDifferentValue() {
        identifier = new OrderIdentifier(1L);
        OrderIdentifier other = new OrderIdentifier(2L);

        boolean result = identifier.equals(other);

        assertFalse(result);
    }
}