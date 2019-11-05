package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.orders.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.orders.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;
import ca.ulaval.glo4002.booking.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    private OrderController controller;
    private OrderService service;

    @BeforeEach
    void setUpController() {
        service = mock(OrderService.class);

        controller = new OrderController(service);
    }

    @Test
    void getByOrderNumber_shouldReturnBadRequest_whenBadRequest() {
        String aOrderNumber = "aOrderNumber";
        when(service.getByOrderNumber(aOrderNumber)).thenThrow(new InvalidFormatException());

        ResponseEntity<?> response = controller.getByOrderNumber(aOrderNumber);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getByOrderNumber_shouldReturnNotFound_whenOrderIsNotFound() {
        String aOrderNumber = "aOrderNumber";
        when(service.getByOrderNumber(aOrderNumber)).thenThrow(new OrderNotFoundException(aOrderNumber));

        ResponseEntity<?> response = controller.getByOrderNumber(aOrderNumber);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getByOrderNumber_shouldReturnOk() {
        String aOrderNumber = "VENDOR-123";
        when(service.getByOrderNumber(aOrderNumber)).thenReturn(mock(OrderWithPassesAsPassesDto.class));

        ResponseEntity<?> response = controller.getByOrderNumber(aOrderNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void addOrder_shouldReturnBadRequest_whenBadRequest() {
        OrderWithPassesAsEventDatesDto aOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
        when(service.order(aOrderDto)).thenThrow(new InvalidFormatException());

        ResponseEntity<?> response = controller.addOrder(aOrderDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void addOrder_shouldReturnCreated() {
        OrderWithPassesAsEventDatesDto aOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
        when(service.order(aOrderDto)).thenReturn("aOrderNumber");

        ResponseEntity<?> response = controller.addOrder(aOrderDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addOrder_shouldReturnLocationHeaders() {
        OrderWithPassesAsEventDatesDto aOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
        String expectedOrderNumber = "expectedOrderNumber";
        when(service.order(aOrderDto)).thenReturn(expectedOrderNumber);

        ResponseEntity<?> response = controller.addOrder(aOrderDto);

        assertNotNull(response.getHeaders());
        assertEquals(1, response.getHeaders().get(HttpHeaders.LOCATION).size());
        assertEquals("/orders/" + expectedOrderNumber, response.getHeaders().get(HttpHeaders.LOCATION).get(0));
    }
}