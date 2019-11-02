package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

import javax.inject.Inject;

public class OrderService {

    private final OrderRepository repository;
    private final OrderFactory factory;
    private final OrderMapper mapper;

    @Inject
    public OrderService(OrderRepository repository, OrderFactory factory, OrderMapper mapper) {
        this.repository = repository;
        this.factory = factory;
        this.mapper = mapper;
    }

    public String order(OrderWithPassesAsEventDatesDto orderDto) {
        Order order = factory.buildWithDto(orderDto);

        repository.addOrder(order);

        return order.getOrderNumber().toString();
    }

    public OrderWithPassesAsPassesDto getByOrderNumber(String requestedOrderNumber) {
        OrderNumber orderNumber = new OrderNumber(requestedOrderNumber);

        Order order = repository.getByOrderNumber(orderNumber).get(); // TODO : Is using get a good idea?

        return mapper.toDto(order);
    }
}
