package ca.ulaval.glo4002.booking;

import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassListMapper;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.services.OrderService;
import org.glassfish.jersey.server.ResourceConfig;

// TODO : Use hk2 for injection
public class BookingResourceConfig extends ResourceConfig {

    NumberGenerator numberGenerator;

    OrderRepository orderRepository;

    PassListMapper passListMapper;
    PassFactory passFactory;
    OrderFactory orderFactory;

    OrderService orderService;

    OrderMapper orderMapper;

    OrderController orderController;

    public BookingResourceConfig() {
        addPackages();

        setUpGenerators();
        setUpRepositories();
        setUpFactories();
        setUpServices();
        setUpParser();
        setUpControllers();

        registerResources();
    }

    private void addPackages() {
        packages("ca.ulaval.glo4002.booking");
    }

    private void setUpGenerators() {
        numberGenerator = new NumberGenerator();
    }

    private void setUpRepositories() {
        orderRepository = new OrderRepository();
    }

    private void setUpFactories() {
        passListMapper = new PassListMapper(passFactory);
        passFactory = new PassFactory();
        orderFactory = new OrderFactory(numberGenerator, passListMapper);
    }

    private void setUpServices() {
        orderService = new OrderService(orderRepository, orderFactory, orderMapper);
    }

    private void setUpParser() {
        orderMapper = new OrderMapper(passListMapper);
    }

    private void setUpControllers() {
        orderController = new OrderController(orderService);
    }

    private void registerResources() {
        register(orderController);
    }
}
