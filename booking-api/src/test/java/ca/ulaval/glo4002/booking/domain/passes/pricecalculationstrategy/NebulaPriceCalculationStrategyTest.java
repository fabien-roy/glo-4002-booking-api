package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.money.PercentageDiscount;
import ca.ulaval.glo4002.booking.domain.passes.Pass;

class NebulaPriceCalculationStrategyTest {

	private NebulaPriceCalculationStrategy priceCalculationStrategy;

	@BeforeEach
	void setUpPriceCalculationStrategy() {
		priceCalculationStrategy = new NebulaPriceCalculationStrategy();
	}

	@Test
	void calculatePassPrice_shouldReturnPassPriceWithDiscount_whenPassQuantityIsOverThreshold() {
		List<Pass> passes = Collections.nCopies(NebulaPriceCalculationStrategy.PASS_QUANTITY_THRESHOLD + 1,
				mock(Pass.class));
		BigDecimal anAmount = BigDecimal.valueOf(100.0);
		Money aPrice = new Money(BigDecimal.valueOf(100.0));
		Money expectedPrice = new Money(
				new PercentageDiscount(NebulaPriceCalculationStrategy.DISCOUNT_PERCENTAGE).apply(anAmount));

		Money passPrice = priceCalculationStrategy.calculatePassPrice(passes.size(), aPrice);

		assertEquals(expectedPrice, passPrice);
	}

	@Test
	void calculatePassPrice_shouldReturnPassPriceWithoutDiscount_whenPassQuantityIsNotOverThreshold() {
		List<Pass> passes = Collections.nCopies(NebulaPriceCalculationStrategy.PASS_QUANTITY_THRESHOLD - 1,
				mock(Pass.class));
		BigDecimal anAmount = BigDecimal.valueOf(100.0);
		Money expectedPrice = new Money(anAmount);

		Money passPrice = priceCalculationStrategy.calculatePassPrice(passes.size(), expectedPrice);

		assertEquals(expectedPrice, passPrice);
	}
}