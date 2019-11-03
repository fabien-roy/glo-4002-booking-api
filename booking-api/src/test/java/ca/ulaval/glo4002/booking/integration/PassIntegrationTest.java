package ca.ulaval.glo4002.booking.integration;

import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.*;
import ca.ulaval.glo4002.booking.dto.*;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.exceptions.InvalidEventDateException;
import ca.ulaval.glo4002.booking.factories.OrderFactory;
import ca.ulaval.glo4002.booking.factories.PassFactory;
import ca.ulaval.glo4002.booking.factories.PassBundleFactory;
import ca.ulaval.glo4002.booking.mappers.OrderMapper;
import ca.ulaval.glo4002.booking.mappers.PassBundleMapper;
import ca.ulaval.glo4002.booking.repositories.InMemoryOrderRepository;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUpController() {
        orderRepository = new InMemoryOrderRepository();

        NumberGenerator numberGenerator = new NumberGenerator();

        PassFactory passFactory = new PassFactory(numberGenerator);
        PassBundleFactory passBundleFactory = new PassBundleFactory(passFactory);
        OrderFactory orderFactory = new OrderFactory(numberGenerator, passBundleFactory);

        PassBundleMapper passBundleMapper = new PassBundleMapper();
        OrderMapper orderMapper = new OrderMapper(passBundleMapper);

        OrderService orderService = new OrderService(orderRepository, orderFactory, orderMapper);

        controller = new OrderController(orderService);
    }

    @Test
    public void getByOrderNumber_shouldReturnOrderWithPass_whenPassIsPackage() {
        Pass pass = new Pass(new Number(1L), mock(Money.class));
        PassBundle passBundle = new PassBundle(
                Collections.singletonList(pass),
                new PassCategory(PassCategories.SUPERNOVA.toString(), null),
                new PassOption(PassOptions.PACKAGE.toString())
        );
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                OrderFactory.START_DATE_TIME,
                passBundle
        );
        orderRepository.addOrder(order);

        ResponseEntity<?> response = controller.getByOrderNumber(order.getOrderNumber().toString());
        PassDto passDto = ((OrderWithPassesAsPassesDto) response.getBody()).getPasses().get(0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pass.getPassNumber().getValue(), passDto.getPassNumber());
        assertEquals(passBundle.getCategory().getName(), passDto.getPassCategory());
        assertEquals(passBundle.getOption().getName(), passDto.getPassOption());
        assertNull(passDto.getEventDate());
    }

    @Test
    public void getByOrderNumber_shouldReturnOrderWithPass_whenPassIsSinglePass() {
        Pass pass = new Pass(new Number(1L), new EventDate(EventDate.START_DATE), mock(Money.class));
        PassBundle passBundle = new PassBundle(
                Collections.singletonList(pass),
                new PassCategory(PassCategories.SUPERNOVA.toString(), null),
                new PassOption(PassOptions.SINGLE_PASS.toString())
        );
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                OrderFactory.START_DATE_TIME,
                passBundle
        );
        orderRepository.addOrder(order);

        ResponseEntity<?> response = controller.getByOrderNumber(order.getOrderNumber().toString());
        PassDto passDto = ((OrderWithPassesAsPassesDto) response.getBody()).getPasses().get(0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pass.getPassNumber().getValue(), passDto.getPassNumber());
        assertEquals(passBundle.getCategory().getName(), passDto.getPassCategory());
        assertEquals(passBundle.getOption().getName(), passDto.getPassOption());
        assertEquals(pass.getEventDate().toString(), passDto.getEventDate());
    }

    @Test
    public void getByOrderNumber_shouldReturnOrderWithPasses_whenPassesAreSinglePass() {
        Pass aPass = new Pass(
                new Number(1L),
                new EventDate(EventDate.START_DATE),
                mock(Money.class)
        );
        Pass anotherPass = new Pass(
                new Number(2L),
                new EventDate(EventDate.START_DATE.plusDays(1)),
                mock(Money.class)
        );
        PassBundle passBundle = new PassBundle(
                Arrays.asList(aPass, anotherPass),
                new PassCategory(PassCategories.SUPERNOVA.toString(), null),
                new PassOption(PassOptions.SINGLE_PASS.toString())
        );
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                OrderFactory.START_DATE_TIME,
                passBundle
        );
        orderRepository.addOrder(order);

        ResponseEntity<?> response = controller.getByOrderNumber(order.getOrderNumber().toString());
        PassDto aPassDto = ((OrderWithPassesAsPassesDto) response.getBody()).getPasses().get(0);
        PassDto anotherPassDto = ((OrderWithPassesAsPassesDto) response.getBody()).getPasses().get(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(aPass.getPassNumber().getValue(), aPassDto.getPassNumber());
        assertEquals(passBundle.getCategory().getName(), aPassDto.getPassCategory());
        assertEquals(passBundle.getOption().getName(), aPassDto.getPassOption());
        assertEquals(aPass.getEventDate().toString(), aPassDto.getEventDate());
        assertEquals(anotherPass.getPassNumber().getValue(), anotherPassDto.getPassNumber());
        assertEquals(passBundle.getCategory().getName(), anotherPassDto.getPassCategory());
        assertEquals(passBundle.getOption().getName(), anotherPassDto.getPassOption());
        assertEquals(anotherPass.getEventDate().toString(), anotherPassDto.getEventDate());
    }

    @Test
    public void addOrder_shouldAddPasses_whenPassesAreSinglePass() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString())
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
    public void addOrder_shouldReturnBadRequest_whenPassIsPackageAndHasEventDate() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString(),
                Collections.singletonList(EventDate.START_DATE.toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenPassIsSinglePackageAndHasNoEventDate() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenPassCategoryIsInvalid() {
        PassBundleDto passBundleDto = new PassBundleDto(
                "anInvalidPassCategory",
                PassOptions.PACKAGE.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenPassOptionIsInvalid() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                "anInvalidPassOption"
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenEventDateIsInvalid() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList("anInvalidOrderDate")
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenEventDateIsUnderBounds() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList(EventDate.START_DATE.minusDays(1).toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidEventDateException.MESSAGE, errorDto.getError());
        assertEquals(InvalidEventDateException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenEventDateIsOverBounds() {
        PassBundleDto passBundleDto = new PassBundleDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList(EventDate.END_DATE.plusDays(1).toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passBundleDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidEventDateException.MESSAGE, errorDto.getError());
        assertEquals(InvalidEventDateException.DESCRIPTION, errorDto.getDescription());
    }
}
