package ca.ulaval.glo4002.booking.orders.rest.mappers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.rest.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.passes.rest.PassDto;

public class OrderMapper {

    private final PassBundleMapper passBundleMapper;

    @Inject
    public OrderMapper(PassBundleMapper passBundleMapper) {
        this.passBundleMapper = passBundleMapper;
    }

    public OrderWithPassesAsPassesDto toDto(Order order) {
        List<PassDto> passes = passBundleMapper.toDto(order.getPassBundle());

        double fullOrderPrice = order.getPrice().getValue().doubleValue();
        double orderPrice = formatOrderPrice(fullOrderPrice);

        return new OrderWithPassesAsPassesDto(
                orderPrice,
                passes
        );
    }

    private double formatOrderPrice(double fullOrderPrice) {
        BigDecimal orderPrice = BigDecimal.valueOf(fullOrderPrice);

        return orderPrice.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}
