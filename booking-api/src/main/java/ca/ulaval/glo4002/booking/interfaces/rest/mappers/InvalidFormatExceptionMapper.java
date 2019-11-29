package ca.ulaval.glo4002.booking.interfaces.rest.mappers;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.interfaces.rest.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {

    @Override
    public Response toResponse(InvalidFormatException exception) {
        ErrorResponse errorResponse = new ErrorResponse("INVALID_FORMAT", "Invalid format");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
