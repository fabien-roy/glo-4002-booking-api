package ca.ulaval.glo4002.booking.dao;

import ca.ulaval.glo4002.booking.domain.Id;
import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.exceptions.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao implements Dao<Order> {

    private List<Order> orders;

    public OrderDao() {
        orders = new ArrayList<>();
    }

    @Override
    public Optional<Order> get(Id id) {
        Optional<Order> foundOrder = orders.stream().filter(order -> order.getId().equals(id)).findAny();

        if (!foundOrder.isPresent()) {
            throw new OrderNotFoundException(id.getValue().toString());
        }

        return foundOrder;
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public void save(Order order) {
        if (orders.contains(order)) {
            throw new OrderAlreadyCreatedException(order.getId().getValue().toString());
        }

        orders.add(order);
    }
}
