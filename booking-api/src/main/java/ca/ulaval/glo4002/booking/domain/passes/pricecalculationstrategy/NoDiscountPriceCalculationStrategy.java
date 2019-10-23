package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;

public class NoDiscountPriceCalculationStrategy implements PriceCalculationStrategy {

    @Override
    public Money calculatePassPrice(int passQuantity, Money price) {
        return price;
    }
}
