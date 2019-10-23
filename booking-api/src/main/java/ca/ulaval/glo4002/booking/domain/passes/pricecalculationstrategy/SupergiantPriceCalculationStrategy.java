package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.domain.AmountDiscount;
import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;

import java.math.BigDecimal;
import java.util.List;

public class SupergiantPriceCalculationStrategy implements PriceCalculationStrategy {

    public static final int PASS_QUANTITY_THRESHOLD = 5;
    public static final BigDecimal DISCOUNT_AMOUNT = BigDecimal.valueOf(10000);
    private AmountDiscount discount = new AmountDiscount(DISCOUNT_AMOUNT);

    @Override
    public Money calculatePassPrice(List<Pass> passes, Money price) {
        if (passes.size() >= PASS_QUANTITY_THRESHOLD) {
            return new Money(discount.apply(price.getValue()));
        } else {
            return price;
        }
    }
}
