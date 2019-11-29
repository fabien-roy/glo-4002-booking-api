package ca.ulaval.glo4002.booking.profits.domain;

import java.math.BigDecimal;

public class PercentageDiscount {

    private BigDecimal percentage;

    public PercentageDiscount(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public BigDecimal apply(BigDecimal value) {
        BigDecimal valueToSubtract = value.multiply(percentage);

        return value.subtract(valueToSubtract);
    }
}
