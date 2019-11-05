package ca.ulaval.glo4002.booking.integration;

import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.PassBundle;
import ca.ulaval.glo4002.booking.dto.ErrorDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.dto.PassBundleDto;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.InvalidOrderDateException;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import ca.ulaval.glo4002.booking.factories.PassBundleFactory;
import ca.ulaval.glo4002.booking.factories.ShuttleFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.repositories.InMemoryOrderRepository;
import ca.ulaval.glo4002.booking.repositories.InMemoryTripRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.repositories.TripRepository;
import ca.ulaval.glo4002.booking.services.OrderService;
import ca.ulaval.glo4002.booking.services.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

// TODO : Integration tests should boot server on another port and use BookingResourceConfig to inject dependencies
// TODO : Exclude integration tests in maven

public class OrderIntegrationTest {

    private OrderController controller;
    private OrderRepository orderRepository;
    private PassBundleFactory passBundleFactory;

    @BeforeEach
    public void setUpController() {
        NumberGenerator numberGenerator = new NumberGenerator();

        PassFactory passFactory = new PassFactory(numberGenerator);
        passBundleFactory = new PassBundleFactory(passFactory);
        ShuttleFactory shuttleFactory = new ShuttleFactory();
        OrderFactory orderFactory = new OrderFactory(numberGenerator, passBundleFactory);

        TripRepository tripRepository = new InMemoryTripRepository(shuttleFactory);
        orderRepository = new InMemoryOrderRepository();

        PassBundleMapper passBundleMapper = new PassBundleMapper();
        OrderMapper orderMapper = new OrderMapper(passBundleMapper);

        TripService tripService = new TripService(tripRepository, shuttleFactory);
        OrderService orderService = new OrderService(orderRepository, orderFactory, orderMapper, tripService);

        controller = new OrderController(orderService);
    }

    @Test
    public void getByOrderNumber_shouldReturnOrder() {
        PassBundle passBundle = passBundleFactory.build(new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        ));
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                OrderFactory.START_DATE_TIME,
                passBundle
        );
        orderRepository.addOrder(order);

        ResponseEntity<?> response = controller.getByOrderNumber(order.getOrderNumber().toString());
        OrderWithPassesAsPassesDto orderDto = (OrderWithPassesAsPassesDto) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order.getPrice().getValue().doubleValue(), orderDto.getOrderPrice());
    }

    @Test
    public void getByOrderNumber_shouldReturnBadRequest_whenOrderNumberHasInvalidFormat() {
        String aOrderNumberWithInvalidFormat = "aOrderNumberWithInvalidFormat";

        ResponseEntity<?> response = controller.getByOrderNumber(aOrderNumberWithInvalidFormat);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void getByOrderNumber_shouldReturnNotFound_whenThereIsNoOrder() {
        Number aNonExistentOrderId = new Number(1L);
        OrderNumber aNonExistentOrderNumber = new OrderNumber(aNonExistentOrderId, "VENDOR");
        String expectedErrorDescription = OrderNotFoundException.DESCRIPTION.replace("{orderNumber}", aNonExistentOrderNumber.toString());

        ResponseEntity<?> response = controller.getByOrderNumber(aNonExistentOrderNumber.toString());
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(OrderNotFoundException.MESSAGE, errorDto.getError());
        assertEquals(expectedErrorDescription, errorDto.getDescription());
    }

    @Test
    public void getByOrderNumber_shouldReturnNotFound_whenOrderDoesNotExist() {
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                OrderFactory.START_DATE_TIME,
                mock(PassBundle.class)
        );
        orderRepository.addOrder(order);
        Number aNonExistentOrderId = new Number(2L);
        OrderNumber aNonExistentOrderNumber = new OrderNumber(aNonExistentOrderId, "VENDOR");
        String expectedErrorDescription = OrderNotFoundException.DESCRIPTION.replace("{orderNumber}", aNonExistentOrderNumber.toString());

        ResponseEntity<?> response = controller.getByOrderNumber(aNonExistentOrderNumber.toString());
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(OrderNotFoundException.MESSAGE, errorDto.getError());
        assertEquals(expectedErrorDescription, errorDto.getDescription());
    }

    // TODO : ACP : This also tests pass when there is only one pass. Is that wrong?
    @Test
    public void addOrder_shouldAddOrder() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get(HttpHeaders.LOCATION));
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenOrderDateIsUnderBounds() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME.minusDays(1), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidOrderDateException.MESSAGE, errorDto.getError());
        assertEquals(InvalidOrderDateException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenOrderDateIsOverBounds() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.END_DATE_TIME.plusDays(1), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidOrderDateException.MESSAGE, errorDto.getError());
        assertEquals(InvalidOrderDateException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenOrderDateIsInvalid() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                "anInvalidOrderDate",
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }
}
