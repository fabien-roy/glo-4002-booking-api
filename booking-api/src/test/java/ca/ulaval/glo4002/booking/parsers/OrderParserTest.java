package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.Order;
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
        String aVendorCode = "aVendorCode";
        Order order = mock(Order.class);
        when(order.getPrice()).thenReturn(aPrice);
        when(order.getVendorCode()).thenReturn(aVendorCode);

        OrderWithPassesAsPassesDto orderDto = subject.toDto(order);

        assertEquals(order.getPrice().getValue().doubleValue(), orderDto.getPrice());
    }

    @Test
    public void parseDto_shouldParseDto() {
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                "2050-07-17",
                "TEAM",
                new PassesDto()
        );

        Order order = subject.parseDto(orderDto);

        assertEquals(orderDto.getOrderDate(), order.getOrderDate().toString());
    }
}