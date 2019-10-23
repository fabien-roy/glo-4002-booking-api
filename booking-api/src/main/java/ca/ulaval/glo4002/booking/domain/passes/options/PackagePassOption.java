package ca.ulaval.glo4002.booking.domain.passes.options;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;

public class PackagePassOption extends PassOption {

    public PackagePassOption(Money price, PriceCalculationStrategy priceCalculationStrategy) {
        super(price, priceCalculationStrategy);
    }

    public Money calculatePrice(int passQuantity) {
        return price;
    }
}
