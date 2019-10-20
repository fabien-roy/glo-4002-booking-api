package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.Id;
import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.domain.OrderDate;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderParser {

    // TODO : Add passes to DTO
    public OrderWithPassesAsPassesDto toDto(Order order) {
        return new OrderWithPassesAsPassesDto(
                order.getPrice().getValue().doubleValue(),
                new ArrayList<>()
        );
    }

    // TODO
    public Order parseDto(OrderWithPassesAsEventDatesDto orderDto) {
        OrderDate orderDate = new OrderDate(orderDto.getOrderDate());

        return new Order(orderDto.getVendorCode(), orderDate);
    }
}
