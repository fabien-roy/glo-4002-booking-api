package ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.SupernovaPriceCalculationStrategy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SupernovaPriceCalculationStrategyTest {

    private SupernovaPriceCalculationStrategy subject;

    @Test
    void calculatePassPrice_shouldReturnPassPrice() {
        subject = new SupernovaPriceCalculationStrategy();
        List<Pass> passes = new ArrayList<>();
        passes.add(mock(Pass.class));
        BigDecimal aAmount = BigDecimal.valueOf(100.0);
        Money expectedPrice = new Money(aAmount);

        Money price = subject.calculatePassPrice(passes, expectedPrice);

        assertEquals(expectedPrice, price);
    }
}