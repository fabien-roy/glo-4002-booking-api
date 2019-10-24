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

    PassDao passDao;
    OrderDao orderDao;

    PassRepository passRepository;
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
        passDao = new PassDao();
        orderDao = new OrderDao();
    }

    private void setUpRepositories() {
        passRepository = new PassRepository(passDao);
        orderRepository = new OrderRepository(orderDao, passRepository);
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
