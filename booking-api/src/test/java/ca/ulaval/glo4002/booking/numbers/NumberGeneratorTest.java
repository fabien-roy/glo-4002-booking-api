package ca.ulaval.glo4002.booking.numbers;

import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGeneratorTest {

    private NumberGenerator generator;

    @BeforeEach
    void setUpGenerator() {
        generator = new NumberGenerator();
    }

    @Test
    void generate_shouldReturnNumber() {
        Number number = generator.generate();

        assertNotNull(number);
    }

    @Test
    void generate_shouldReturnDifferentNumbers() {
        Number number = generator.generate();
        Number otherNumber = generator.generate();

        assertNotEquals(number, otherNumber);
    }
}