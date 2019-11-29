package ca.ulaval.glo4002.booking.profits.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmountDiscountTest {

    @Test
    void apply_shouldApplyTheDiscount() {
        BigDecimal amount = BigDecimal.valueOf(10.0);
        AmountDiscount discount = new AmountDiscount(amount);
        BigDecimal value = BigDecimal.valueOf(100.0);
        BigDecimal expectedValue = BigDecimal.valueOf(90.0);

        BigDecimal actualValue = discount.apply(value);

        assertEquals(expectedValue, actualValue);
    }
}