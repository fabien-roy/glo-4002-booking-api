package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class NoDiscountPriceCalculationStrategyTest {

    @Test
    void calculatePassPrice_shouldReturnPassPrice() {
        NoDiscountPriceCalculationStrategy priceCalculationStrategy = new NoDiscountPriceCalculationStrategy();
        List<Pass> passes = new ArrayList<>();
        passes.add(mock(Pass.class));
        BigDecimal aAmount = BigDecimal.valueOf(100.0);
        Money expectedPrice = new Money(aAmount);

        Money price = priceCalculationStrategy.calculatePassPrice(passes.size(), expectedPrice);

        assertEquals(expectedPrice, price);
    }
}