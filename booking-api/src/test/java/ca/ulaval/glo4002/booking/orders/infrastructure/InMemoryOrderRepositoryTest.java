package ca.ulaval.glo4002.booking.orders.infrastructure;

import ca.ulaval.glo4002.booking.orders.domain.OrderIdentifier;
import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;
import ca.ulaval.glo4002.booking.orders.domain.OrderRefactored;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InMemoryOrderRepositoryTest {

	private OrderRepository repository;

	@BeforeEach
	void setUpRepository() {
		repository = new InMemoryOrderRepository();
	}

	@Test
	void getOrderNumber_shouldThrowOrderNotFoundException_whenThereIsNoOrder() {
		OrderNumber aNonExistentOrderNumber = new OrderNumber(new OrderIdentifier(1L), "VENDOR");

		assertThrows(OrderNotFoundException.class, () -> repository.getByOrderNumber(aNonExistentOrderNumber));
	}

	@Test
	void getOrderNumber_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
		OrderNumber nonExistentOrderNumber = new OrderNumber(new OrderIdentifier(1L), "VENDOR");
		OrderNumber orderNumber = new OrderNumber(new OrderIdentifier(2L), "VENDOR");
		OrderRefactored order = mock(OrderRefactored.class);
		when(order.getOrderNumber()).thenReturn(orderNumber);
		repository.addOrder(order);

		assertThrows(OrderNotFoundException.class, () -> repository.getByOrderNumber(nonExistentOrderNumber));
	}

	@Test
	void getByOrderNumber_shouldReturnOrder() {
		OrderNumber orderNumber = new OrderNumber(new OrderIdentifier(2L), "VENDOR");
		OrderRefactored order = mock(OrderRefactored.class);
		when(order.getOrderNumber()).thenReturn(orderNumber);
		repository.addOrder(order);

		OrderRefactored foundOrder = repository.getByOrderNumber(orderNumber);

		assertEquals(order, foundOrder);
	}

	@Test
	void addOrder_shouldAddOrder() {
		OrderRefactored order = mock(OrderRefactored.class);
		repository.addOrder(order);

		List<OrderRefactored> orders = repository.findAll();

		assertTrue(orders.contains(order));
	}

	@Test
	void findAll_shouldReturnAllOrders() {
		OrderRefactored order = mock(OrderRefactored.class);
		OrderRefactored otherOrder = mock(OrderRefactored.class);
		repository.addOrder(order);
		repository.addOrder(otherOrder);

		List<OrderRefactored> orders = repository.findAll();

		assertTrue(orders.contains(order));
		assertTrue(orders.contains(otherOrder));
	}
}