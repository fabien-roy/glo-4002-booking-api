package ca.ulaval.glo4002.booking.controllers;

import ca.ulaval.glo4002.booking.entities.Order;
import ca.ulaval.glo4002.booking.exceptions.order.OrderNotFoundException;
import ca.ulaval.glo4002.booking.repositories.OrderRepository;
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

    public OrderController(OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrderById(@PathParam("id") Long entityId){
        return orderRepository.findById(entityId).orElseThrow(OrderNotFoundException::new);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(Order order) {
        orderRepository.save(order);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
