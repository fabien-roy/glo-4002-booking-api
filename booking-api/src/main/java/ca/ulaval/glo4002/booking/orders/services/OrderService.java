package ca.ulaval.glo4002.booking.orders.services;

import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;
import ca.ulaval.glo4002.booking.orders.domain.OrderRefactored;
import ca.ulaval.glo4002.booking.orders.domain.OrderRefactoredFactory;
import ca.ulaval.glo4002.booking.orders.infrastructure.OrderRepository;
import ca.ulaval.glo4002.booking.orders.rest.OrderRefactoredRequest;
import ca.ulaval.glo4002.booking.orders.rest.OrderResponse;
import ca.ulaval.glo4002.booking.orders.rest.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService;
import ca.ulaval.glo4002.booking.shuttles.trips.services.TripService;

import javax.inject.Inject;

public class OrderService {

	private final OrderRepository repository;
	private final OrderRefactoredFactory factory;
	private final OrderMapper mapper;
	private final TripService tripService;
	private final OxygenInventoryService oxygenInventoryService;

	@Inject
	public OrderService(OrderRepository repository, OrderRefactoredFactory factory, OrderMapper mapper, TripService tripService, OxygenInventoryService oxygenInventoryService) {
		this.repository = repository;
		this.factory = factory;
		this.mapper = mapper;
		this.tripService = tripService;
		this.oxygenInventoryService = oxygenInventoryService;
	}

	public String order(OrderRefactoredRequest orderRequest) {
		OrderRefactored order = mapper.fromRequest(orderRequest);
		order = factory.create(order, orderRequest.getVendorCode());

		repository.addOrder(order);
		tripService.orderForPasses(order.getPasses());
		oxygenInventoryService.orderForPasses(order.getPasses(), order.getOrderDate());
		OrderNumber orderNumber = order.getOrderNumber();

		return orderNumber.toString();
	}

	public OrderResponse getByOrderNumber(String requestedOrderNumber) {
		OrderNumber orderNumber = new OrderNumber(requestedOrderNumber); // TODO : A Mapper should parse a String to a OrderNumber

		OrderRefactored order = repository.getByOrderNumber(orderNumber);

		return mapper.toResponse(order);
	}
}
