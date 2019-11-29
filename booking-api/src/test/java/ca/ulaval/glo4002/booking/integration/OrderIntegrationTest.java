package ca.ulaval.glo4002.booking.integration;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.orders.domain.Order;
import ca.ulaval.glo4002.booking.orders.domain.OrderFactory;
import ca.ulaval.glo4002.booking.orders.domain.OrderNumber;
import ca.ulaval.glo4002.booking.orders.infrastructure.InMemoryOrderRepository;
import ca.ulaval.glo4002.booking.orders.infrastructure.OrderRepository;
import ca.ulaval.glo4002.booking.orders.rest.OrderResource;
import ca.ulaval.glo4002.booking.orders.rest.OrderRequest;
import ca.ulaval.glo4002.booking.orders.rest.OrderResponse;
import ca.ulaval.glo4002.booking.orders.rest.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.orders.services.OrderService;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenFactory;
import ca.ulaval.glo4002.booking.oxygen.domain.OxygenTankProducer;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.passes.domain.PassFactory;
import ca.ulaval.glo4002.booking.passes.domain.PassOptions;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.InMemoryOxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.InMemoryOxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;
import ca.ulaval.glo4002.booking.passes.rest.PassBundleRequest;
import ca.ulaval.glo4002.booking.passes.domain.PassBundleFactory;
import ca.ulaval.glo4002.booking.passes.rest.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.oxygen.inventory.services.OxygenInventoryService;
import ca.ulaval.glo4002.booking.shuttles.trips.services.TripService;
import ca.ulaval.glo4002.booking.shuttles.trips.infrastructure.InMemoryTripRepository;
import ca.ulaval.glo4002.booking.shuttles.domain.ShuttleFactory;
import ca.ulaval.glo4002.booking.shuttles.trips.infrastructure.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import javax.ws.rs.core.Response;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

// TODO : Check how to test http errors in integration tests

public class OrderIntegrationTest {

    private OrderResource controller;
    private Festival festival;
    private OrderRepository repository;
    private PassBundleFactory passBundleFactory;

    @BeforeEach
    public void setUpController() {
        festival = new Festival();

        NumberGenerator numberGenerator = new NumberGenerator();

        EventDateFactory eventDateFactory = new EventDateFactory(festival);
        PassFactory passFactory = new PassFactory(numberGenerator, eventDateFactory);
        passBundleFactory = new PassBundleFactory(passFactory);
        ShuttleFactory shuttleFactory = new ShuttleFactory();
        OxygenFactory oxygenFactory = new OxygenFactory(festival);

        OrderFactory orderFactory = new OrderFactory(festival, numberGenerator, passBundleFactory);

        TripRepository tripRepository = new InMemoryTripRepository(shuttleFactory);
        OxygenInventoryRepository oxygenInventoryRepository = new InMemoryOxygenInventoryRepository();
        OxygenHistoryRepository oxygenHistoryRepository = new InMemoryOxygenHistoryRepository();
        repository = new InMemoryOrderRepository();

        OxygenTankProducer oxygenTankProducer = new OxygenTankProducer(oxygenInventoryRepository, oxygenHistoryRepository, oxygenFactory);

        PassBundleMapper passBundleMapper = new PassBundleMapper();
        OrderMapper orderMapper = new OrderMapper(passBundleMapper);

        TripService tripService = new TripService(festival, tripRepository, shuttleFactory);
        OxygenInventoryService oxygenInventoryService = new OxygenInventoryService(festival, oxygenFactory, oxygenTankProducer);
        OrderService orderService = new OrderService(repository, orderFactory, orderMapper, tripService, oxygenInventoryService);

        controller = new OrderResource(orderService);
    }

    @Test
    public void getByOrderNumber_shouldReturnOrder() {
        PassBundle passBundle = passBundleFactory.create(new PassBundleRequest(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        ));
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                festival.getMinimumEventDateToOrder().toLocalDateTime(),
                passBundle
        );
        repository.addOrder(order);

        Response response = controller.getByOrderNumber(order.getOrderNumber().toString());
        OrderResponse orderDto = (OrderResponse) response.getEntity();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(order.getPrice().getValue().doubleValue(), orderDto.getOrderPrice());
    }

    /*
    @Test
    public void getByOrderNumber_shouldReturnBadRequest_whenOrderNumberHasInvalidFormat() {
        String aOrderNumberWithInvalidFormat = "aOrderNumberWithInvalidFormat";

        ResponseEntity<?> response = controller.getByOrderNumber(aOrderNumberWithInvalidFormat);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getByOrderNumber_shouldReturnNotFound_whenThereIsNoOrder() {
        Number aNonExistentOrderId = new Number(1L);
        OrderNumber aNonExistentOrderNumber = new OrderNumber(aNonExistentOrderId, "VENDOR");

        ResponseEntity<?> response = controller.getByOrderNumber(aNonExistentOrderNumber.toString());
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getByOrderNumber_shouldReturnNotFound_whenOrderDoesNotExist() {
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                festival.getMinimumEventDateToOrder().toLocalDateTime(),
                mock(PassBundle.class)
        );
        repository.addOrder(order);
        Number aNonExistentOrderId = new Number(2L);
        OrderNumber aNonExistentOrderNumber = new OrderNumber(aNonExistentOrderId, "VENDOR");

        ResponseEntity<?> response = controller.getByOrderNumber(aNonExistentOrderNumber.toString());
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    */

    @Test
    public void addOrder_shouldAddOrder() {
        PassBundleRequest passBundleRequest = new PassBundleRequest(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        );
        OrderRequest orderDto = new OrderRequest(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleRequest
        );

        Response response = controller.addOrder(orderDto);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getHeaders().get(HttpHeaders.LOCATION));
    }


    /*
    @Test
    public void addOrder_shouldReturnBadRequest_whenOrderDateIsUnderBounds() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime().minusDays(1), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenOrderDateIsOverBounds() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(festival.getMaximumEventDateToOrder().toLocalDateTime().plusDays(1), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
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
    }
    */
}
