package ca.ulaval.glo4002.booking.profits.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.profits.domain.PercentageDiscount;
import org.junit.jupiter.api.Test;

class PercentageDiscountTest {

	@Test
	void apply_shouldApply() {
		BigDecimal percentage = BigDecimal.valueOf(.1);
		PercentageDiscount discount = new PercentageDiscount(percentage);
		BigDecimal value = BigDecimal.valueOf(100.0);
		BigDecimal expectedValue = BigDecimal.valueOf(90.0);

		BigDecimal actualValue = discount.apply(value);

		// Using BigDecimal.compareTo since "0.0 != 0.00"
		assertEquals(0, expectedValue.compareTo(actualValue));
	}
}