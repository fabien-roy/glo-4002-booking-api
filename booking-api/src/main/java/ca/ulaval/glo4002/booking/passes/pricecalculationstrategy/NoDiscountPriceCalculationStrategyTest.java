package ca.ulaval.glo4002.booking.passes.pricecalculationstrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.profits.Money;
import ca.ulaval.glo4002.booking.passes.Pass;

class NoDiscountPriceCalculationStrategyTest {

    @Test
    void calculatePassPrice_shouldReturnPassPrice() {
        NoDiscountPriceCalculationStrategy priceCalculationStrategy = new NoDiscountPriceCalculationStrategy();
        List<Pass> passes = Collections.singletonList(mock(Pass.class));
        Money expectedPrice = new Money(BigDecimal.valueOf(100.0));

        Money price = priceCalculationStrategy.calculatePassPrice(passes.size(), expectedPrice);

        assertEquals(expectedPrice, price);
    }
}