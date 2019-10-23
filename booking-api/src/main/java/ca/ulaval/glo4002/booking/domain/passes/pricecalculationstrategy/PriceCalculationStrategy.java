package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.domain.money.Money;

public interface PriceCalculationStrategy {

    Money calculatePassPrice(int passQuantity, Money price);
}
