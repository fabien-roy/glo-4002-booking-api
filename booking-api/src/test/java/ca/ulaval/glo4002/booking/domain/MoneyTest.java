package ca.ulaval.glo4002.booking.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    private Money subject;

    @Test
    void add_shouldSetValueToSum() {
        BigDecimal initialValue = new BigDecimal(100.0);
        BigDecimal addedValue = new BigDecimal(120.0);
        BigDecimal expectedValue = initialValue.add(addedValue);
        subject = new Money(initialValue);

        subject.add(new Money(addedValue));

        assertEquals(expectedValue, subject.getValue());
    }

    @Test
    void applyDiscount_shouldApplyDiscount() {
        BigDecimal discount = new BigDecimal(.2);
        BigDecimal initialValue = new BigDecimal(100.0);
        BigDecimal expectedAmount = initialValue.subtract(initialValue.multiply(discount));
        Money expectedMoney = new Money(expectedAmount);
        subject = new Money(initialValue);

        subject.applyDiscount(discount);

        assertEquals(expectedMoney, subject);
    }

    @Test
    void equals_shouldReturnTrue_whenValuesAreEqual() {
        BigDecimal value = new BigDecimal(100.0);
        subject = new Money(value);
        Money moneyWithSameValue = new Money(value);

        boolean result = moneyWithSameValue.equals(subject);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenValuesAreNotEqual() {
        BigDecimal aValue = new BigDecimal(100.0);
        BigDecimal anotherValue = new BigDecimal(200.0);
        subject = new Money(aValue);
        Money moneyWithDifferentValue = new Money(anotherValue);

        boolean result = moneyWithDifferentValue.equals(subject);

        assertFalse(result);
    }
}
