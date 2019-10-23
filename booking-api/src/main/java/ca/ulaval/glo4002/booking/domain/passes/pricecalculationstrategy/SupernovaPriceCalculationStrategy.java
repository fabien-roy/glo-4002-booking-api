package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.domain.Money;

public class SupernovaPriceCalculationStrategy implements PriceCalculationStrategy {

    @Override
    public Money calculatePassPrice(int passQuantity, Money price) {
        return price;
    }
}
