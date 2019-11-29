package ca.ulaval.glo4002.booking.passes.domain.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.profits.domain.Money;

public class NoDiscountPriceCalculationStrategy implements PriceCalculationStrategy {

    @Override
    public Money calculatePassPrice(int passQuantity, Money price) {
        return price;
    }
}
