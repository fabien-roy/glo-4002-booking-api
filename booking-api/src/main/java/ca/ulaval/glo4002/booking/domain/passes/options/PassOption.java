package ca.ulaval.glo4002.booking.domain.passes.options;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;

public abstract class PassOption {

    protected Money price;
    protected PriceCalculationStrategy priceCalculationStrategy;

    public PassOption(Money price, PriceCalculationStrategy priceCalculationStrategy) {
        this.price = price;
        this.priceCalculationStrategy = priceCalculationStrategy;
    }

    public abstract Money calculatePrice(int passQuantity);
}
