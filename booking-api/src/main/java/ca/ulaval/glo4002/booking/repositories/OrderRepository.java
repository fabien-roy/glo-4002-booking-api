package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;

import java.util.Optional;

public interface OrderRepository {

    Optional<Order> getByOrderNumber(OrderNumber orderNumber);

    void addOrder(Order order);
}
