package ca.ulaval.glo4002.booking.interfaces.rest.mappers;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import ca.ulaval.glo4002.booking.interfaces.rest.ErrorDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {

    @Override
    public Response toResponse(InvalidFormatException exception) {
        ErrorDto errorDto = new ErrorDto("INVALID_FORMAT", "Invalid format");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorDto).build();
    }
}
