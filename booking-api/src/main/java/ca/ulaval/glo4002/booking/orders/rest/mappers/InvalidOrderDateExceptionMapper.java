package ca.ulaval.glo4002.booking.orders.rest.mappers;

import ca.ulaval.glo4002.booking.interfaces.rest.ErrorResponse;
import ca.ulaval.glo4002.booking.orders.rest.exceptions.InvalidOrderDateException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidOrderDateExceptionMapper implements ExceptionMapper<InvalidOrderDateException> {

    // The following start and end order dates are default. This is as required.

    @Override
    public Response toResponse(InvalidOrderDateException exception) {
        ErrorResponse errorResponse = new ErrorResponse("INVALID_ORDER_DATE", "Order date should be between January 1 2050 and July 16 2050");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
