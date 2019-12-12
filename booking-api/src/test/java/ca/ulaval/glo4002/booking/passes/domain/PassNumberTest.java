package ca.ulaval.glo4002.booking.passes.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PassNumberTest {

    private PassNumber number;

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotNumber() {
        number = new PassNumber(1L);
        Object anObject = new Object();

        boolean isSame = number.equals(anObject);

        assertFalse(isSame);
    }

    @Test
    void equals_shouldReturnTrue_whenNumberHasSameValue() {
        Long aValue = 1L;
        number = new PassNumber(aValue);
        PassNumber otherNumber = new PassNumber(aValue);

        boolean isSame = number.equals(otherNumber);

        assertTrue(isSame);
    }

    @Test
    void equals_shouldReturnFalse_whenNumberHasDifferentValue() {
        number = new PassNumber(1L);
        PassNumber other = new PassNumber(2L);

        boolean result = number.equals(other);

        assertFalse(result);
    }
}