package ca.ulaval.glo4002.booking.domain.money;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    private Money money;

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotId() {
        money = new Money(new BigDecimal(100.0));
        Object object = new Object();

        boolean result = money.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenValuesAreEqual() {
        BigDecimal value = new BigDecimal(100.0);
        money = new Money(value);
        Money moneyWithSameValue = new Money(value);

        boolean result = moneyWithSameValue.equals(money);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenValuesAreNotEqual() {
        BigDecimal aValue = new BigDecimal(100.0);
        BigDecimal anotherValue = new BigDecimal(200.0);
        money = new Money(aValue);
        Money moneyWithDifferentValue = new Money(anotherValue);

        boolean result = moneyWithDifferentValue.equals(money);

        assertFalse(result);
    }

    @Test
    void hashCode_shouldReturnValueHashCode() {
        BigDecimal aValue = new BigDecimal(100.0);
        int expectedHashCode = aValue.hashCode();
        money = new Money(aValue);

        int hashCode = money.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}
