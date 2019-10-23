package ca.ulaval.glo4002.booking.domain.passes.options;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;

public class SinglePassOption extends PassOption {

    public SinglePassOption(Money price, PriceCalculationStrategy priceCalculationStrategy) {
        super(price, priceCalculationStrategy);
    }

    public Money calculatePrice(int passQuantity) {
        return priceCalculationStrategy.calculatePassPrice(passQuantity, price);
    }
}
