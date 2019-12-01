package ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.profits.domain.Money;

public class SupergiantPriceDiscountStrategy implements PriceDiscountStrategy {

	public static final int PASS_QUANTITY_THRESHOLD = 5;
	public static final BigDecimal DISCOUNT_AMOUNT = BigDecimal.valueOf(10000);

	@Override
	public Money calculateDiscount(int passQuantity, Money price) {
		if (passQuantity >= PASS_QUANTITY_THRESHOLD) {
			return price.applyAmountDiscount(DISCOUNT_AMOUNT);
		} else {
			return price;
		}
	}
}
