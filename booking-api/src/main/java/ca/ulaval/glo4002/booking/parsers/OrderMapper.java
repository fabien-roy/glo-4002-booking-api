package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassDto;

import java.util.List;

public class OrderMapper {

    private PassListFactory passListFactory;

    public OrderMapper(PassListFactory passListFactory) {
        this.passListFactory = passListFactory;
    }

    public OrderWithPassesAsPassesDto toDto(Order order) {
        List<PassDto> passes = passListFactory.toDto(order.getPassList());

        return new OrderWithPassesAsPassesDto(
                order.getOrderNumber().toString(),
                order.getPrice().getValue().doubleValue(),
                passes
        );
    }
}
