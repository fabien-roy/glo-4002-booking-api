package ca.ulaval.glo4002.booking.domain.passes.options;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;

public abstract class PassOption {

    protected Money price;
    protected PriceCalculationStrategy priceCalculationStrategy;

    public PassOption(Money price) {
        this.price = price;
    }

    public PriceCalculationStrategy getPriceCalculationStrategy() {
        return priceCalculationStrategy;
    }

    public void setPriceCalculationStrategy(PriceCalculationStrategy priceCalculationStrategy) {
        this.priceCalculationStrategy = priceCalculationStrategy;
    }

    public abstract Money calculatePrice(int passQuantity);
}
