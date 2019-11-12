package ca.ulaval.glo4002.booking.domain.money;

import java.math.BigDecimal;
import java.util.Comparator;

public class Money implements Comparable<Money> {

    private BigDecimal value;
    private Comparator<Money> comparator = Comparator.comparing(Money::getValue);

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money(Money money) {
        this.value = new BigDecimal(String.valueOf(money.value));
    }

    public BigDecimal getValue() {
        return value;
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

    public Money multiply(BigDecimal factor) {
        value = value.multiply(factor);

        return this;
    }

    // TODO : add test
    public Money add(Money money) {
        BigDecimal amount = new BigDecimal(String.valueOf(money.value));

        this.value = this.value.add(amount);

        return new Money(this.value);
    }

    // TODO : add test
    public Money subtract(Money money) {
        BigDecimal amount = new BigDecimal(String.valueOf(money.value));

        this.value = this.value.subtract(amount);

        return new Money(this.value);
    }

    @Override
    public int compareTo(Money other) {
        return comparator.compare(this, other);
    }
}