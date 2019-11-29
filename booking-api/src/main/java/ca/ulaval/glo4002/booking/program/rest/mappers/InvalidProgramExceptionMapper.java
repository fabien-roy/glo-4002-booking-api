package ca.ulaval.glo4002.booking.program.rest.mappers;

import ca.ulaval.glo4002.booking.interfaces.rest.ErrorResponse;
import ca.ulaval.glo4002.booking.program.rest.exceptions.InvalidProgramException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidProgramExceptionMapper implements ExceptionMapper<InvalidProgramException> {

    @Override
    public Response toResponse(InvalidProgramException exception) {
        ErrorResponse errorResponse = new ErrorResponse("INVALID_PROGRAM", "Invalid program");

        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
