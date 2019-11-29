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
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

	private final OrderService service;

	@Inject
	public OrderResource(OrderService service) {
		this.service = service;
	}

	@GET
	@Path("/{orderNumber}")
	public Response getByOrderNumber(@PathParam("orderNumber") String orderNumber) {
		OrderResponse orderDto = service.getByOrderNumber(orderNumber);

		return Response.ok().entity(orderDto).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrder(OrderRequest requestedOrderDto) {
		String orderNumber = service.order(requestedOrderDto);

		URI location = URI.create("/orders/" + orderNumber);

		return Response.created(location).build();
	}
}
