package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.domain.passes.money.Money;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderMapperTest {

    private OrderMapper subject;
    private PassListFactory passListFactory;
    private OrderNumber orderNumber;
    private Order order;

    @BeforeEach
    void setUpSubject() {
        passListFactory = mock(PassListFactory.class);
        subject = new OrderMapper(passListFactory);
    }

    @BeforeEach
    void setUpOrder() {
        orderNumber = mock(OrderNumber.class);
        order = mock(Order.class);
        when(orderNumber.toString()).thenReturn("expectedOrderNumber");
        when(order.getOrderNumber()).thenReturn(orderNumber);
        when(order.getPrice()).thenReturn(new Money(new BigDecimal(500)));
        when(order.getPassList()).thenReturn(mock(PassList.class));
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectOrderNumber() {
        OrderWithPassesAsPassesDto orderDto = subject.toDto(order);

        assertEquals(orderNumber.toString(), orderDto.getOrderNumber());
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectPrice() {
        OrderWithPassesAsPassesDto orderDto = subject.toDto(order);

        assertEquals(order.getPrice().getValue().doubleValue(), orderDto.getPrice());
    }

    @Test
    void toDto_shouldCallPassListParser() {
        subject.toDto(order);

        verify(passListFactory).toDto(any());
    }
}