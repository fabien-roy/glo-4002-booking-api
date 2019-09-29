package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.exceptions.UnusedMethodException;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderParser orderParser;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.orderParser = new OrderParser();
    }

    @Override
    public Order findById(Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);

        if (orderEntity.isPresent()) {
            return orderParser.parseEntity(orderEntity.get());
        } else {
            throw new OrderNotFoundException();
        }
    }

    @Override
    public Iterable<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        orderRepository.findAll().forEach(orderEntity -> orders.add(orderParser.parseEntity(orderEntity)));

        return orders;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderParser.toEntity(order);

        orderRepository.save(orderEntity);

        return order;
    }

    @Override
    public Iterable<Order> saveAll(Iterable<Order> objects) {
        throw new UnusedMethodException();
    }
}
