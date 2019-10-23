package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;

import java.util.List;

public interface PriceCalculationStrategy {

    Money calculatePassPrice(int passQuantity, Money price);
}
