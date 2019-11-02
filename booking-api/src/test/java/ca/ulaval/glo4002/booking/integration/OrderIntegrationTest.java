package ca.ulaval.glo4002.booking.integration;

import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import ca.ulaval.glo4002.booking.factories.PassListFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassListMapper;
import ca.ulaval.glo4002.booking.repositories.InMemoryOrderRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO : Integration tests should boot server on another port and use BookingResourceConfig to inject dependencies
// TODO : Exclude integration tests in maven

public class OrderIntegrationTest {

    private OrderController controller;

    @BeforeEach
    public void setUpController() {
        OrderRepository orderRepository = new InMemoryOrderRepository();

        NumberGenerator numberGenerator = new NumberGenerator();

        PassFactory passFactory = new PassFactory();
        PassListFactory passListFactory = new PassListFactory(numberGenerator, passFactory);
        OrderFactory orderFactory = new OrderFactory(numberGenerator, passListFactory);

        PassListMapper passListMapper = new PassListMapper();
        OrderMapper orderMapper = new OrderMapper(passListMapper);

        OrderService orderService = new OrderService(orderRepository, orderFactory, orderMapper);

        controller = new OrderController(orderService);
    }

    // TODO : Add body validation to GET tests

    @Test
    public void getByOrderNumber_shouldReturnOrder() {

    }

    @Test
    public void getByOrderNumber_shouldReturnError_whenOrderNumberHasInvalidFormat() {
        String aOrderNumberWithInvalidFormat = "aOrderNumberWithInvalidFormat";

        ResponseEntity<?> response = controller.getByOrderNumber(aOrderNumberWithInvalidFormat);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getByOrderNumber_shouldReturnNotFound_whenThereIsNoOrder() {

    }

    @Test
    public void getByOrderNumber_shouldReturnNotFound_whenOrderDoesNotExist() {

    }

    // TODO : Write POST tests
}
