package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.constants.ControllerConstants;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.repositories.*;
import ca.ulaval.glo4002.booking.services.*;
import org.springframework.http.HttpHeaders;
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
        OxygenTankService oxygenTankService = new OxygenTankServiceImpl(new OxygenTankRepositoryImpl());
        OxygenTankInventoryService oxygenTankInventoryService = new OxygenTankInventoryServiceImpl(new OxygenTankInventoryRepositoryImpl(), oxygenTankService);
        PassengerService passengerService = new PassengerServiceImpl(new PassengerRepositoryImpl());
        ShuttleService shuttleService = new ShuttleServiceImpl(new ShuttleRepositoryImpl(), passengerService);
        ShuttleInventoryService shuttleInventoryService = new ShuttleInventoryServiceImpl(new ShuttleInventoryRepositoryImpl(), shuttleService);

        this.orderService = new OrderServiceImpl(new OrderRepositoryImpl(), passService, oxygenTankInventoryService, shuttleInventoryService);
    }

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getByOrderNumber(@PathParam("id") String orderNumber){
        OrderWithPassesAsPassesDto order;

        try {
            order = orderParser.toDto(orderService.findByOrderNumber(orderNumber));
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

        HttpHeaders headers = new HttpHeaders();
        headers.add(ControllerConstants.LOCATION_HEADER_NAME, "/orders/" + order.orderNumber);

        return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).headers(headers).body(order);
    }
}
