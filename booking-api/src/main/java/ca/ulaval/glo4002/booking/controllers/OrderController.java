package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.InventoryRepositoryImpl;
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
        PassService passService = new PassServiceImpl(new PassRepositoryImpl());
        InventoryService inventoryService = new InventoryServiceImpl(new InventoryRepositoryImpl());
        OxygenTankService oxygenTankService = new OxygenTankServiceImpl(new OxygenTankRepositoryImpl(), inventoryService);

        this.orderService = new OrderServiceImpl(new OrderRepositoryImpl(), passService, oxygenTankService);
    }

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getOrderById(@PathParam("id") Long entityId){
        OrderWithPassesAsPassesDto order;

        try {
            order = orderParser.toDto(orderService.findById(entityId));
        } catch (HumanReadableException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
        } catch (FestivalException exception) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(order);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addOrder(OrderWithPassesAsEventDatesDto dto) {
        OrderWithPassesAsPassesDto order;

        try {
            order = orderParser.toDto(orderService.order(orderParser.parseDto(dto)));
        } catch (HumanReadableException exception) {
            return ResponseEntity.status(exception.getHttpStatus()).body(exception.toErrorDto());
        } catch (FestivalException exception) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).body(order);
    }
}
