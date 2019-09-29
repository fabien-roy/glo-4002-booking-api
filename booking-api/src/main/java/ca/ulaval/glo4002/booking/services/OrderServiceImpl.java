package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderParser orderParser;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.orderParser = new OrderParser();
    }

    @Override
    public Order findById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
        		.orElseThrow(OrderNotFoundException::new);

        return orderParser.parseEntity(orderEntity);
    }

    @Override
    public Iterable<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        orderRepository.findAll()
        .forEach(orderEntity -> orders.add(orderParser.parseEntity(orderEntity)));

        return orders;
    }

    @Override
    public Order save(Order order) {
    	
        orderRepository.save(orderParser.toEntity(order));

        return order;
    }

    @Override
    public Iterable<Order> saveAll(Iterable<Order> objects) {
        throw new UnusedMethodException();
    }
}
