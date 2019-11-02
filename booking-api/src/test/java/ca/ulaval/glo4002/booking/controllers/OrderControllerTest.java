package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.exceptions.OrderAlreadyCreatedException;
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

    private OrderController subject;
    private OrderService service;

    @BeforeEach
    void setUpSubject() {
        service = mock(OrderService.class);

        subject = new OrderController(service);
    }

    // TODO : ACP : Controllers must throw correct http status and error dto
    @Test
    void getByOrderNumber_shouldReturnNotFound_whenOrderIsNotFound() {
        String aOrderNumber = "aOrderNumber";
        when(service.getByOrderNumber(any())).thenThrow(new OrderNotFoundException(aOrderNumber));

        ResponseEntity<?> response = subject.getByOrderNumber(aOrderNumber);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getByOrderNumber_shouldReturnOk() {
        String aOrderNumber = "VENDOR-123";
        when(service.getByOrderNumber(any())).thenReturn(mock(OrderWithPassesAsPassesDto.class));

        ResponseEntity<?> response = subject.getByOrderNumber(aOrderNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // TODO : ACP : Controllers must throw correct http status and error dto
    @Test
    void addOrder_shouldReturnBadRequest_whenBadRequest() {
        OrderWithPassesAsEventDatesDto aOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
        when(service.order(any())).thenThrow(new OrderAlreadyCreatedException("aOrderNumber"));

        ResponseEntity<?> response = subject.addOrder(aOrderDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void addOrder_shouldReturnCreated() {
        OrderWithPassesAsEventDatesDto aOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
        OrderWithPassesAsPassesDto expectedOrderDto = mock(OrderWithPassesAsPassesDto.class);
        when(service.order(any())).thenReturn(expectedOrderDto);

        ResponseEntity<?> response = subject.addOrder(aOrderDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addOrder_shouldReturnLocationHeaders() {
        OrderWithPassesAsEventDatesDto aOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
        OrderWithPassesAsPassesDto expectedOrderDto = mock(OrderWithPassesAsPassesDto.class);
        String expectedOrderNumber = "expectedOrderNumber";
        when(expectedOrderDto.getOrderNumber()).thenReturn(expectedOrderNumber);
        when(service.order(any())).thenReturn(expectedOrderDto);

        ResponseEntity<?> response = subject.addOrder(aOrderDto);

        assertNotNull(response.getHeaders());
        assertEquals(1, response.getHeaders().get(HttpHeaders.LOCATION).size());
        assertEquals("/orders/" + expectedOrderNumber, response.getHeaders().get(HttpHeaders.LOCATION).get(0));
    }
}