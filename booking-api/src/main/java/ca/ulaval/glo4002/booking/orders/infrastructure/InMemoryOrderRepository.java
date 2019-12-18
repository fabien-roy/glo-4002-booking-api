package ca.ulaval.glo4002.booking.orders.infrastructure;

import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;
import ca.ulaval.glo4002.booking.orders.domain.OrderRefactored;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.OrderNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {

	private List<OrderRefactored> orders;

	public InMemoryOrderRepository() {
		orders = new ArrayList<>();
	}

	@Override
	public OrderRefactored getByOrderNumber(OrderNumber orderNumber) {
		Optional<OrderRefactored> foundOrder = orders.stream().filter(order -> order.getOrderNumber().equals(orderNumber)).findAny();

		if (!foundOrder.isPresent()) {
			throw new OrderNotFoundException(orderNumber.toString());
		}

		return foundOrder.get();
	}

	@Override
	public void addOrder(OrderRefactored order) {
		orders.add(order);
	}

	@Override
	public List<OrderRefactored> findAll() {
	    return orders;
	}
}
