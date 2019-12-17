package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.passes.domain.PassNumberGenerator;

import javax.inject.Inject;

public class OrderRefactoredFactory {

    private final OrderIdentifierGenerator orderIdentifierGenerator;
    private final PassNumberGenerator passNumberGenerator;

    @Inject
    public OrderRefactoredFactory(OrderIdentifierGenerator orderIdentifierGenerator, PassNumberGenerator passNumberGenerator) {
        this.orderIdentifierGenerator = orderIdentifierGenerator;
        this.passNumberGenerator = passNumberGenerator;
    }

    public OrderRefactored create(OrderRefactored order, String vendorCode) {
        OrderIdentifier orderIdentifier = orderIdentifierGenerator.generate();
        OrderNumber orderNumber = new OrderNumber(orderIdentifier, vendorCode);
        order.setOrderNumber(orderNumber);

        long passNumber = passNumberGenerator.generate();
        order.setPassNumber(passNumber);

        return order;
    }
}
