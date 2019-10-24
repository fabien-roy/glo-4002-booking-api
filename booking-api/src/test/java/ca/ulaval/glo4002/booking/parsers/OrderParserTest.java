package ca.ulaval.glo4002.booking.parsers;

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

class OrderParserTest {

    private OrderParser subject;
    private PassListFactory passListFactory;

    @BeforeEach
    void setUpSubject() {
        passListFactory = mock(PassListFactory.class);
        subject = new OrderParser(passListFactory);
    }

    @Test
    void toDto_shouldBuildDtoWithCorrectPrice() {
        Money aPrice = new Money(new BigDecimal(500));
        Order order = mock(Order.class);
        when(order.getPrice()).thenReturn(aPrice);

        OrderWithPassesAsPassesDto orderDto = subject.toDto(order);

        assertEquals(order.getPrice().getValue().doubleValue(), orderDto.getPrice());
    }

    @Test
    void toDto_shouldCallPassListParser() {
        Money aPrice = new Money(new BigDecimal(500));
        Order order = mock(Order.class);
        when(order.getPassList()).thenReturn(mock(PassList.class));
        when(order.getPrice()).thenReturn(aPrice);

        subject.toDto(order);

        verify(passListFactory).toDto(any());
    }
}