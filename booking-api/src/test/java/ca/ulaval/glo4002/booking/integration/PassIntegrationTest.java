package ca.ulaval.glo4002.booking.integration;

import ca.ulaval.glo4002.booking.festival.Festival;
import ca.ulaval.glo4002.booking.interfaces.rest.ErrorDto;
import ca.ulaval.glo4002.booking.program.events.EventDateFactory;
import ca.ulaval.glo4002.booking.orders.*;
import ca.ulaval.glo4002.booking.program.events.EventDate;
import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.numbers.NumberGenerator;
import ca.ulaval.glo4002.booking.oxygen.*;
import ca.ulaval.glo4002.booking.oxygen.history.InMemoryOxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.InMemoryOxygenInventoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundle;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleFactory;
import ca.ulaval.glo4002.booking.profits.Money;
import ca.ulaval.glo4002.booking.passes.*;
import ca.ulaval.glo4002.booking.passes.bundles.PassBundleDto;
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

    private OrderController controller;
    private Festival festival;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUpController() {
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

        controller = new OrderController(orderService);
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

        Response response = controller.getByOrderNumber(order.getOrderNumber().toString());
        PassDto passDto = ((OrderWithPassesAsPassesDto) response.getEntity()).getPasses().get(0);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(pass.getPassNumber().getValue(), passDto.getPassNumber());
        assertEquals(passBundle.getCategory().toString(), passDto.getPassCategory());
        assertEquals(passBundle.getOption().toString(), passDto.getPassOption());
        assertNull(passDto.getEventDate());
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

        Response response = controller.getByOrderNumber(order.getOrderNumber().toString());
        PassDto passDto = ((OrderWithPassesAsPassesDto) response.getEntity()).getPasses().get(0);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(pass.getPassNumber().getValue(), passDto.getPassNumber());
        assertEquals(passBundle.getCategory().toString(), passDto.getPassCategory());
        assertEquals(passBundle.getOption().toString(), passDto.getPassOption());
        assertEquals(pass.getEventDate().toString(), passDto.getEventDate());
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

        Response response = controller.getByOrderNumber(order.getOrderNumber().toString());
        PassDto aPassDto = ((OrderWithPassesAsPassesDto) response.getEntity()).getPasses().get(0);
        PassDto anotherPassDto = ((OrderWithPassesAsPassesDto) response.getEntity()).getPasses().get(1);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(aPass.getPassNumber().getValue(), aPassDto.getPassNumber());
        assertEquals(passBundle.getCategory().toString(), aPassDto.getPassCategory());
        assertEquals(passBundle.getOption().toString(), aPassDto.getPassOption());
        assertEquals(aPass.getEventDate().toString(), aPassDto.getEventDate());
        assertEquals(anotherPass.getPassNumber().getValue(), anotherPassDto.getPassNumber());
        assertEquals(passBundle.getCategory().toString(), anotherPassDto.getPassCategory());
        assertEquals(passBundle.getOption().toString(), anotherPassDto.getPassOption());
        assertEquals(anotherPass.getEventDate().toString(), anotherPassDto.getEventDate());
    }

    @Test
    public void addOrder_shouldAddPasses_whenPassesAreSinglePass() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Arrays.asList(EventDate.getDefaultStartEventDate().toString(), EventDate.getDefaultStartEventDate().plusDays(1).toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(festival.getMinimumEventDateToOrder().toLocalDateTime(), ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        Response response = controller.addOrder(orderDto);

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
