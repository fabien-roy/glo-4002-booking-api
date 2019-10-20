package ca.ulaval.glo4002.booking.domain;

import java.math.BigDecimal;

public class Money implements Comparable<Money>{

    private BigDecimal value;

    public Money() {
        this.value = new BigDecimal(0.);
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

    public void applyDiscountPercentage(Double discount) {
        value = value.subtract(value.multiply(BigDecimal.valueOf(discount)));
    }

    // TODO : peut-être pas valide, mais évite d'avoir a faire des money.getValue().compareTo(money2.getValue()) ou des money.getValue().get
    // TODO : moins chiant que override equals avec respecter les 4 règles mathématique d'une relation d'égalité
    @Override
    public int compareTo(Money money) {
        return this.value.compareTo(money.value);
    }
}