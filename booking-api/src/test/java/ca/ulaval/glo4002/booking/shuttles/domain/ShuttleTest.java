package ca.ulaval.glo4002.booking.shuttles.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.shuttles.domain.Shuttle;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleCategories;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.domain.Money;

class ShuttleTest {

	private Shuttle shuttle;

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
