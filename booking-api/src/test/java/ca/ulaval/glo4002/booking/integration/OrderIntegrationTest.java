package ca.ulaval.glo4002.booking.integration;

import ca.ulaval.glo4002.booking.configuration.Configuration;
import ca.ulaval.glo4002.booking.events.EventDateFactory;
import ca.ulaval.glo4002.booking.errors.ExceptionMapper;
import ca.ulaval.glo4002.booking.orders.*;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.oxygen.*;
import ca.ulaval.glo4002.booking.oxygen.history.InMemoryOxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.InMemoryOxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.passes.*;
import ca.ulaval.glo4002.booking.errors.ErrorDto;
import ca.ulaval.glo4002.booking.errors.InvalidFormatException;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundle;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleDto;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleFactory;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryService;
import ca.ulaval.glo4002.booking.shuttles.trips.TripService;
import ca.ulaval.glo4002.booking.shuttles.trips.InMemoryTripRepository;
import ca.ulaval.glo4002.booking.shuttles.ShuttleFactory;
import ca.ulaval.glo4002.booking.shuttles.trips.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class OrderIntegrationTest {

    private OrderController controller;
    private Configuration configuration;
    private OrderRepository repository;
    private PassBundleFactory passBundleFactory;

    @BeforeEach
    public void setUpController() {
        configuration = new Configuration();

        NumberGenerator numberGenerator = new NumberGenerator();

        EventDateFactory eventDateFactory = new EventDateFactory(configuration);
        PassFactory passFactory = new PassFactory(numberGenerator, eventDateFactory);
        passBundleFactory = new PassBundleFactory(passFactory);
        ShuttleFactory shuttleFactory = new ShuttleFactory();
        OxygenFactory oxygenFactory = new OxygenFactory(configuration);

        OrderFactory orderFactory = new OrderFactory(configuration, numberGenerator, passBundleFactory);

        TripRepository tripRepository = new InMemoryTripRepository(shuttleFactory);
        OxygenInventoryRepository oxygenInventoryRepository = new InMemoryOxygenInventoryRepository();
        OxygenHistoryRepository oxygenHistoryRepository = new InMemoryOxygenHistoryRepository();
        repository = new InMemoryOrderRepository();

        OxygenTankProducer oxygenTankProducer = new OxygenTankProducer(oxygenInventoryRepository, oxygenHistoryRepository, oxygenFactory);

        PassBundleMapper passBundleMapper = new PassBundleMapper();
        OrderMapper orderMapper = new OrderMapper(passBundleMapper);

        TripService tripService = new TripService(configuration, tripRepository, shuttleFactory);
        OxygenInventoryService oxygenInventoryService = new OxygenInventoryService(configuration, oxygenFactory, oxygenTankProducer);
        OrderService orderService = new OrderService(repository, orderFactory, orderMapper, tripService, oxygenInventoryService);

        ExceptionMapper exceptionMapper = new ExceptionMapper();
        controller = new OrderController(exceptionMapper, orderService);
    }

    @Test
    public void getByOrderNumber_shouldReturnOrder() {
        PassBundle passBundle = passBundleFactory.build(new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        ));
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                configuration.getMinimumEventDateToOrder().toLocalDateTime(),
                passBundle
        );
        repository.addOrder(order);

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
                configuration.getMinimumEventDateToOrder().toLocalDateTime(),
                mock(PassBundle.class)
        );
        repository.addOrder(order);
        Number aNonExistentOrderId = new Number(2L);
        OrderNumber aNonExistentOrderNumber = new OrderNumber(aNonExistentOrderId, "VENDOR");
        String expectedErrorDescription = OrderNotFoundException.DESCRIPTION.replace("{orderNumber}", aNonExistentOrderNumber.toString());

        ResponseEntity<?> response = controller.getByOrderNumber(aNonExistentOrderNumber.toString());
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(OrderNotFoundException.MESSAGE, errorDto.getError());
        assertEquals(expectedErrorDescription, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldAddOrder() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(configuration.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
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
                ZonedDateTime.of(configuration.getMinimumEventDateToOrder().toLocalDateTime().minusDays(1), ZoneId.systemDefault()).toString(),
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
                ZonedDateTime.of(configuration.getMaximumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault()).toString(),
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
