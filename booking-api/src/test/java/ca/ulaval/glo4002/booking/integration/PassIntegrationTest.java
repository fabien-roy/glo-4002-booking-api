package ca.ulaval.glo4002.booking.integration;

import ca.ulaval.glo4002.booking.controllers.OrderController;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.orders.Order;
import ca.ulaval.glo4002.booking.domain.orders.OrderNumber;
import ca.ulaval.glo4002.booking.domain.passes.*;
import ca.ulaval.glo4002.booking.domain.passes.pricecalculationstrategy.NoDiscountPriceCalculationStrategy;
import ca.ulaval.glo4002.booking.dto.*;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.exceptions.passes.OutOfBoundsEventDateException;
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

        PassFactory passFactory = new PassFactory();
        PassListFactory passListFactory = new PassListFactory(numberGenerator, passFactory);
        OrderFactory orderFactory = new OrderFactory(numberGenerator, passListFactory);

        PassListMapper passListMapper = new PassListMapper();
        OrderMapper orderMapper = new OrderMapper(passListMapper);

        OrderService orderService = new OrderService(orderRepository, orderFactory, orderMapper);

        controller = new OrderController(orderService);
    }

    @Test
    public void getByOrderNumber_shouldReturnOrderWithPass_whenPassIsPackage() {
        PassList passList = new PassList(
                new PassCategory(PassCategories.SUPERNOVA.toString()),
                new PassOption(PassOptions.PACKAGE.toString()),
                new NoDiscountPriceCalculationStrategy()
        );
        Pass pass = new Pass(new Number(1L));
        passList.setPasses(Collections.singletonList(pass));
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                OrderFactory.START_DATE_TIME,
                passList
        );
        orderRepository.addOrder(order);

        ResponseEntity<?> response = controller.getByOrderNumber(order.getOrderNumber().toString());
        PassDto passDto = ((OrderWithPassesAsPassesDto) response.getBody()).getPasses().get(0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pass.getPassNumber().getValue(), passDto.getPassNumber());
        assertEquals(passList.getCategory().getName(), passDto.getPassCategory());
        assertEquals(passList.getOption().getName(), passDto.getPassOption());
        assertNull(passDto.getEventDate());
    }

    @Test
    public void getByOrderNumber_shouldReturnOrderWithPass_whenPassIsSinglePass() {
        PassList passList = new PassList(
                new PassCategory(PassCategories.SUPERNOVA.toString()),
                new PassOption(PassOptions.SINGLE_PASS.toString()),
                new NoDiscountPriceCalculationStrategy()
        );
        Pass pass = new Pass(
                new Number(1L),
                new EventDate(EventDate.START_DATE)
        );
        passList.setPasses(Collections.singletonList(pass));
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                OrderFactory.START_DATE_TIME,
                passList
        );
        orderRepository.addOrder(order);

        ResponseEntity<?> response = controller.getByOrderNumber(order.getOrderNumber().toString());
        PassDto passDto = ((OrderWithPassesAsPassesDto) response.getBody()).getPasses().get(0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pass.getPassNumber().getValue(), passDto.getPassNumber());
        assertEquals(passList.getCategory().getName(), passDto.getPassCategory());
        assertEquals(passList.getOption().getName(), passDto.getPassOption());
        assertEquals(pass.getEventDate().toString(), passDto.getEventDate());
    }

    @Test
    public void getByOrderNumber_shouldReturnOrderWithPasses_whenPassesAreSinglePass() {
        PassList passList = new PassList(
                new PassCategory(PassCategories.SUPERNOVA.toString()),
                new PassOption(PassOptions.SINGLE_PASS.toString()),
                new NoDiscountPriceCalculationStrategy()
        );
        Pass aPass = new Pass(
                new Number(1L),
                new EventDate(EventDate.START_DATE)
        );
        Pass anotherPass = new Pass(
                new Number(2L),
                new EventDate(EventDate.START_DATE.plusDays(1))
        );
        passList.setPasses(Arrays.asList(aPass, anotherPass));
        Order order = new Order(
                new OrderNumber(new Number(1L), "VENDOR"),
                OrderFactory.START_DATE_TIME,
                passList
        );
        orderRepository.addOrder(order);

        ResponseEntity<?> response = controller.getByOrderNumber(order.getOrderNumber().toString());
        PassDto aPassDto = ((OrderWithPassesAsPassesDto) response.getBody()).getPasses().get(0);
        PassDto anotherPassDto = ((OrderWithPassesAsPassesDto) response.getBody()).getPasses().get(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(aPass.getPassNumber().getValue(), aPassDto.getPassNumber());
        assertEquals(passList.getCategory().getName(), aPassDto.getPassCategory());
        assertEquals(passList.getOption().getName(), aPassDto.getPassOption());
        assertEquals(aPass.getEventDate().toString(), aPassDto.getEventDate());
        assertEquals(anotherPass.getPassNumber().getValue(), anotherPassDto.getPassNumber());
        assertEquals(passList.getCategory().getName(), anotherPassDto.getPassCategory());
        assertEquals(passList.getOption().getName(), anotherPassDto.getPassOption());
        assertEquals(anotherPass.getEventDate().toString(), anotherPassDto.getEventDate());
    }

    @Test
    public void addOrder_shouldAddPasses_whenPassesAreSinglePass() {
        PassListDto passListDto = new PassListDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Arrays.asList(EventDate.START_DATE.toString(), EventDate.START_DATE.plusDays(1).toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passListDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get(HttpHeaders.LOCATION));
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenPassIsPackageAndHasEventDate() {
        PassListDto passListDto = new PassListDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.PACKAGE.toString(),
                Collections.singletonList(EventDate.START_DATE.toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passListDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenPassIsSinglePackageAndHasNoEventDate() {
        PassListDto passListDto = new PassListDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passListDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenPassCategoryIsInvalid() {
        PassListDto passListDto = new PassListDto(
                "anInvalidPassCategory",
                PassOptions.PACKAGE.toString()
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passListDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenPassOptionIsInvalid() {
        PassListDto passListDto = new PassListDto(
                PassCategories.SUPERNOVA.toString(),
                "anInvalidPassOption"
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passListDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenEventDateIsInvalid() {
        PassListDto passListDto = new PassListDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList("anInvalidOrderDate")
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passListDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(InvalidFormatException.MESSAGE, errorDto.getError());
        assertEquals(InvalidFormatException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenEventDateIsUnderBounds() {
        PassListDto passListDto = new PassListDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList(EventDate.START_DATE.minusDays(1).toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passListDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(OutOfBoundsEventDateException.MESSAGE, errorDto.getError());
        assertEquals(OutOfBoundsEventDateException.DESCRIPTION, errorDto.getDescription());
    }

    @Test
    public void addOrder_shouldReturnBadRequest_whenEventDateIsOverBounds() {
        PassListDto passListDto = new PassListDto(
                PassCategories.SUPERNOVA.toString(),
                PassOptions.SINGLE_PASS.toString(),
                Collections.singletonList(EventDate.END_DATE.plusDays(1).toString())
        );
        OrderWithPassesAsEventDatesDto orderDto = new OrderWithPassesAsEventDatesDto(
                ZonedDateTime.of(OrderFactory.START_DATE_TIME, ZoneId.systemDefault()).toString(),
                "VENDOR",
                passListDto
        );

        ResponseEntity<?> response = controller.addOrder(orderDto);
        ErrorDto errorDto = (ErrorDto) response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(OutOfBoundsEventDateException.MESSAGE, errorDto.getError());
        assertEquals(OutOfBoundsEventDateException.DESCRIPTION, errorDto.getDescription());
    }
}
