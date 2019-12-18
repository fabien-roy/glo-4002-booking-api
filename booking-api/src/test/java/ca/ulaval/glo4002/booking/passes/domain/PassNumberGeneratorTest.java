package ca.ulaval.glo4002.booking.passes.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PassNumberGeneratorTest {

    private PassNumberGenerator generator;

    @BeforeEach
    void setUpGenerator() {
        generator = new PassNumberGenerator();
    }

    @Test
    void generate_shouldReturnNumber() {
        PassNumber number = generator.generate();

        assertNotNull(number);
    }

    @Test
    void generate_shouldReturnDifferentNumbers() {
        PassNumber number = generator.generate();
        PassNumber otherNumber = generator.generate();

        assertNotEquals(number, otherNumber);
    }
}