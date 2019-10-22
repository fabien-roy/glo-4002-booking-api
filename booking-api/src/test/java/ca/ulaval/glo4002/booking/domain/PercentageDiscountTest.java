package ca.ulaval.glo4002.booking.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PercentageDiscountTest {

    PercentageDiscount subject;

    @Test
    void apply_shouldApply() {
        BigDecimal percentage = new BigDecimal(.1);
        subject = new PercentageDiscount(percentage);
        BigDecimal value = new BigDecimal(100.0);
        BigDecimal expectedValue = value.subtract(value.multiply(percentage));

        BigDecimal actualValue = subject.apply(value);

        assertEquals(expectedValue, actualValue);
    }
}