package ca.ulaval.glo4002.booking.orders.infrastructure;

import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;

import java.util.List;

public interface OrderRepository {

    Order getByOrderNumber(OrderNumber orderNumber);

    void addOrder(Order order);

    List<Order> findAll();
}
