package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderDate;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;

import java.util.ArrayList;

public class OrderParser {

    // TODO : Add passes to DTO
    public OrderWithPassesAsPassesDto toDto(Order order) {
        return new OrderWithPassesAsPassesDto(
                order.getPrice().getValue().doubleValue(),
                new ArrayList<>()
        );
    }

    public Order parseDto(OrderWithPassesAsEventDatesDto orderDto) {
        OrderDate orderDate = new OrderDate(orderDto.getOrderDate());

        return new Order(orderDto.getVendorCode(), orderDate);
    }
}
