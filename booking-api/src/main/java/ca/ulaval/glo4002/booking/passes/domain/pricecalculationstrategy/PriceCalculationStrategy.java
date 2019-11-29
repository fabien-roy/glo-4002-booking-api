package ca.ulaval.glo4002.booking.passes.domain.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.profits.domain.Money;

public interface PriceCalculationStrategy {

    Money calculatePassPrice(int passQuantity, Money price);
}
