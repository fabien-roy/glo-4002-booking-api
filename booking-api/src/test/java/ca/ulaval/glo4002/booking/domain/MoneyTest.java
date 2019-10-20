package ca.ulaval.glo4002.booking.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    void givenACertainAmount_whenAddingToIt_shouldReturnTheSum() {
        Money price = new Money(new BigDecimal(100.00));

        price.add(new Money(new BigDecimal(20.0)));

        assertTrue(120.0 == price.getValue().doubleValue());
    }

    @Test
    void givenAPrice_whenDiscountInPercentageIsApply_shouldApplyTheDiscount() {
        Money price = new Money(new BigDecimal(100.00));
        Money expected = new Money(new BigDecimal(90));

        price.applyDiscountPercentage(.10);

        assertTrue(expected.compareTo(price) == 0);
    }

    @Test
    void givenTwoMoneyWithEqualAmount_whenCompareTo_shouldReturnTrue() {
        Money money1 = new Money(new BigDecimal(100.0));

        assertTrue(money1.compareTo(money1) == 0);
    }

    @Test
    void givenTwoMoneyWithNotEqualAmounts_whenCompareTo_shouldReturnFalse() {
        Money money1 = new Money(new BigDecimal(10.0));
        Money money2 = new Money(new BigDecimal(0.));

        assertTrue(money1.compareTo(money2) != 0);
    }
}
