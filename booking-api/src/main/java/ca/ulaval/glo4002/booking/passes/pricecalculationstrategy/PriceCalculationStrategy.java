package ca.ulaval.glo4002.booking.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.profits.Money;

public interface PriceCalculationStrategy {

    Money calculatePassPrice(int passQuantity, Money price);
}