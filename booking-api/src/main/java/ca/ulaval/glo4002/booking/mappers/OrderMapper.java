package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassDto;

import javax.inject.Inject;
import java.util.List;

public class OrderMapper {

    private final PassListMapper passListMapper;

    @Inject
    public OrderMapper(PassListMapper passListMapper) {
        this.passListMapper = passListMapper;
    }

    public OrderWithPassesAsPassesDto toDto(Order order) {
        List<PassDto> passes = passListMapper.toDto(order.getPassList());

        return new OrderWithPassesAsPassesDto(
                order.getOrderNumber().toString(),
                order.getPrice().getValue().doubleValue(),
                passes
        );
    }
}
