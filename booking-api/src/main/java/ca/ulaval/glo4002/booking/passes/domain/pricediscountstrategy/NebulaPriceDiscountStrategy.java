package ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy;

import ca.ulaval.glo4002.booking.profits.domain.Money;

import java.math.BigDecimal;

public class NebulaPriceDiscountStrategy implements PriceDiscountStrategy {

	public static final int PASS_QUANTITY_THRESHOLD = 3;
	public static final BigDecimal DISCOUNT_PERCENTAGE = BigDecimal.valueOf(.1);

	@Override
	public Money calculateDiscount(int passQuantity, Money price) {
		if (passQuantity > PASS_QUANTITY_THRESHOLD) {
			 return price.applyPercentageDiscount(DISCOUNT_PERCENTAGE);
		} else {
			return price;
		}
	}
}
