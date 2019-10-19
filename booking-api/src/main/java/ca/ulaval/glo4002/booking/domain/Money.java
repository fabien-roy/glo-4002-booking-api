package ca.ulaval.glo4002.booking.domain;

import java.math.BigDecimal;

public class Money {

    private BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
