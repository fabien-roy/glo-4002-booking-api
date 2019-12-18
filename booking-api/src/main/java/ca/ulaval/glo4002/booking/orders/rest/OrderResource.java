package ca.ulaval.glo4002.booking.orders.rest;

import ca.ulaval.glo4002.booking.orders.services.OrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
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
		OrderResponse orderResponse = service.getByOrderNumber(orderNumber);

		return Response.ok().entity(orderResponse).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrder(OrderRequest orderRequest) {
		String orderNumber = service.order(orderRequest);

		URI location = URI.create("/orders/" + orderNumber);

		return Response.created(location).build();
	}
}
