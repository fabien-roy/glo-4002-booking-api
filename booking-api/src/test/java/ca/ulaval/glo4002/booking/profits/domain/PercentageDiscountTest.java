package ca.ulaval.glo4002.booking.profits.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.profits.domain.PercentageDiscount;
import org.junit.jupiter.api.Test;

class PercentageDiscountTest {

	private PercentageDiscount discount;

	@Test
	void apply_shouldApply() {
		BigDecimal percentage = new BigDecimal(.1);
		discount = new PercentageDiscount(percentage);
		BigDecimal value = new BigDecimal(100.0);
		BigDecimal valueToSubtract = value.multiply(percentage);
		BigDecimal expectedValue = value.subtract(valueToSubtract);

		BigDecimal actualValue = discount.apply(value);

		assertEquals(expectedValue, actualValue);
	}
}