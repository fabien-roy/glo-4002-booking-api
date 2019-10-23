package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderDate;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassDto;
import ca.ulaval.glo4002.booking.exceptions.orders.InvalidOrderFormatException;

import java.util.List;

// TODO : ACP : Should DTOs handle their creation with an Order?

public class OrderParser {

    private PassListParser passListParser;

    OrderParser(PassListParser passListParser) {
        this.passListParser = passListParser;
    }

    public OrderWithPassesAsPassesDto toDto(Order order) {
        List<PassDto> passes = passListParser.toDto(order.getPassList());

        return new OrderWithPassesAsPassesDto(
                order.getPrice().getValue().doubleValue(),
                passes
        );
    }

    public Order parseDto(OrderWithPassesAsEventDatesDto orderDto) {
        if (orderDto.getPasses() == null) {
            throw new InvalidOrderFormatException();
        }

        OrderDate orderDate = new OrderDate(orderDto.getOrderDate());
        PassList passList = passListParser.parseDto(orderDto.getPasses());

        return new Order(orderDto.getVendorCode(), orderDate, passList);
    }
}
