package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    private Number number;

    @Test
    void constructing_shouldThrowInvalidFormatException_whenNumberIsInvalid() {
        assertThrows(InvalidFormatException.class, () -> new Number("anInvalidNumberFormat"));
    }

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotNumber() {
        number = new Number(1L);
        Object object = new Object();

        boolean result = number.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenNumberHasSameValue() {
        Long aValue = 1L;
        number = new Number(aValue);
        Number other = new Number(aValue);

        boolean result = number.equals(other);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenNumberHasDifferentValue() {
        number = new Number(1L);
        Number other = new Number(2L);

        boolean result = number.equals(other);

        assertFalse(result);
    }

    @Test
    void hashCode_shouldReturnValueHashCode() {
        Long aValue = 1L;
        int expectedHashCode = aValue.hashCode();
        number = new Number(aValue);

        int hashCode = number.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}