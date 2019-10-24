package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderDate;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.exceptions.orders.InvalidOrderFormatException;
import ca.ulaval.glo4002.booking.parsers.PassListFactory;

public class OrderFactory {

    private NumberGenerator numberGenerator;
    private PassListFactory passListFactory;

    public OrderFactory(NumberGenerator numberGenerator, PassListFactory passListFactory) {
        this.numberGenerator = numberGenerator;
        this.passListFactory = passListFactory;
    }

    public Order build(OrderWithPassesAsEventDatesDto orderDto) {
        if (orderDto.getPasses() == null) {
            throw new InvalidOrderFormatException();
        }

        OrderNumber orderNumber = new OrderNumber(numberGenerator.generate(), orderDto.getVendorCode());
        OrderDate orderDate = new OrderDate(orderDto.getOrderDate());
        PassList passList = passListFactory.parseDto(orderDto.getPasses());

        return new Order(orderNumber, orderDate, passList);
    }
}
