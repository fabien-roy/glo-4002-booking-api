package ca.ulaval.glo4002.booking.orders.rest.mappers;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.orders.domain.OrderRefactored;
import ca.ulaval.glo4002.booking.orders.rest.OrderRefactoredRequest;
import ca.ulaval.glo4002.booking.orders.rest.OrderResponse;
import ca.ulaval.glo4002.booking.passes.domain.PassRefactored;
import ca.ulaval.glo4002.booking.passes.rest.PassResponse;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassRefactoredMapper;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OrderMapper {

    private final PassBundleMapper passBundleMapper;
    private final OrderDateMapper orderDateMapper;
    private final PassRefactoredMapper passMapper;

    @Inject
    public OrderMapper(PassBundleMapper passBundleMapper, OrderDateMapper orderDateMapper, PassRefactoredMapper passMapper) {
        this.passBundleMapper = passBundleMapper;
        this.orderDateMapper = orderDateMapper;
        this.passMapper = passMapper;
    }

    public OrderRefactored fromRequest(OrderRefactoredRequest request) {
        if (request.getPass() == null) {
            throw new InvalidFormatException();
        }

        OrderDate orderDate = orderDateMapper.fromString(request.getOrderDate());
        PassRefactored pass = passMapper.fromRequest(request.getPass());

        return new OrderRefactored(orderDate, pass);
    }

    public OrderResponse toResponse(Order order) {
        List<PassResponse> passes = passBundleMapper.toResponse(order.getPassBundle());

        float fullOrderPrice = order.getPrice().getValue().floatValue();
        float orderPrice = formatOrderPrice(fullOrderPrice);

        return new OrderResponse(orderPrice, passes);
    }

    private float formatOrderPrice(float fullOrderPrice) {
        BigDecimal orderPrice = BigDecimal.valueOf(fullOrderPrice);

        return orderPrice.setScale(2, RoundingMode.HALF_EVEN).floatValue();
    }
}
