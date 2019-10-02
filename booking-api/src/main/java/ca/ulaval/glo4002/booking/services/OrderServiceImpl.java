package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.domainobjects.passes.Pass;
import ca.ulaval.glo4002.booking.domainobjects.passes.categories.NebulaPassCategory;
import ca.ulaval.glo4002.booking.domainobjects.passes.categories.SupergiantPassCategory;
import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;
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
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id.toString()));

        return orderParser.parseEntity(orderEntity);
    }

    @Override
    public Iterable<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        orderRepository.findAll().forEach(orderEntity -> orders.add(orderParser.parseEntity(orderEntity)));

        return orders;
    }

    @Override
    public Order order(Order order) {
        OrderEntity savedOrder = orderRepository.save(orderParser.toEntity(order));

        List<Pass> passes = new ArrayList<>();
        passService.order(savedOrder, order.getPasses()).forEach(passes::add);
        Quality quality = passes.get(0).getCategory().getQuality();

        // TODO : Send savedOrder (OrderEntity) to service
        oxygenTankService.order(quality, order.getOrderDate().toLocalDate());

        // TODO : Order Shuttle
        // List<Shuttle> shuttles = new ArrayList<>();

        savedOrder = orderRepository.update(savedOrder);

        return orderParser.parseEntity(savedOrder);
    }

    public Double getOrderPrice(Order order){

        Double rebate = 1d;
        Double price = 0d;

        if(order.getPasses().get(0).getCategory() instanceof SupergiantPassCategory) {
            rebate = verifyIfSupergiantRebateApplies(order, rebate);
        }

        if(order.getPasses().get(0).getCategory() instanceof NebulaPassCategory) {
            rebate = verifyIfNebulaRebateApply(order, rebate);
        }

        for (Pass p:
                order.getPasses()) {
            price += p.getPrice();
        }

        return price * rebate;
    }

    private Double verifyIfSupergiantRebateApplies(Order order, Double rebate) {
        if (order.getPasses().size() >= PassConstants.Categories.SUPERGIANT_SINGLE_PASS_REBATE_THRESHOLD) {
            rebate = PassConstants.Categories.SUPERGIANT_SINGLE_PASS_REBATE;
        }
        return rebate;
    }

    private Double verifyIfNebulaRebateApply(Order order, Double rebate) {
        if(order.getPasses().size() >= PassConstants.Categories.NEBULA_SINGLE_PASS_REBATE_THRESHOLD){
            rebate = PassConstants.Categories.NEBULA_SINGLE_PASS_REBATE;
        }
        return rebate;
    }
}
