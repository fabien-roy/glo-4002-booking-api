package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.PercentageDiscount;
import ca.ulaval.glo4002.booking.domain.passes.Pass;

import java.math.BigDecimal;
import java.util.List;

public class NebulaPriceCalculationStrategy implements PriceCalculationStrategy {

    public static final int PASS_QUANTITY_THRESHOLD = 3;
    public static final BigDecimal DISCOUNT_PERCENTAGE = BigDecimal.valueOf(.1);
    private PercentageDiscount discount = new PercentageDiscount(DISCOUNT_PERCENTAGE);

    @Override
    public Money calculatePassPrice(List<Pass> passes, Money price) {
        if (passes.size() > PASS_QUANTITY_THRESHOLD) {
            return new Money(discount.apply(price.getValue()));
        } else {
            return price;
        }
    }
}
