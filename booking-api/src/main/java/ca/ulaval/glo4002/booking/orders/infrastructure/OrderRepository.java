package ca.ulaval.glo4002.booking.orders.infrastructure;

import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;
import ca.ulaval.glo4002.booking.orders.domain.OrderRefactored;

import java.util.List;

public interface OrderRepository {

    OrderRefactored getByOrderNumber(OrderNumber orderNumber);

    void addOrder(OrderRefactored order);

    List<OrderRefactored> findAll();
}
