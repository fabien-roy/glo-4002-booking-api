package ca.ulaval.glo4002.booking.orders.rest.mappers;

import ca.ulaval.glo4002.booking.interfaces.rest.ErrorDto;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.OrderNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class OrderNotFoundExceptionMapper implements ExceptionMapper<OrderNotFoundException> {

    @Override
    public Response toResponse(OrderNotFoundException exception) {
        // TODO : Write correct order number in ErrorDto
        ErrorDto errorDto = new ErrorDto("ORDER_NOT_FOUND", "Order with number {orderNumber} not found");

        return Response.status(Response.Status.NOT_FOUND).entity(errorDto).build();
    }
}
