package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.exceptions.ControllerException;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepositoryImpl;
import ca.ulaval.glo4002.booking.repositories.OxygenTankRepositoryImpl;
import ca.ulaval.glo4002.booking.repositories.PassRepositoryImpl;
import ca.ulaval.glo4002.booking.services.*;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrderController {

    private OrderService orderService;
    private final OrderParser orderParser = new OrderParser();

    public OrderController() {
        // TODO : ACP : Inject this
        PassService passService = new PassServiceImpl(new PassRepositoryImpl());
        OxygenTankService oxygenTankService = new OxygenTankServiceImpl(new OxygenTankRepositoryImpl());
        this.orderService = new OrderServiceImpl(new OrderRepositoryImpl(), passService, oxygenTankService);
    }

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getOrderById(@PathParam("id") Long entityId){
        Order order;

        try {
            // TODO : ACP : Is this correct for passes?
            order = orderService.findById(entityId);
        } catch (ControllerException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
        } catch (FestivalException exception) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(orderParser.toDto(order));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addOrder(OrderWithPassesAsEventDatesDto dto) {
        Order order;

        try {
            order = orderService.order(orderParser.parseDto(dto));
        } catch (ControllerException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
        } catch (FestivalException exception) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).body(orderParser.toDto(order));
    }
}
