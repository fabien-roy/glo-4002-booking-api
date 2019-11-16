package ca.ulaval.glo4002.booking.orders;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import ca.ulaval.glo4002.booking.exceptions.ExceptionMapper;

@Path("/orders")
public class OrderController {

	private final ExceptionMapper exceptionMapper;
	private final OrderService service;

	@Inject
	public OrderController(ExceptionMapper exceptionMapper, OrderService service) {
		this.exceptionMapper = exceptionMapper;
		this.service = service;
	}

	@GET
	@Path("/{requestedOrderNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getByOrderNumber(@PathParam("requestedOrderNumber") String requestedOrderNumber) {
		OrderWithPassesAsPassesDto orderDto;

		try {
			orderDto = service.getByOrderNumber(requestedOrderNumber);
		} catch (Exception exception) {
			return exceptionMapper.mapError(exception);
		}

		return ResponseEntity.ok().body(orderDto);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> addOrder(OrderWithPassesAsEventDatesDto requestedOrderDto) {
		String orderNumber;

		try {
			orderNumber = service.order(requestedOrderDto);
		} catch (Exception exception) {
			return exceptionMapper.mapError(exception);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, "/orders/" + orderNumber);

		return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).headers(headers).build();
	}
}
