package ca.ulaval.glo4002.booking;

import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.dao.OrderDao;
import ca.ulaval.glo4002.booking.dao.PassDao;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.parsers.PassListParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.PassRepository;
import ca.ulaval.glo4002.booking.services.OrderService;
import org.glassfish.jersey.server.ResourceConfig;

// TODO : Use hk2 for injection
public class BookingResourceConfig extends ResourceConfig {

    OrderDao orderDao;

    OrderRepository orderRepository;

    OrderService orderService;

    PassFactory passFactory;

    PassListParser passListParser;
    OrderParser orderParser;

    OrderController orderController;

    public BookingResourceConfig() {
        addPackages();

        setUpDaos();
        setUpRepositories();
        setUpServices();
        setUpFactories();
        setUpParser();
        setUpControllers();

        registerResources();
    }

    private void addPackages() {
        packages("ca.ulaval.glo4002.booking");
    }

    private void setUpDaos() {
        orderDao = new OrderDao();
    }

    private void setUpRepositories() {
        orderRepository = new OrderRepository();
    }

    private void setUpServices() {
        orderService = new OrderService(orderRepository);
    }

    private void setUpFactories() {
        passFactory = new PassFactory();
    }

    private void setUpParser() {
        passListParser = new PassListParser(passFactory);
        orderParser = new OrderParser(passListParser);
    }

    private void setUpControllers() {
        orderController = new OrderController(orderService, orderRepository, orderParser);
    }

    private void registerResources() {
        register(orderController);
    }
}
