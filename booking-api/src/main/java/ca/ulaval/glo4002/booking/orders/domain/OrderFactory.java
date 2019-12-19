package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.passes.domain.PassNumber;
import ca.ulaval.glo4002.booking.passes.domain.PassNumberGenerator;

import javax.inject.Inject;

public class OrderFactory {

    private final OrderIdentifierGenerator orderIdentifierGenerator;
    private final PassNumberGenerator passNumberGenerator;

    @Inject
    public OrderFactory(OrderIdentifierGenerator orderIdentifierGenerator, PassNumberGenerator passNumberGenerator) {
        this.orderIdentifierGenerator = orderIdentifierGenerator;
        this.passNumberGenerator = passNumberGenerator;
    }

    public Order create(Order order, String vendorCode) {
        OrderIdentifier orderIdentifier = orderIdentifierGenerator.generate();
        OrderNumber orderNumber = new OrderNumber(orderIdentifier, vendorCode);
        order.setOrderNumber(orderNumber);

        order.getPasses().forEach(pass -> {
            PassNumber passNumber = passNumberGenerator.generate();
            pass.setNumber(passNumber);
        });

        return order;
    }
}
