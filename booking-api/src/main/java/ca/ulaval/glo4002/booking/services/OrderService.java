package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

public class OrderService {

    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order order(Order order) {
        // TODO : Receive DTO
        // TODO : Use OrderFactory to create order (since information has to be created)
        repository.addOrder(order);


        // TODO : Return DTO
        // TODO : Use OrderMapper (parser) to generate DTO (since we now have the information)

        return order;
    }
}
