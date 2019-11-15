package ca.ulaval.glo4002.booking.shuttles;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.Money;

class ShuttleTest {

	Shuttle shuttle;

	@Test
	void constructing_shouldCreateCorrectCategory() {
		ShuttleCategories category = ShuttleCategories.MILLENNIUM_FALCON;

		shuttle = new Shuttle(category, null, null);

		assertEquals(shuttle.getCategory(), category);
	}

	@Test
	void constructing_shouldCreateCorrectMaxCapacity() {
		Integer maxCapacity = 5;

		shuttle = new Shuttle(null, maxCapacity, null);

		assertEquals(shuttle.getMaxCapacity(), maxCapacity);
	}

	@Test
	void constructing_shouldCreateCorrectPrice() {
		BigDecimal bigDecimalPrice = new BigDecimal(2222);
		Money price = new Money(bigDecimalPrice);

		shuttle = new Shuttle(null, null, price);

		assertEquals(shuttle.getPrice(), price);
	}

}
