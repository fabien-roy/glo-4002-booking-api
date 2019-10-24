package ca.ulaval.glo4002.booking.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGeneratorTest {

    private NumberGenerator subject;

    @BeforeEach
    void setUpSubject() {
        subject = new NumberGenerator();
    }

    @Test
    void generate_shouldReturnNumber() {
        Number number = subject.generate();

        assertNotNull(number);
    }

    @Test
    void generate_shouldReturnDifferentNumbers() {
        Number number = subject.generate();
        Number otherNumber = subject.generate();

        assertNotEquals(number, otherNumber);
    }
}