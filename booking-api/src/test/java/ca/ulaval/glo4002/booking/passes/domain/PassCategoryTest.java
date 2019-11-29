package ca.ulaval.glo4002.booking.passes.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassCategory;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.Money;

class PassCategoryTest {

	PassCategory category;

	@Test
	void constructing_shouldSetCorrectCategory() {
		category = new PassCategory(PassCategories.NEBULA, null);

		assertEquals(category.getCategory(), PassCategories.NEBULA);
	}

	@Test
	void constructing_shouldSetCorrectPricePerOption() {
		Map<PassOptions, Money> pricePerOption = new HashMap<>();
		BigDecimal number = new BigDecimal(2.0);
		Money money = new Money(number);
		pricePerOption.put(PassOptions.PACKAGE, money);

		category = new PassCategory(null, pricePerOption);

		assertEquals(category.getPricePerOption(PassOptions.PACKAGE), money);
	}

}
