package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domain.Id;
import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.exceptions.BookingException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.services.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// TODO : Send correct error dtos

@Path("/orders")
public class OrderController {

    private OrderService service;
    private OrderRepository repository;
    private OrderParser parser;

    public OrderController(OrderService service, OrderRepository repository, OrderParser parser) {
        this.service = service;
        this.repository = repository;
        this.parser = parser;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getByOrderNumber(@PathParam("id") String orderNumber){
        OrderWithPassesAsPassesDto orderDto;

        try {
            Id orderId = parser.parseIdFromOrderNumber(orderNumber);
            Order order = repository.getById(orderId).get(); // TODO : Should we check?
            orderDto = parser.toDto(order);
        } catch (BookingException exception) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(orderDto);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addOrder(OrderWithPassesAsEventDatesDto requestOrderDto) {
        OrderWithPassesAsPassesDto responseOrderDto;
        Order order;

        try {
            order = parser.parseDto(requestOrderDto);
            order = service.order(order);
            responseOrderDto = parser.toDto(order);
        } catch (BookingException exception) {
            return ResponseEntity.badRequest().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/orders/" + order.getOrderNumber());

        return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).headers(headers).body(responseOrderDto);
    }
}
