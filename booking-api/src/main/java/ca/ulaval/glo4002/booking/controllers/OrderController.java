package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.parsers.PassParser;
import ca.ulaval.glo4002.booking.services.OrderService;
import ca.ulaval.glo4002.booking.services.PassService;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/orders")
public class OrderController {

    private final OrderService orderService;
    private final PassService passService;
    private final OrderParser orderParser;
    private final PassParser passParser;

    public OrderController(OrderService orderService, PassService passService, OrderParser orderParser, PassParser passParser) {
        this.orderService = orderService;
        this.passService = passService;
        this.orderParser = orderParser;
        this.passParser = passParser;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getOrders() {
        List<Order> orders = new ArrayList<>();

        orderService.findAll().forEach(orders::add);

        // TODO : Get passes (passService.findAll)
        // passService.findAll();

        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order -> orderDtos.add(orderParser.toDto(order)));

        return ResponseEntity.ok().body(orderDtos);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getOrderById(@PathParam("id") Long entityId){
        // TODO : Get pass (passService.findById)
        // passService.findById(?);

        Order order;

        try {
            order = orderService.findById(entityId);
        } catch (OrderNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(orderParser.toDto(order));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addOrder(OrderDto dto) {
        Order order = orderParser.parseDto(dto);
        List<Pass> passes = passParser.parseDto(dto.passes);

        orderService.save(order);
        passService.saveAll(passes);

        return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).body(orderParser.toDto(order));
    }
}
