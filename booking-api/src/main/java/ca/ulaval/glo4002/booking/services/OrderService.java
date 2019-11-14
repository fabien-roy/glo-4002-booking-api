package ca.ulaval.glo4002.booking.services;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.dto.orders.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.orders.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

public class OrderService {

	private final OrderRepository repository;
	private final OrderFactory factory;
	private final OrderMapper mapper;
	private final TripService tripService;
	private final OxygenInventoryService oxygenInventoryService;

	@Inject
	public OrderService(OrderRepository repository, OrderFactory factory, OrderMapper mapper, TripService tripService,
			OxygenInventoryService oxygenInventoryService) {
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
		oxygenInventoryService.orderForPasses(order.getPassBundle().getCategory(), order.getPasses());
		OrderNumber orderNumber = order.getOrderNumber();

		return orderNumber.toString();
	}

	public OrderWithPassesAsPassesDto getByOrderNumber(String requestedOrderNumber) {
		OrderNumber orderNumber = new OrderNumber(requestedOrderNumber);

		Order order = repository.getByOrderNumber(orderNumber);

		return mapper.toDto(order);
	}
}
