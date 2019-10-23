package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.exceptions.InvalidIdFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdTest {

    private Id subject;

    @Test
    void constructing_shouldThrowInvalidIdFormatException_whenIdIsInvalid() {
        assertThrows(
                InvalidIdFormatException.class,
                () -> new Id("anInvalidIdFormat")
        );
    }

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotId() {
        Long aId = 1L;
        subject = new Id(aId);
        Object object = new Object();

        boolean result = subject.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenIdHasSameValue() {
        Long aId = 1L;
        subject = new Id(aId);
        Id other = new Id(aId);

        boolean result = subject.equals(other);

        assertTrue(result);
    }

    @Test
    void hashCode_shouldReturnValueHashCode() {
        Long aValue = 1L;
        int expectedHashCode = aValue.hashCode();
        subject = new Id(aValue);

        int hashCode = subject.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}