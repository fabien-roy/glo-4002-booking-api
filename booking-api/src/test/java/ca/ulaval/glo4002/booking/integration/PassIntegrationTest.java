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
import ca.ulaval.glo4002.booking.passes.domain.*;
import ca.ulaval.glo4002.booking.passes.rest.PassResponse;
import ca.ulaval.glo4002.booking.program.events.domain.EventDateFactory;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.InMemoryOxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.InMemoryOxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;
import ca.ulaval.glo4002.booking.passes.domain.PassBundleFactory;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.passes.rest.PassBundleRequest;
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
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class PassIntegrationTest {

    private OrderResource resource;
    private Festival festival;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUpResource() {
        festival = new Festival();

        NumberGenerator numberGenerator = new NumberGenerator();

        EventDateFactory eventDateFactory = new EventDateFactory(festival);
        PassFactory passFactory = new PassFactory(numberGenerator, eventDateFactory);
        PassBundleFactory passBundleFactory = new PassBundleFactory(passFactory);
        ShuttleFactory shuttleFactory = new ShuttleFactory();
        OxygenFactory oxygenFactory = new OxygenFactory(festival);
        OrderFactory orderFactory = new OrderFactory(festival, numberGenerator, passBundleFactory);

        TripRepository tripRepository = new InMemoryTripRepository(shuttleFactory);
        OxygenInventoryRepository oxygenInventoryRepository = new InMemoryOxygenInventoryRepository();
        OxygenHistoryRepository oxygenHistoryRepository = new InMemoryOxygenHistoryRepository();
        orderRepository = new InMemoryOrderRepository();

        OxygenTankProducer oxygenTankProducer = new OxygenTankProducer(oxygenInventoryRepository, oxygenHistoryRepository, oxygenFactory);

        PassBundleMapper passBundleMapper = new PassBundleMapper();
        OrderMapper orderMapper = new OrderMapper(passBundleMapper);

        TripService tripService = new TripService(festival, tripRepository, shuttleFactory);
        OxygenInventoryService oxygenInventoryService = new OxygenInventoryService(festival, oxygenFactory, oxygenTankProducer);
        OrderService orderService = new OrderService(orderRepository, orderFactory, orderMapper, tripService, oxygenInventoryService);

        resource = new OrderResource(orderService);
    }

    @Test
    public void getByOrderNumber_shouldReturnOrderWithPass_whenPassIsPackage() {
        Money passPrice = new Money(new BigDecimal(100.0));
        Pass pass = new Pass(new Number(1L), passPrice);
        PassBundle passBundle = new PassBundle(
                Collections.singletonList(pass),
                new PassCategory(PassCategories.SUPERNOVA, null),
                PassOptions.PACKAGE
        );
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                festival.getMinimumEventDateToOrder().toLocalDateTime(),
                passBundle
        );
        orderRepository.addOrder(order);

        Response response = resource.getByOrderNumber(order.getOrderNumber().toString());
        PassResponse passResponse = ((OrderResponse) response.getEntity()).getPasses().get(0);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(pass.getPassNumber().getValue(), passResponse.getPassNumber());
        assertEquals(passBundle.getCategory().toString(), passResponse.getPassCategory());
        assertEquals(passBundle.getOption().toString(), passResponse.getPassOption());
        assertNull(passResponse.getEventDate());
    }

    @Test
    public void getByOrderNumber_shouldReturnOrderWithPass_whenPassIsSinglePass() {
        Money passPrice = new Money(new BigDecimal(100.0));
        Pass pass = new Pass(new Number(1L), passPrice, EventDate.getDefaultStartEventDate());
        PassBundle passBundle = new PassBundle(
                Collections.singletonList(pass),
                new PassCategory(PassCategories.SUPERNOVA, null),
                PassOptions.SINGLE_PASS
        );
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                festival.getMinimumEventDateToOrder().toLocalDateTime(),
                passBundle
        );
        orderRepository.addOrder(order);

        Response response = resource.getByOrderNumber(order.getOrderNumber().toString());
        PassResponse passResponse = ((OrderResponse) response.getEntity()).getPasses().get(0);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(pass.getPassNumber().getValue(), passResponse.getPassNumber());
        assertEquals(passBundle.getCategory().toString(), passResponse.getPassCategory());
        assertEquals(passBundle.getOption().toString(), passResponse.getPassOption());
        assertEquals(pass.getEventDate().toString(), passResponse.getEventDate());
    }

    @Test
    public void getByOrderNumber_shouldReturnOrderWithPasses_whenPassesAreSinglePass() {
        Money passPrice = new Money(new BigDecimal(100.0));
        Pass aPass = new Pass(new Number(1L), passPrice, EventDate.getDefaultStartEventDate());
        Pass anotherPass = new Pass(
                new Number(2L),
                mock(Money.class),
               EventDate.getDefaultStartEventDate().plusDays(1)
        );
        PassBundle passBundle = new PassBundle(
                Arrays.asList(aPass, anotherPass),
                new PassCategory(PassCategories.SUPERNOVA, null),
                PassOptions.SINGLE_PASS
        );
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                festival.getMinimumEventDateToOrder().toLocalDateTime(),
                passBundle
        );
        orderRepository.addOrder(order);

        Response response = resource.getByOrderNumber(order.getOrderNumber().toString());
        PassResponse aPassResponse = ((OrderResponse) response.getEntity()).getPasses().get(0);
        PassResponse anotherPassResponse = ((OrderResponse) response.getEntity()).getPasses().get(1);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(aPass.getPassNumber().getValue(), aPassResponse.getPassNumber());
        assertEquals(passBundle.getCategory().toString(), aPassResponse.getPassCategory());
        assertEquals(passBundle.getOption().toString(), aPassResponse.getPassOption());
        assertEquals(aPass.getEventDate().toString(), aPassResponse.getEventDate());
        assertEquals(anotherPass.getPassNumber().getValue(), anotherPassResponse.getPassNumber());
        assertEquals(passBundle.getCategory().toString(), anotherPassResponse.getPassCategory());
        assertEquals(passBundle.getOption().toString(), anotherPassResponse.getPassOption());
        assertEquals(anotherPass.getEventDate().toString(), anotherPassResponse.getEventDate());
    }

    @Test
    public void addOrder_shouldAddPasses_whenPassesAreSinglePass() {
        PassBundleRequest passBundleRequest = new PassBundleRequest(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Arrays.asList(EventDate.getDefaultStartEventDate().toString(), EventDate.getDefaultStartEventDate().plusDays(1).toString())
        );
        OrderRequest orderRequest = new OrderRequest(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleRequest
        );

        Response response = resource.addOrder(orderRequest);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getHeaders().get(HttpHeaders.LOCATION));
    }

    /*
    @Test
    public void addOrder_shouldReturnBadRequest_whenPassIsPackageAndHasEventDate() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString(),
                Collections.singletonList(EventDate.getDefaultStartEventDate().toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        Response response = controller.addOrder(orderDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenPassIsSinglePackageAndHasNoEventDate() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        Response response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getEntity();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenPassCategoryIsInvalid() {
        PassBundleDto passBundleDto = new PassBundleDto(
                "anInvalidPassCategory",
                PassOptions.PACKAGE.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        Response response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getEntity();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenPassOptionIsInvalid() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                "anInvalidPassOption"
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        Response response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getEntity();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenEventDateIsInvalid() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList("anInvalidOrderDate")
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        Response response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getEntity();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenEventDateIsUnderBounds() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList(EventDate.getDefaultStartEventDate().minusDays(1).toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        Response response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getEntity();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenEventDateIsOverBounds() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList(EventDate.getDefaultEndEventDate().plusDays(1).toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        Response response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getEntity();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    */
}
