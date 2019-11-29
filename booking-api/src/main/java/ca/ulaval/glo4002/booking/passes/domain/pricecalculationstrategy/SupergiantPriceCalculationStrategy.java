package ca.ulaval.glo4002.booking.passes.domain.pricecalculationstrategy;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.profits.domain.Money;

public class SupergiantPriceCalculationStrategy implements PriceCalculationStrategy {

	public static final int PASS_QUANTITY_THRESHOLD = 5;
	public static final BigDecimal DISCOUNT_AMOUNT = BigDecimal.valueOf(10000);

	@Override
	public Money calculatePassPrice(int passQuantity, Money price) {
		if (passQuantity >= PASS_QUANTITY_THRESHOLD) {
			price.applyAmountDiscount(DISCOUNT_AMOUNT);
			return price;
		} else {
			return price;
		}
	}
}
