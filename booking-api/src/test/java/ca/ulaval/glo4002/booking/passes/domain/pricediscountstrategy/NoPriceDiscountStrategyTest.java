package ca.ulaval.glo4002.booking.passes.domain.pricediscountstrategy;

import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class NoPriceDiscountStrategyTest {

    @Test
    void calculateDiscount_shouldReturnPassPrice() {
        NoPriceDiscountStrategy priceDiscountStrategy = new NoPriceDiscountStrategy();
        List<Pass> passes = Collections.singletonList(mock(Pass.class));
        Money expectedPrice = new Money(BigDecimal.valueOf(100000.0));

        Money price = priceDiscountStrategy.calculateDiscount(passes.size(), expectedPrice);

        assertEquals(expectedPrice, price);
    }
}