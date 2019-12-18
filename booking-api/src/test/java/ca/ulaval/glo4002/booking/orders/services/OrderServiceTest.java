package ca.ulaval.glo4002.booking.orders.services;

import ca.ulaval.glo4002.booking.orders.domain.*;
import ca.ulaval.glo4002.booking.orders.domain.OrderRepository;
import ca.ulaval.glo4002.booking.orders.rest.OrderRequest;
import ca.ulaval.glo4002.booking.orders.rest.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.shuttles.trips.services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class OrderServiceTest {

	private OrderService service;
	private OrderRepository repository;
	private OrderFactory factory;
	private TripService tripService;
	private OxygenInventoryService oxygenInventoryService;
	private Order order;
	private List<Pass> passes;

	@BeforeEach
	void setUpService() {
		OrderMapper mapper = mock(OrderMapper.class);
		repository = mock(OrderRepository.class);
		tripService = mock(TripService.class);
		oxygenInventoryService = mock(OxygenInventoryService.class);

		service = new OrderService(repository, factory, mapper, tripService, oxygenInventoryService);
	}

	@BeforeEach
	void setUpOrder() {
	    passes = Collections.singletonList(mock(Pass.class));
		OrderNumber orderNumber = mock(OrderNumber.class);
		order = mock(Order.class);
		when(order.getOrderNumber()).thenReturn(orderNumber);
		when(order.getPasses()).thenReturn(passes);
		factory = mock(OrderFactory.class);
		when(factory.create(any(), any())).thenReturn(order);
	}

	@Test
	void order_shouldAddOrder() {
		OrderRequest orderRequest = mock(OrderRequest.class);

		service.order(orderRequest);

		verify(repository).addOrder(eq(order));
	}

	@Test
	void order_shouldOrderTrips() {
		OrderRequest orderRequest = mock(OrderRequest.class);

		service.order(orderRequest);

		verify(tripService).orderForPasses(eq(passes));
	}

	@Test
	void order_shouldOrderOxygenWithCorrectPasses() {
		OrderRequest orderRequest = mock(OrderRequest.class);
		List<Pass> expectedPasses = Collections.singletonList(mock(Pass.class));
		when(order.getPasses()).thenReturn(expectedPasses);

		service.order(orderRequest);

		verify(oxygenInventoryService).orderForPasses(eq(expectedPasses), any());
	}

	@Test
	void order_shouldOrderOxygenWithCorrectDate() {
		OrderRequest orderRequest = mock(OrderRequest.class);
		OrderDate expectedOrderDate = mock(OrderDate.class);
		when(order.getOrderDate()).thenReturn(expectedOrderDate);

		service.order(orderRequest);

		verify(oxygenInventoryService).orderForPasses(any(), eq(expectedOrderDate));
	}

	@Test
	void getByOrderNumber_shouldGetOrder() {
		OrderNumber orderNumber = new OrderNumber(new OrderIdentifier(1L), "VENDOR");
	    when(order.getOrderNumber()).thenReturn(orderNumber);
	    when(repository.getByOrderNumber(order.getOrderNumber())).thenReturn(order);

		service.getByOrderNumber(order.getOrderNumber().toString());

		verify(repository).getByOrderNumber(eq(order.getOrderNumber()));
	}
}