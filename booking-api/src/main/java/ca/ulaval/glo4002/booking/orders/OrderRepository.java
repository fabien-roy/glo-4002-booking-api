package ca.ulaval.glo4002.booking.orders;

import ca.ulaval.glo4002.booking.orders.Order;
import ca.ulaval.glo4002.booking.orders.OrderNumber;

public interface OrderRepository {

    Order getByOrderNumber(OrderNumber orderNumber);

    void addOrder(Order order);
}
