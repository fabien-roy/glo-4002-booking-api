package ca.ulaval.glo4002.booking.orders.rest.mappers;

import ca.ulaval.glo4002.booking.interfaces.rest.ErrorResponse;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.OrderNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class OrderNotFoundExceptionMapper implements ExceptionMapper<OrderNotFoundException> {

    @Override
    public Response toResponse(OrderNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse("ORDER_NOT_FOUND", "Order with number {orderNumber} not found".replace("{orderNumber}", exception.getOrderNumber()));

        return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
    }
}
