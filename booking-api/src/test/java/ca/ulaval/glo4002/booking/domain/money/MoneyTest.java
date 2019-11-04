package ca.ulaval.glo4002.booking.domain.money;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.money.PercentageDiscount;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    private Money subject;

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotId() {
        subject = new Money(new BigDecimal(100.0));
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
