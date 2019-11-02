package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.exceptions.BookingException;
import ca.ulaval.glo4002.booking.services.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// TODO : Use ExceptionMapper for ErrorDto and HttpStatus

@Path("/orders")
public class OrderController {

    private final OrderService service;

    @Inject
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GET
    @Path("/{requestedOrderNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getByOrderNumber(@PathParam("requestedOrderNumber") String requestedOrderNumber){
        OrderWithPassesAsPassesDto orderDto;

        try {
            orderDto = service.getByOrderNumber(requestedOrderNumber);
        } catch (BookingException exception) {
            return ResponseEntity.status(exception.getStatus()).body(exception.toErrorDto());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(orderDto);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addOrder(OrderWithPassesAsEventDatesDto requestOrderDto) {
        OrderWithPassesAsPassesDto responseOrderDto;

        try {
            responseOrderDto = service.order(requestOrderDto);
        } catch (BookingException exception) {
            return ResponseEntity.status(exception.getStatus()).body(exception.toErrorDto());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/orders/" + responseOrderDto.getOrderNumber());

        return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).headers(headers).body(responseOrderDto);
    }
}
