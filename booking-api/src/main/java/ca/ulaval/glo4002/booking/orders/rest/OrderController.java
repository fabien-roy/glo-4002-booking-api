package ca.ulaval.glo4002.booking.orders.rest;

import ca.ulaval.glo4002.booking.orders.services.OrderService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;

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
	public Response getByOrderNumber(@PathParam("requestedOrderNumber") String requestedOrderNumber) {
		OrderWithPassesAsPassesDto orderDto = service.getByOrderNumber(requestedOrderNumber);

		return Response.ok().entity(orderDto).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrder(OrderWithPassesAsEventDatesDto requestedOrderDto) {
		String orderNumber = service.order(requestedOrderDto);

		URI location = URI.create("/orders/" + orderNumber);

		return Response.created(location).build();
	}
}
