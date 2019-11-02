package ca.ulaval.glo4002.booking.repositories;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.exceptions.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {

    private List<Order> orders;

    public InMemoryOrderRepository() {
        orders = new ArrayList<>();
    }

    public Optional<Order> getByOrderNumber(OrderNumber orderNumber) {
        Optional<Order> foundOrder = orders.stream().filter(order -> order.getOrderNumber().equals(orderNumber)).findAny();

        if (!foundOrder.isPresent()) {
            throw new OrderNotFoundException(orderNumber.toString());
        }

        return foundOrder;
    }

    public void addOrder(Order order) {
        if (orders.contains(order)) {
            throw new OrderAlreadyCreatedException(order.getOrderNumber().toString());
        }

        orders.add(order);
    }
}
