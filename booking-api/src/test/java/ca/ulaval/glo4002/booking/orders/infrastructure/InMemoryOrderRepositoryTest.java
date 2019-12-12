package ca.ulaval.glo4002.booking.orders.infrastructure;

import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.orders.domain.OrderIdentifier;
import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.OrderNotFoundException;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
		OrderNumber aNonExistentOrderNumber = new OrderNumber(new OrderIdentifier(1L), "VENDOR");
		OrderNumber anOrderNumber = new OrderNumber(new OrderIdentifier(2L), "VENDOR");
		OrderDate anOrderDate = mock(OrderDate.class);
		PassBundle aPassBundle = mock(PassBundle.class);
		Order anOrder = new Order(anOrderNumber, anOrderDate, aPassBundle);
		repository.addOrder(anOrder);

		assertThrows(OrderNotFoundException.class, () -> repository.getByOrderNumber(aNonExistentOrderNumber));
	}

	@Test
	void getByOrderNumber_shouldReturnOrder() {
		OrderNumber anOrderNumber = new OrderNumber(new OrderIdentifier(1L), "VENDOR");
		OrderDate anOrderDate = mock(OrderDate.class);
		PassBundle aPassBundle = mock(PassBundle.class);
		Order anOrder = new Order(anOrderNumber, anOrderDate, aPassBundle);
		repository.addOrder(anOrder);

		Order foundOrder = repository.getByOrderNumber(anOrderNumber);

		assertEquals(anOrderNumber, foundOrder.getOrderNumber());
	}

	@Test
	void addOrder_shouldAddOrder() {
		OrderNumber anOrderNumber = new OrderNumber(new OrderIdentifier(2L), "VENDOR");
		OrderDate anOrderDate = mock(OrderDate.class);
		PassBundle aPassBundle = mock(PassBundle.class);
		Order anOrder = new Order(anOrderNumber, anOrderDate, aPassBundle);

		repository.addOrder(anOrder);

		assertTrue(repository.findAll().contains(anOrder));
	}

	@Test
	void findAll_shouldReturnEveryOrder() {
		OrderNumber anOrderNumber = new OrderNumber(new OrderIdentifier(2L), "VENDOR");
		OrderDate anOrderDate = mock(OrderDate.class);
		PassBundle aPassBundle = mock(PassBundle.class);
		Order anOrder = new Order(anOrderNumber, anOrderDate, aPassBundle);
		repository.addOrder(anOrder);

		assertEquals(1, repository.findAll().size());
	}
}