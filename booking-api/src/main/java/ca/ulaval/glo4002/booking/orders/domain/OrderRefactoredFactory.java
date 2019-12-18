package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.passes.domain.PassNumberGenerator;

import javax.inject.Inject;

public class OrderRefactoredFactory {

    // TODO : Maybe price calculation should be in factory

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

        order.getPasses().forEach(pass -> {
            long passNumber = passNumberGenerator.generate();
            pass.setNumber(passNumber);
        });

        return order;
    }
}
