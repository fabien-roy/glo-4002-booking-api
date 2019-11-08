package ca.ulaval.glo4002.booking.domain.money;

import java.math.BigDecimal;
import java.util.Comparator;

public class Money implements Comparable<Money> {

    private BigDecimal value;
    private Comparator<Money> comparator = Comparator.comparing(Money::getValue);

    public Money(BigDecimal value) {
        this.value = value;
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

    @Override
    public int compareTo(Money other) {
        return comparator.compare(this, other);
    }
}