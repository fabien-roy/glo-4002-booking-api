package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.constants.DateConstants;
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
    private final ShuttleInventoryService shuttleInventoryService;
    private final OrderParser orderParser;

    public OrderServiceImpl(OrderRepository orderRepository, PassService passService, OxygenTankService oxygenTankService, ShuttleInventoryService shuttleInventoryService) {
        this.orderRepository = orderRepository;
        this.passService = passService;
        this.oxygenTankService = oxygenTankService;
        this.shuttleInventoryService = shuttleInventoryService;
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

    // TODO : This should not save order if something goes wrong
    @Override
    public Order order(Order order) {
        order.setPrice(getOrderPrice(order));

        OrderEntity savedOrder = orderRepository.save(orderParser.toEntity(order));

        List<Pass> passes = new ArrayList<>();
        passService.order(savedOrder, order.getPasses()).forEach(passes::add);
        Quality quality = passes.get(0).getCategory().getQuality();

        oxygenTankService.order(quality, order.getOrderDate().toLocalDate());

        if (passes.get(0).getOption().getId().equals(PassConstants.Options.PACKAGE_ID)) {
            shuttleInventoryService.order(quality, DateConstants.START_DATE, DateConstants.END_DATE, savedOrder.getPasses().get(0).getId());
        } else {
            savedOrder.getPasses().forEach(pass -> shuttleInventoryService.order(quality, pass.getEventDate(), pass.getEventDate(), pass.getId()));
        }

        savedOrder = orderRepository.update(savedOrder);

        return orderParser.parseEntity(savedOrder);
    }

    public double getOrderPrice(Order order){
        double rebate = 1.00d;
        double price = 0.00d;

        if(order.getPasses().get(0).getCategory() instanceof SupergiantPassCategory) {
            rebate = verifyIfSupergiantRebateApplies(order, rebate);
        }

        if(order.getPasses().get(0).getCategory() instanceof NebulaPassCategory) {
            rebate = verifyIfNebulaRebateApply(order, rebate);
        }

        for (Pass pass : order.getPasses()) {
            price += pass.getPrice();
        }

        return price * rebate;
    }

    private double verifyIfSupergiantRebateApplies(Order order, double rebate) {
        if (order.getPasses().size() >= PassConstants.Categories.SUPERGIANT_SINGLE_PASS_REBATE_THRESHOLD) {
            return PassConstants.Categories.SUPERGIANT_SINGLE_PASS_REBATE;
        }

        return rebate;
    }

    private double verifyIfNebulaRebateApply(Order order, double rebate) {
        if(order.getPasses().size() >= PassConstants.Categories.NEBULA_SINGLE_PASS_REBATE_THRESHOLD){
            return PassConstants.Categories.NEBULA_SINGLE_PASS_REBATE;
        }

        return rebate;
    }
}
