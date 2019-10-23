package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;

import java.util.List;

public class SupernovaPriceCalculationStrategy implements PriceCalculationStrategy {

    @Override
    public Money calculatePassPrice(List<Pass> passes, Money price) {
        return price;
    }
}
