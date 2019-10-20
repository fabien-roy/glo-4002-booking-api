package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.domain.OrderDate;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.exceptions.BookingException;
import ca.ulaval.glo4002.booking.exceptions.OrderAlreadyCreatedException;
import ca.ulaval.glo4002.booking.exceptions.OrderNotFoundException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Any;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    OrderController subject;
    OrderService service;
    OrderParser parser;
    OrderRepository repository;

    @BeforeEach
    void setUpSubject() {
        service = mock(OrderService.class);
        parser = mock(OrderParser.class);
        repository = mock(OrderRepository.class);

        subject = new OrderController(service, repository, parser);
    }

    // TODO : ACP : Controllers must throw correct http status and error dto
    @Test
    void getByOrderNumber_shouldReturnNotFound_whenOrderIsNotFound() {
        String aOrderNumber = "aOrderNumber";
        when(repository.getById(any())).thenThrow(new OrderNotFoundException(aOrderNumber));

        ResponseEntity<?> response = subject.getByOrderNumber(aOrderNumber);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getByOrderNumber_shouldReturnOk() {
        String aOrderNumber = "VENDOR-123";
        when(repository.getById(any())).thenReturn(Optional.of(mock(Order.class)));

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
        Order aOrder = mock(Order.class);
        when(aOrder.getOrderNumber()).thenReturn("TEAM-123");
        when(service.order(any())).thenReturn(aOrder);

        ResponseEntity<?> response = subject.addOrder(aOrderDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void addOrder_shouldReturnLocationHeaders() {
        OrderWithPassesAsEventDatesDto aOrderDto = mock(OrderWithPassesAsEventDatesDto.class);
        Order aOrder = mock(Order.class);
        String aOrderNumber = "TEAM-123";
        when(aOrder.getOrderNumber()).thenReturn(aOrderNumber);
        when(service.order(any())).thenReturn(aOrder);

        ResponseEntity<?> response = subject.addOrder(aOrderDto);

        assertNotNull(response.getHeaders());
        assertEquals(1, response.getHeaders().get(HttpHeaders.LOCATION).size());
        assertEquals("/orders/" + aOrderNumber, response.getHeaders().get(HttpHeaders.LOCATION).get(0));
    }
}