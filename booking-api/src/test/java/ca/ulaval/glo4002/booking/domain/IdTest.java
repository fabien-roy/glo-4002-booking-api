package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.exceptions.InvalidIdFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdTest {

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
        Id subject = new Id(aId);
        Object object = new Object();

        boolean result = subject.equals(object);

        assertFalse(result);
    }
}