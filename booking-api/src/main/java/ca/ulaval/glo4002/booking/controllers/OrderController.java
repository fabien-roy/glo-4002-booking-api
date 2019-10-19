package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domain.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.exceptions.BookingException;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/orders")
public class OrderController {

    private OrderRepository repository;
    private OrderParser parser;

    public OrderController(OrderRepository repository, OrderParser parser) {
        this.repository = repository;
        this.parser = parser;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getByOrderNumber(@PathParam("id") String orderNumber){
        OrderWithPassesAsPassesDto orderDto;

        try {
            Order order = repository.getByOrderNumber(orderNumber);
            orderDto = parser.toDto(order);
        } catch (BookingException exception) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(orderDto);

        /*
        try {
            order = orderParser.toDto(orderService.findByOrderNumber(orderNumber));
        } catch (HumanReadableException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
        } catch (FestivalException exception) {
            return ResponseEntity.notFound().build();
        }
        */
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addOrder(OrderWithPassesAsEventDatesDto dto) {
        return ResponseEntity.badRequest().build();
    }
}
