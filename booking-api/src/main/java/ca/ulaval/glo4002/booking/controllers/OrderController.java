package ca.ulaval.glo4002.booking.controllers;

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

import ca.ulaval.glo4002.booking.dto.orders.OrderWithPassesAsEventDatesDto;
import ca.ulaval.glo4002.booking.dto.orders.OrderWithPassesAsPassesDto;
import ca.ulaval.glo4002.booking.exceptions.BookingException;
import ca.ulaval.glo4002.booking.services.OrderService;

// TODO : Use ExceptionMapper for ErrorDto and HttpStatus

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
	public ResponseEntity<?> getByOrderNumber(@PathParam("requestedOrderNumber") String requestedOrderNumber) {
		OrderWithPassesAsPassesDto orderDto;

		try {
			orderDto = service.getByOrderNumber(requestedOrderNumber);
		} catch (BookingException exception) {
			return ResponseEntity.status(exception.getStatus()).body(exception.toErrorDto());
		} catch (Exception exception) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok().body(orderDto);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> addOrder(OrderWithPassesAsEventDatesDto requestedOrderDto) {
		String orderNumber;

		try {
			orderNumber = service.order(requestedOrderDto);
		} catch (BookingException exception) {
			return ResponseEntity.status(exception.getStatus()).body(exception.toErrorDto());
		} catch (Exception exception) {
			return ResponseEntity.badRequest().build();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, "/orders/" + orderNumber);

		return ResponseEntity.status(Response.Status.CREATED.getStatusCode()).headers(headers).build();
	}
}
