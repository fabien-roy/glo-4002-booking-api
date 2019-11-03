package ca.ulaval.glo4002.booking.mappers;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassDto;

import javax.inject.Inject;
import java.util.List;

public class OrderMapper {

    private final PassBundleMapper passBundleMapper;

    @Inject
    public OrderMapper(PassBundleMapper passBundleMapper) {
        this.passBundleMapper = passBundleMapper;
    }

    public OrderWithPassesAsPassesDto toDto(Order order) {
        List<PassDto> passes = passBundleMapper.toDto(order.getPassBundle());

        return new OrderWithPassesAsPassesDto(
                order.getPrice().getValue().doubleValue(),
                passes
        );
    }
}
