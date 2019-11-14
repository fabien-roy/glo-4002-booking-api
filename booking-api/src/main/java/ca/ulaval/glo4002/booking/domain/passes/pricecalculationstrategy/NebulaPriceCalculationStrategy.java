package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.money.PercentageDiscount;

public class NebulaPriceCalculationStrategy implements PriceCalculationStrategy {

	public static final int PASS_QUANTITY_THRESHOLD = 3;
	public static final BigDecimal DISCOUNT_PERCENTAGE = BigDecimal.valueOf(.1);
	private PercentageDiscount discount = new PercentageDiscount(DISCOUNT_PERCENTAGE);

	@Override
	public Money calculatePassPrice(int passQuantity, Money price) {
		if (passQuantity > PASS_QUANTITY_THRESHOLD) {
			return new Money(discount.apply(price.getValue()));
		} else {
			return price;
		}
	}
}
