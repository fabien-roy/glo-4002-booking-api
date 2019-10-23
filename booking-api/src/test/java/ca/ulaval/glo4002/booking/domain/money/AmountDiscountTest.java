package ca.ulaval.glo4002.booking.domain.money;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.money.AmountDiscount;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmountDiscountTest {

    private AmountDiscount subject;

    @Test
    void apply_shouldApply() {
        BigDecimal amount = new BigDecimal(.1);
        subject = new AmountDiscount(amount);
        BigDecimal value = new BigDecimal(100.0);
        BigDecimal expectedValue = value.subtract(amount);

        BigDecimal actualValue = subject.apply(value);

        assertEquals(expectedValue, actualValue);
    }
}