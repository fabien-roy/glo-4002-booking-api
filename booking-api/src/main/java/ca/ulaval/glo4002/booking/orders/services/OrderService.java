package ca.ulaval.glo4002.booking.orders.services;

import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderFactory;
import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;
import ca.ulaval.glo4002.booking.orders.infrastructure.OrderRepository;
import ca.ulaval.glo4002.booking.orders.rest.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.orders.rest.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.orders.rest.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryService;
import ca.ulaval.glo4002.booking.shuttles.trips.TripService;

import javax.inject.Inject;

public class OrderService {

	private final OrderRepository repository;
	private final OrderFactory factory;
	private final OrderMapper mapper;
	private final TripService tripService;
	private final OxygenInventoryService oxygenInventoryService;

	@Inject
	public OrderService(OrderRepository repository, OrderFactory factory, OrderMapper mapper, TripService tripService, OxygenInventoryService oxygenInventoryService) {
		this.repository = repository;
		this.factory = factory;
		this.mapper = mapper;
		this.tripService = tripService;
		this.oxygenInventoryService = oxygenInventoryService;
	}

	public String order(OrderWithPassesAsEventDatesDto orderDto) {
		Order order = factory.build(orderDto);

		repository.addOrder(order);
		tripService.orderForPasses(order.getPassBundle().getCategory(), order.getPasses());
		oxygenInventoryService.orderForPasses(order.getPassBundle().getCategory(), order.getPasses(), order.getOrderDate());
		OrderNumber orderNumber = order.getOrderNumber();

		return orderNumber.toString();
	}

	public OrderWithPassesAsPassesDto getByOrderNumber(String requestedOrderNumber) {
		OrderNumber orderNumber = new OrderNumber(requestedOrderNumber);

		Order order = repository.getByOrderNumber(orderNumber);

		return mapper.toDto(order);
	}
}
