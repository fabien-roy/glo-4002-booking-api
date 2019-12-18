package ca.ulaval.glo4002.booking.orders.infrastructure;

import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;
import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.OrderNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {

	private List<Order> orders;

	public InMemoryOrderRepository() {
		orders = new ArrayList<>();
	}

	@Override
	public Order getByOrderNumber(OrderNumber orderNumber) {
		Optional<Order> foundOrder = orders.stream().filter(order -> order.getOrderNumber().equals(orderNumber)).findAny();

		if (!foundOrder.isPresent()) {
			throw new OrderNotFoundException(orderNumber.toString());
		}

		return foundOrder.get();
	}

	@Override
	public void addOrder(Order order) {
		orders.add(order);
	}

	@Override
	public List<Order> findAll() {
	    return orders;
	}
}
