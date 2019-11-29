package ca.ulaval.glo4002.booking.program.events.rest.mappers;

import ca.ulaval.glo4002.booking.interfaces.rest.ErrorResponse;
import ca.ulaval.glo4002.booking.program.events.rest.exceptions.InvalidEventDateException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidEventDateExceptionMapper implements ExceptionMapper<InvalidEventDateException> {

    // The following start and end event dates are default. This is as required.

    @Override
    public Response toResponse(InvalidEventDateException exception) {
        ErrorResponse errorResponse = new ErrorResponse("INVALID_EVENT_DATE", "Event date should be between July 17 2050 and July 24 2050");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
