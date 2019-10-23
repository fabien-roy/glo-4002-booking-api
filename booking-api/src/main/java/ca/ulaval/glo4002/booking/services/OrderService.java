package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

public class OrderService {

    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order order(Order order) {
        repository.addOrder(order);

        return order;
    }
}
