package ca.ulaval.glo4002.booking.numbers;

import ca.ulaval.glo4002.booking.errors.InvalidFormatException;
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
        Object anObject = new Object();

        boolean isSame = number.equals(anObject);

        assertFalse(isSame);
    }

    @Test
    void equals_shouldReturnTrue_whenNumberHasSameValue() {
        Long aValue = 1L;
        number = new Number(aValue);
        Number otherNumber = new Number(aValue);

        boolean isSame = number.equals(otherNumber);

        assertTrue(isSame);
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