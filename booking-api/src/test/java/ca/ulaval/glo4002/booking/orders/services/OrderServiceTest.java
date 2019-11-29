package ca.ulaval.glo4002.booking.orders.services;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderDate;
import ca.ulaval.glo4002.booking.orders.domain.OrderFactory;
import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;
import ca.ulaval.glo4002.booking.orders.infrastructure.OrderRepository;
import ca.ulaval.glo4002.booking.orders.rest.OrderRequest;
import ca.ulaval.glo4002.booking.orders.rest.OrderResponse;
import ca.ulaval.glo4002.booking.orders.rest.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService;
import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassCategory;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;
import ca.ulaval.glo4002.booking.passes.rest.PassBundleRequest;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.shuttles.trips.services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

	private OrderService service;
	private OrderRepository repository;
	private OrderFactory factory;
	private TripService tripService;
	private OxygenInventoryService oxygenInventoryService;

	@BeforeEach
	void setUpService() {
		OrderMapper mapper = new OrderMapper(new PassBundleMapper());
		repository = mock(OrderRepository.class);
		factory = mock(OrderFactory.class);
		tripService = mock(TripService.class);
		oxygenInventoryService = mock(OxygenInventoryService.class);

		service = new OrderService(repository, factory, mapper, tripService, oxygenInventoryService);
	}

	@Test
	void order_shouldAddOrder() {
		String aVendorCode = "aVendorCode";
		Order anOrder = mockOrder(aVendorCode, PassCategories.SUPERNOVA);
		OrderRequest orderRequest = mockOrderRequest(aVendorCode);

		service.order(orderRequest);

		verify(repository).addOrder(eq(anOrder));
	}

	@Test
	void order_shouldOrderTripsWithCorrectPassCategory() {
		String aVendorCode = "aVendorCode";
		OrderRequest orderRequest = mockOrderRequest(aVendorCode);
		PassCategories expectedPassCategory = PassCategories.SUPERNOVA;
		mockOrder(aVendorCode, expectedPassCategory);

		service.order(orderRequest);

		verify(tripService).orderForPasses(eq(expectedPassCategory), any());
	}

	@Test
	void order_shouldOrderTripsWithCorrectPasses() {
		String aVendorCode = "aVendorCode";
		OrderRequest orderRequest = mockOrderRequest(aVendorCode);
		List<Pass> expectedPasses = new ArrayList<>();
		mockOrder(aVendorCode, PassCategories.SUPERNOVA, expectedPasses);

		service.order(orderRequest);

		verify(tripService).orderForPasses(any(), eq(expectedPasses));
	}

	@Test
	void order_shouldOrderOxygenWithCorrectPassCategory() {
		String aVendorCode = "aVendorCode";
		OrderRequest orderRequest = mockOrderRequest(aVendorCode);
		PassCategories expectedPassCategory = PassCategories.SUPERNOVA;
		mockOrder(aVendorCode, expectedPassCategory);

		service.order(orderRequest);

		verify(oxygenInventoryService).orderForPasses(eq(expectedPassCategory), any(), any());
	}

	@Test
	void order_shouldOrderOxygenWithCorrectPasses() {
		String aVendorCode = "aVendorCode";
		OrderRequest orderRequest = mockOrderRequest(aVendorCode);
		List<Pass> expectedPasses = new ArrayList<>();
		mockOrder(aVendorCode, PassCategories.SUPERNOVA, expectedPasses);

		service.order(orderRequest);

		verify(oxygenInventoryService).orderForPasses(any(), eq(expectedPasses), any());
	}

	@Test
	void getByOrderNumber_shouldGetOrder() {
		OrderNumber anOrderNumber = new OrderNumber(new Number(1L), "Vendor");
		Pass aPass = mock(Pass.class);
		when(aPass.getPassNumber()).thenReturn(new Number(1L));
		when(aPass.getPrice()).thenReturn(new Money(new BigDecimal(0.0)));
		PassBundle passBundle = new PassBundle(
				Collections.singletonList(aPass),
				new PassCategory(PassCategories.SUPERNOVA, null),
				PassOptions.PACKAGE
		);
		OrderDate aOrderDate = new OrderDate(FestivalConfiguration.getDefaultStartEventDate().minusDays(1).toLocalDateTime());
		Order order = new Order(anOrderNumber, aOrderDate, passBundle);
		when(repository.getByOrderNumber(anOrderNumber)).thenReturn(order);

		OrderResponse orderResponse = service.getByOrderNumber(anOrderNumber.toString());

		assertEquals(order.getPrice().getValue().doubleValue(), orderResponse.getOrderPrice());
	}

	private Order mockOrder(String vendorCode, PassCategories passCategory) {
		return mockOrder(vendorCode, passCategory, new ArrayList<>());
	}

	private Order mockOrder(String vendorCode, PassCategories passCategory, List<Pass> passes) {
		Order order = mock(Order.class);
		when(order.getOrderNumber()).thenReturn(new OrderNumber(new Number(1L), vendorCode));

		PassBundle passBundle = mock(PassBundle.class);
		when(passBundle.getCategory()).thenReturn(passCategory);
		when(passBundle.getPasses()).thenReturn(passes);
		when(order.getPassBundle()).thenReturn(passBundle);
		when(factory.create(any())).thenReturn(order);

		return order;
	}

	private OrderRequest mockOrderRequest(String vendorCode) {
		return new OrderRequest("aOrderDate", vendorCode, mock(PassBundleRequest.class));
	}
}