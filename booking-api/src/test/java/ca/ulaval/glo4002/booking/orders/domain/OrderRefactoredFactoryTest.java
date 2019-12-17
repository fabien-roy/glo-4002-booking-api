package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassNumberGenerator;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import ca.ulaval.glo4002.booking.passes.domain.PassRefactored;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderRefactoredFactoryTest {

    private OrderRefactoredFactory factory;
    private OrderIdentifierGenerator orderIdentifierGenerator;
    private PassNumberGenerator passNumberGenerator;

    @BeforeEach
    void setUpFactory() {
        orderIdentifierGenerator = mock(OrderIdentifierGenerator.class);
        passNumberGenerator = mock(PassNumberGenerator.class);

        factory = new OrderRefactoredFactory(orderIdentifierGenerator, passNumberGenerator);
    }

    @Test
    void create_shouldSetOrderNumber() {
        OrderIdentifier orderIdentifier = new OrderIdentifier(1L);
        String vendorCode = "VENDOR";
        OrderNumber expectedOrderNumber = new OrderNumber(orderIdentifier, vendorCode);
        when(orderIdentifierGenerator.generate()).thenReturn(orderIdentifier);
        OrderDate orderDate = mock(OrderDate.class);
        PassRefactored pass = mock(PassRefactored.class);
        OrderRefactored order = new OrderRefactored(orderDate, pass);

        order = factory.create(order, vendorCode);

        assertEquals(expectedOrderNumber, order.getOrderNumber());
    }

    @Test
    void create_shouldSetPassNumber() {
        long expectedPassNumber = 1L;
        when(passNumberGenerator.generate()).thenReturn(expectedPassNumber);
        OrderDate orderDate = mock(OrderDate.class);
        PassRefactored pass = new PassRefactored(
                PassCategories.SUPERNOVA,
                PassOptions.PACKAGE,
                mock(Money.class),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
        OrderRefactored order = new OrderRefactored(orderDate, pass);

        order = factory.create(order, "VENDOR");

        assertEquals(expectedPassNumber, order.getPass().getNumber());
    }
}