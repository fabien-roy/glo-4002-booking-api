package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order getByOrderNumber(OrderNumber orderNumber);
    
    List<Order> findAll();

    void addOrder(Order order);
}
