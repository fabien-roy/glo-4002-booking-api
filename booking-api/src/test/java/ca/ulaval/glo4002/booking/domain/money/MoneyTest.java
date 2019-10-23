package ca.ulaval.glo4002.booking.domain.money;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.money.PercentageDiscount;
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
    void applyPercentageDiscount_shouldApplyPercentageDiscount() {
        BigDecimal percentage = new BigDecimal(.1);
        PercentageDiscount discount = new PercentageDiscount(percentage);
        BigDecimal initialValue = new BigDecimal(100.0);
        BigDecimal expectedAmount = discount.apply(initialValue);
        Money expectedMoney = new Money(expectedAmount);
        subject = new Money(initialValue);

        subject.applyPercentageDiscount(discount);

        assertEquals(expectedMoney, subject);
    }

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotId() {
        subject = new Money();
        Object object = new Object();

        boolean result = subject.equals(object);

        assertFalse(result);
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

    @Test
    void hashCode_shouldReturnValueHashCode() {
        BigDecimal aValue = new BigDecimal(100.0);
        int expectedHashCode = aValue.hashCode();
        subject = new Money(aValue);

        int hashCode = subject.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}
