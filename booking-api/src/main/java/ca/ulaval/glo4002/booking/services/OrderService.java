package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

public class OrderService {

    private OrderRepository repository;
    private OrderFactory factory;

    public OrderService(OrderRepository repository, OrderFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public Order order(OrderWithPassesAsEventDatesDto orderDto) {
        Order order = factory.build(orderDto);

        repository.addOrder(order);

        // TODO : Use OrderMapper (parser) to generate DTO (since we now have the information)

        // TODO : Return DTO
        return order;
    }
}
