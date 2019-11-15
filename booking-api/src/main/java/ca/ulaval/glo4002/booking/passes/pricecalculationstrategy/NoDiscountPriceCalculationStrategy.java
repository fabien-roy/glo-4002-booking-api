package ca.ulaval.glo4002.booking.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.profits.Money;

public class NoDiscountPriceCalculationStrategy implements PriceCalculationStrategy {

    @Override
    public Money calculatePassPrice(int passQuantity, Money price) {
        return price;
    }
}
