package ca.ulaval.glo4002.booking.orders.rest.mappers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.rest.OrderResponse;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.passes.rest.PassResponse;

public class OrderMapper {

    private final PassBundleMapper passBundleMapper;

    @Inject
    public OrderMapper(PassBundleMapper passBundleMapper) {
        this.passBundleMapper = passBundleMapper;
    }

    public OrderResponse toResponse(Order order) {
        List<PassResponse> passes = passBundleMapper.toResponse(order.getPassBundle());

        float fullOrderPrice = order.getPrice().getValue().floatValue();
        float orderPrice = formatOrderPrice(fullOrderPrice);

        return new OrderResponse(
                orderPrice,
                passes
        );
    }

    private float formatOrderPrice(float fullOrderPrice) {
        BigDecimal orderPrice = BigDecimal.valueOf(fullOrderPrice);

        return orderPrice.setScale(2, RoundingMode.HALF_EVEN).floatValue();
    }
}
