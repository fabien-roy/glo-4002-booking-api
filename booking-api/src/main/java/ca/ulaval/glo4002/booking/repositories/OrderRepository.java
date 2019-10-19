package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.dao.OrderDao;
import ca.ulaval.glo4002.booking.domain.Order;

import java.util.Optional;

public class OrderRepository {

    OrderDao dao;

    public OrderRepository(OrderDao dao) {
        this.dao = dao;
    }

    public Optional<Order> getByOrderNumber(String orderNumber) {
        return dao.get(orderNumber);
    }

    public void addOrder(Order order) {
        dao.save(order);
    }
}
