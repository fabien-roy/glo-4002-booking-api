package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassDto;

import java.util.List;

// TODO : ACP : Should DTOs handle their creation with an Order?

// TODO : Parser = Mapper
public class OrderParser {

    private PassListFactory passListFactory;

    public OrderParser(PassListFactory passListFactory) {
        this.passListFactory = passListFactory;
    }

    public OrderWithPassesAsPassesDto toDto(Order order) {
        List<PassDto> passes = passListFactory.toDto(order.getPassList());

        return new OrderWithPassesAsPassesDto(
                order.getPrice().getValue().doubleValue(),
                passes
        );
    }
}
