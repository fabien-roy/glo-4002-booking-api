package ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.PercentageDiscount;
import ca.ulaval.glo4002.booking.passes.domain.Pass;

class NebulaPriceDiscountStrategyTest {

	private NebulaPriceDiscountStrategy priceDiscountStrategy;

	@BeforeEach
	void setUpPriceCalculationStrategy() {
		priceDiscountStrategy = new NebulaPriceDiscountStrategy();
	}

	@Test
	void calculateDiscount_shouldReturnPassPriceWithDiscount_whenPassQuantityIsOverThreshold() {
		List<Pass> passes = Collections.nCopies(NebulaPriceDiscountStrategy.PASS_QUANTITY_THRESHOLD + 1, mock(Pass.class));
		Money aPrice = new Money(BigDecimal.valueOf(100000.0));
		Money expectedPrice = new Money(BigDecimal.valueOf(90000.0));

		Money passPrice = priceDiscountStrategy.calculateDiscount(passes.size(), aPrice);

		assertEquals(expectedPrice, passPrice);
	}

	@Test
	void calculateDiscount_shouldReturnPassPriceWithoutDiscount_whenPassQuantityIsNotOverThreshold() {
		List<Pass> passes = Collections.nCopies(NebulaPriceDiscountStrategy.PASS_QUANTITY_THRESHOLD - 1, mock(Pass.class));
		BigDecimal anAmount = BigDecimal.valueOf(100.0);
		Money expectedPrice = new Money(anAmount);

		Money passPrice = priceDiscountStrategy.calculateDiscount(passes.size(), expectedPrice);

		assertEquals(expectedPrice, passPrice);
	}
}