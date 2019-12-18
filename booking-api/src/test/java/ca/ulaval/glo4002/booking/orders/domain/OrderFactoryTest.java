package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.passes.domain.*;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderFactoryTest {

    private OrderFactory factory;
    private OrderIdentifierGenerator orderIdentifierGenerator;
    private PassNumberGenerator passNumberGenerator;

    @BeforeEach
    void setUpFactory() {
        orderIdentifierGenerator = mock(OrderIdentifierGenerator.class);
        passNumberGenerator = mock(PassNumberGenerator.class);

        factory = new OrderFactory(orderIdentifierGenerator, passNumberGenerator);
    }

    @Test
    void create_shouldSetOrderNumber() {
        OrderIdentifier orderIdentifier = new OrderIdentifier(1L);
        String vendorCode = "VENDOR";
        OrderNumber expectedOrderNumber = new OrderNumber(orderIdentifier, vendorCode);
        when(orderIdentifierGenerator.generate()).thenReturn(orderIdentifier);
        OrderDate orderDate = mock(OrderDate.class);
        Pass pass = mock(Pass.class);
        Order order = new Order(orderDate, Collections.singletonList(pass));

        order = factory.create(order, vendorCode);

        assertEquals(expectedOrderNumber, order.getOrderNumber());
    }

    @Test
    void create_shouldSetPassNumber_whenThereIsASinglePass() {
        PassNumber expectedPassNumber = new PassNumber(1L);
        when(passNumberGenerator.generate()).thenReturn(expectedPassNumber);
        OrderDate orderDate = mock(OrderDate.class);
        Pass pass = new Pass(
                Collections.emptyList(),
                PassCategories.SUPERNOVA,
                PassOptions.PACKAGE,
                mock(Money.class)
        );
        Order order = new Order(orderDate, Collections.singletonList(pass));

        order = factory.create(order, "VENDOR");

        assertEquals(expectedPassNumber, order.getPasses().get(0).getNumber());
    }

    @Test
    void create_shouldSetPassNumbers_whenThereAreMultiplePasses() {
        PassNumber expectedPassNumber = new PassNumber(1L);
        when(passNumberGenerator.generate()).thenReturn(expectedPassNumber);
        OrderDate orderDate = mock(OrderDate.class);
        Pass pass = new Pass(
                Collections.emptyList(),
                PassCategories.SUPERNOVA,
                PassOptions.SINGLE_PASS,
                mock(Money.class)
        );
        Order order = new Order(orderDate, Collections.nCopies(2, pass));

        order = factory.create(order, "VENDOR");

        assertEquals(expectedPassNumber, order.getPasses().get(0).getNumber());
        assertEquals(expectedPassNumber, order.getPasses().get(1).getNumber());
    }
}