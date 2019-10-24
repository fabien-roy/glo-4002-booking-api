package ca.ulaval.glo4002.booking.dao;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao {

    private List<Order> orders;
    private Number nextId;

    public OrderDao() {
        orders = new ArrayList<>();
        nextId = new Number(0L);
    }

    public Optional<Order> get(Number id) {
        /*
        Optional<Order> foundOrder = orders.stream().filter(order -> order.getId().equals(id)).findAny();

        if (!foundOrder.isPresent()) {
            throw new OrderNotFoundException(id.getValue().toString());
        }

        return foundOrder;
        */
        return null;
    }

    public List<Order> getAll() {
        return orders;
    }

    public void save(Order order) {
        if (orders.contains(order)) {
            throw new OrderAlreadyCreatedException(order.getOrderNumber());
        }

        order.setNumber(nextId);
        nextId = new Number(nextId.getValue() + 1);

        orders.add(order);
    }
}
