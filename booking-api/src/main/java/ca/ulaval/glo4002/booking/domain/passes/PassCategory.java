package ca.ulaval.glo4002.booking.domain.passes;

import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.PriceCalculationStrategy;

public class PassCategory {

    private PriceCalculationStrategy priceCalculationStrategy;

    public PriceCalculationStrategy getPriceCalculationStrategy() {
        return priceCalculationStrategy;
    }
}
