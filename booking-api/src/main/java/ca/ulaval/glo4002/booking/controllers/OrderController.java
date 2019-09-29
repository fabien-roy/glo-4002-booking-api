package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.parsers.PassParser;
import ca.ulaval.glo4002.booking.services.OrderService;
import ca.ulaval.glo4002.booking.services.PassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PassServiceImpl passService;
    @Autowired
    private OrderParser orderParser;
    @Autowired
    private PassParser passParser;

    public OrderController(OrderService orderService, PassServiceImpl passService, OrderParser orderParser, PassParser passParser) {
        this.orderService = orderService;
        this.passService = passService;
        this.orderParser = orderParser;
        this.passParser = passParser;
    }

    // TODO : Test this
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDto> getOrders() {
        List<Order> orders = new ArrayList<>();
        orderService.findAll().forEach(orders::add);

        // TODO : Get passes (passService.findAll)
        // passService.findAll();

        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order -> orderDtos.add(orderParser.toDto(order)));

        return orderDtos;
    }

    // TODO : Test this
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDto getOrderById(@PathParam("id") Long entityId){
        // TODO : Get pass (passService.findById)
        // passService.findById(?);
        Order order = orderService.findById(entityId);

        return orderParser.toDto(order);
    }

    // TODO : Test this
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(OrderDto dto) {
        Order order = orderParser.parseDto(dto);
        List<Pass> passes = passParser.parseDto(dto.passes);

        orderService.save(order);
        passService.saveAll(passes);

        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
