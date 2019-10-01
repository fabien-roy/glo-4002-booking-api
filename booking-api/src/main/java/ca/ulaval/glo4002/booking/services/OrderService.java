package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.orders.Order;

public interface OrderService extends Service<Order> {

    Order findById(Long id);

    Iterable<Order> findAll();

    Order order(Order order);
}
