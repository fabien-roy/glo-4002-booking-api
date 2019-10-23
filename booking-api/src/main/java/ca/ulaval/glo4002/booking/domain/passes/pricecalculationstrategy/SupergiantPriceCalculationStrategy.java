package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.domain.money.AmountDiscount;
import ca.ulaval.glo4002.booking.domain.money.Money;

public class SupergiantPriceCalculationStrategy implements PriceCalculationStrategy {

    public static final int PASS_QUANTITY_THRESHOLD = 5;
    public static final BigDecimal DISCOUNT_AMOUNT = BigDecimal.valueOf(10000);
    private AmountDiscount discount = new AmountDiscount(DISCOUNT_AMOUNT);

    @Override
    public Money calculatePassPrice(int passQuantity, Money price) {
        if (passQuantity >= PASS_QUANTITY_THRESHOLD) {
            return new Money(discount.apply(price.getValue()));
        } else {
            return price;
        }
    }
}
