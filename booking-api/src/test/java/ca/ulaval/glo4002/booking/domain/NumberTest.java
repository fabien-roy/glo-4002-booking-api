package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    private Number subject;

    @Test
    void constructing_shouldThrowInvalidFormatException_whenNumberIsInvalid() {
        assertThrows(InvalidFormatException.class, () -> new Number("anInvalidNumberFormat"));
    }

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotNumber() {
        subject = new Number(1L);
        Object object = new Object();

        boolean result = subject.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenIdHasSameValue() {
        Long aValue = 1L;
        subject = new Number(aValue);
        Number other = new Number(aValue);

        boolean result = subject.equals(other);

        assertTrue(result);
    }

    @Test
    void hashCode_shouldReturnValueHashCode() {
        Long aValue = 1L;
        int expectedHashCode = aValue.hashCode();
        subject = new Number(aValue);

        int hashCode = subject.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}