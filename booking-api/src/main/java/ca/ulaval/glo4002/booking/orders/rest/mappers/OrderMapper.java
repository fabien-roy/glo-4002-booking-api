package ca.ulaval.glo4002.booking.orders.rest.mappers;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.orders.rest.OrderRequest;
import ca.ulaval.glo4002.booking.orders.rest.OrderResponse;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.rest.PassResponse;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassMapper;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OrderMapper {

    private final OrderDateMapper orderDateMapper;
    private final PassMapper passMapper;

    @Inject
    public OrderMapper(OrderDateMapper orderDateMapper, PassMapper passMapper) {
        this.orderDateMapper = orderDateMapper;
        this.passMapper = passMapper;
    }

    public Order fromRequest(OrderRequest request) {
        if (request.getPasses() == null) {
            throw new InvalidFormatException();
        }

        OrderDate orderDate = orderDateMapper.fromString(request.getOrderDate());
        List<Pass> passes = passMapper.fromRequest(request.getPasses());

        return new Order(orderDate, passes);
    }

    public OrderResponse toResponse(Order order) {
        List<PassResponse> passes = passMapper.toResponse(order.getPasses());

        float fullOrderPrice = order.getPrice().getValue().floatValue();
        float orderPrice = formatOrderPrice(fullOrderPrice);

        return new OrderResponse(orderPrice, passes);
    }

    private float formatOrderPrice(float fullOrderPrice) {
        BigDecimal orderPrice = BigDecimal.valueOf(fullOrderPrice);

        return orderPrice.setScale(2, RoundingMode.HALF_EVEN).floatValue();
    }
}
