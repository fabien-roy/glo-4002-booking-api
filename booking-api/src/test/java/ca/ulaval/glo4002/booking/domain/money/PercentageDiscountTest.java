package ca.ulaval.glo4002.booking.domain.money;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PercentageDiscountTest {

    private PercentageDiscount discount;

    @Test
    void apply_shouldApply() {
        BigDecimal percentage = new BigDecimal(.1);
        discount = new PercentageDiscount(percentage);
        BigDecimal value = new BigDecimal(100.0);
        BigDecimal expectedValue = value.subtract(value.multiply(percentage));

        BigDecimal actualValue = discount.apply(value);

        assertEquals(expectedValue, actualValue);
    }
}