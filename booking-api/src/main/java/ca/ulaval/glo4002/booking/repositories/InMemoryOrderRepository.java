package ca.ulaval.glo4002.booking.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;

public class InMemoryOrderRepository implements OrderRepository {

	private List<Order> orders;

	public InMemoryOrderRepository() {
		orders = new ArrayList<>();
	}

	public Order getByOrderNumber(OrderNumber orderNumber) {
		Optional<Order> foundOrder = orders.stream().filter(order -> order.getOrderNumber().equals(orderNumber))
				.findAny();

		if (!foundOrder.isPresent()) {
			throw new OrderNotFoundException(orderNumber.toString());
		}

		return foundOrder.get();
	}

	public void addOrder(Order order) {
		orders.add(order);
	}
}
