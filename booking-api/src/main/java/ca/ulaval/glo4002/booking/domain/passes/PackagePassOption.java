package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;

import java.util.List;

public class PackagePassOption extends PassOption {

    private PriceCalculationStrategy priceCalculationStrategy;

    public PackagePassOption(Money price, PriceCalculationStrategy priceCalculationStrategy) {
        super(price, priceCalculationStrategy);
    }

    public Money calculatePrice(List<Pass> passes) {
        return price;
    }
}
