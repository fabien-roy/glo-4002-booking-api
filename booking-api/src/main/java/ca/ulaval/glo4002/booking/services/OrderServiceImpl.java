package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.domainobjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.entities.OrderEntity;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PassService passService;
    private final OxygenTankService oxygenTankService;
    private final OrderParser orderParser;

    public OrderServiceImpl(OrderRepository orderRepository, PassService passService, OxygenTankService oxygenTankService) {
        this.orderRepository = orderRepository;
        this.passService = passService;
        this.oxygenTankService = oxygenTankService;
        this.orderParser = new OrderParser();
    }

    @Override
    public Order findById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        return orderParser.parseEntity(orderEntity);
    }

    @Override
    public Iterable<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        orderRepository.findAll().forEach(orderEntity -> orders.add(orderParser.parseEntity(orderEntity)));

        return orders;
    }

    // TODO : Check it nothing was saved to db if there is an error
    @Override
    public Order order(Order order) {
        List<Pass> passes = new ArrayList<>();
        passService.order(passService.getPasses(order.getOrderItems())).forEach(passes::add);
        Quality quality = passes.get(0).getCategory().getQuality();

        List<OxygenTank> oxygenTanks = new ArrayList<>();
        oxygenTankService.order(quality, order.getOrderDate().toLocalDate()).forEach(oxygenTanks::add);

        // TODO : Order Shuttle
        List<Shuttle> shuttles = new ArrayList<>();

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.addAll(passes);
        orderItems.addAll(oxygenTanks);
        orderItems.addAll(shuttles);
        order.setOrderItems(orderItems);

        return orderParser.parseEntity(orderRepository.save(orderParser.toEntity(order)));
    }
}
