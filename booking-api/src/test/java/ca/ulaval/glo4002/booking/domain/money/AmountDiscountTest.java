package ca.ulaval.glo4002.booking.domain.money;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmountDiscountTest {

    private AmountDiscount discount;

    @Test
    void apply_shouldApply() {
        BigDecimal amount = new BigDecimal(.1);
        discount = new AmountDiscount(amount);
        BigDecimal value = new BigDecimal(100.0);
        BigDecimal expectedValue = value.subtract(amount);

        BigDecimal actualValue = discount.apply(value);

        assertEquals(expectedValue, actualValue);
    }
}