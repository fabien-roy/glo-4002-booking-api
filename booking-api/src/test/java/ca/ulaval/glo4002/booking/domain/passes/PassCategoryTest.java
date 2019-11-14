package ca.ulaval.glo4002.booking.domain.passes;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;

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
