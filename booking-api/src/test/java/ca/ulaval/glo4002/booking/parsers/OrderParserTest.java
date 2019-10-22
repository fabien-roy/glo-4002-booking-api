package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.domain.OrderDate;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderParserTest {

    private OrderParser subject;

    @BeforeEach
    public void setUpSubject() {
        subject = new OrderParser();
    }

    @Test
    public void toDto_shouldBuildDto() {
        Money aPrice = new Money(new BigDecimal(500));
        Order order = mock(Order.class);
        when(order.getPrice()).thenReturn(aPrice);

        OrderWithPassesAsPassesDto orderDto = subject.toDto(order);

        assertEquals(order.getPrice().getValue().doubleValue(), orderDto.getPrice());
    }

    @Test
    public void parseDto_shouldParseDto() {
        String orderDate = "2050-05-21T15:23:20.142Z";
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                orderDate,
                "TEAM",
                new PassesDto()
        );
        OrderDate expectedOrderDate = new OrderDate(orderDate);

        Order order = subject.parseDto(orderDto);

        assertEquals(expectedOrderDate.toString(), order.getOrderDate().toString());
        assertEquals(orderDto.getVendorCode(), order.getVendorCode());
    }
}