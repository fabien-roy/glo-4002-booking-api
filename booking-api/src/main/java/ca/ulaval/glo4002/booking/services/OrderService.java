package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.parsers.OrderMapper;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

public class OrderService {

    private OrderRepository repository;
    private OrderFactory factory;
    private OrderMapper mapper;

    public OrderService(OrderRepository repository, OrderFactory factory, OrderMapper mapper) {
        this.repository = repository;
        this.factory = factory;
        this.mapper = mapper;
    }

    public OrderWithPassesAsPassesDto order(OrderWithPassesAsEventDatesDto orderDto) {
        Order order = factory.buildWithDto(orderDto);

        repository.addOrder(order);

        return mapper.toDto(order);
    }

    // TODO getByOrderNumber
    public OrderWithPassesAsPassesDto getByOrderNumber(String requestedOrderNumber) {
        return null;
    }
}
