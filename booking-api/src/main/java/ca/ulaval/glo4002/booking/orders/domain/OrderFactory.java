package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.orders.rest.OrderRequest;
import ca.ulaval.glo4002.booking.passes.domain.PassBundleFactory;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderFactory {

    private final NumberGenerator numberGenerator;
    private final OrderDateFactory orderDateFactory;
    private final PassBundleFactory passBundleFactory;

    @Inject
    public OrderFactory(NumberGenerator numberGenerator, OrderDateFactory orderDateFactory, PassBundleFactory passBundleFactory) {
        this.numberGenerator = numberGenerator;
        this.orderDateFactory = orderDateFactory;
        this.passBundleFactory = passBundleFactory;
    }

    public Order create(OrderRequest orderRequest) {
        // TODO : Make passes nonNullable in Requests
        if (orderRequest.getPasses() == null) {
            throw new InvalidFormatException();
        }

        OrderNumber orderNumber = new OrderNumber(numberGenerator.generate(), orderRequest.getVendorCode());
        OrderDate orderDate = orderDateFactory.create(orderRequest.getOrderDate()); // TODO : Test this call
        PassBundle passBundle = passBundleFactory.create(orderRequest.getPasses());

        return new Order(orderNumber, orderDate, passBundle);
    }
}
