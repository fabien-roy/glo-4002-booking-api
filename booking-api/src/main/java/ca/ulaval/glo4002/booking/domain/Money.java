package ca.ulaval.glo4002.booking.domain;

import java.math.BigDecimal;

public class Money {

    private BigDecimal value;

    public Money() {
        this.value = BigDecimal.valueOf(0.0);
    }

    public Money(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void add(Money money) {
        this.value = this.value.add(money.value);
    }

    // TODO : ACP : Add Discount class?
    public void applyDiscount(BigDecimal discount) {
        BigDecimal valueToSubtract = value.multiply(discount);

        value = value.subtract(valueToSubtract);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        Money money = (Money) object;

        return value.equals(money.getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}