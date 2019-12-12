package ca.ulaval.glo4002.booking.orders.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderIdentifierGeneratorTest {

    private OrderIdentifierGenerator generator;

    @BeforeEach
    void setUpGenerator() {
        generator = new OrderIdentifierGenerator();
    }

    @Test
    void generate_shouldReturnIdentifier() {
        OrderIdentifier identifier = generator.generate();

        assertNotNull(identifier);
    }

    @Test
    void generate_shouldReturnDifferentIdentifiers() {
        OrderIdentifier identifier = generator.generate();
        OrderIdentifier otherIdentifier = generator.generate();

        assertNotEquals(identifier, otherIdentifier);
    }
}