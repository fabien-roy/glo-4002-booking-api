package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderTest {

    private Order order;

    @Test
    void getPrice_shouldReturnPassPrice_whenThereIsASinglePass() {
        Money expectedPrice = new Money(BigDecimal.valueOf(100));
        Pass pass = mock(Pass.class);
        when(pass.getPrice()).thenReturn(expectedPrice);

        order = new Order(mock(OrderDate.class), Collections.singletonList(pass));

        assertEquals(expectedPrice, order.getPrice());
    }

    @Test
    void getPrice_shouldReturnTotalPrice_whenThereIsAreMultiplePasses() {
        int passQuantity = 2;
        Money passPrice = new Money(BigDecimal.valueOf(100));
        Money expectedPrice = passPrice.multiply(BigDecimal.valueOf(passQuantity));
        Pass pass = mock(Pass.class);
        when(pass.getPrice()).thenReturn(passPrice);

        order = new Order(mock(OrderDate.class), Collections.nCopies(passQuantity, pass));

        assertEquals(expectedPrice, order.getPrice());
    }
}