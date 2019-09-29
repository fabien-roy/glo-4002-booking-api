package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.dto.OrderDto;
import ca.ulaval.glo4002.booking.domainObjects.orders.Order;
import ca.ulaval.glo4002.booking.domainObjects.passes.Pass;
import ca.ulaval.glo4002.booking.exceptions.orders.OrderNotFoundException;
import ca.ulaval.glo4002.booking.parsers.OrderParser;
import ca.ulaval.glo4002.booking.parsers.PassParser;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
import ca.ulaval.glo4002.booking.services.PassService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PassService passService;
    @Autowired
    private OrderParser orderParser;
    @Autowired
    private PassParser passParser;

    public OrderController(OrderRepository orderRepository, PassService passService, OrderParser orderParser, PassParser passParser) {
        this.orderRepository = orderRepository; // TODO : Use a service
        this.passService = passService;
        this.orderParser = orderParser;
        this.passParser = passParser;
    }

    // TODO : Test this
    // TODO : Return List<OrderDto> (with OrderParser)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);

        // TODO : Get passes (passService.findAll)
        // passService.findByAll();

        return orders;
    }

    // TODO : Test this
    // TODO : Return OrderDto (with OrderParser)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrderById(@PathParam("id") Long entityId){
        // TODO : Get pass (passService.findById)
        // passService.findById(?);
        // TODO : Make OrderService/Repository throw
        return orderRepository.findById(entityId).orElseThrow(OrderNotFoundException::new);
    }

    // TODO : Test this
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(OrderDto dto) {
        // Order order = orderParser.parseDto(dto);
        List<Pass> passes = passParser.parseDto(dto.passes);

        // TODO : Save order
        // orderService.save(order);
        passService.saveAll(passes);

        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
