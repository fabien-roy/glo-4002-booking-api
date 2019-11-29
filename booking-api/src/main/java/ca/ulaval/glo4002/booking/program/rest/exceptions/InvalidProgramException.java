package ca.ulaval.glo4002.booking.program.rest.exceptions;

import ca.ulaval.glo4002.booking.errors.BookingException;
import org.springframework.http.HttpStatus;

public class InvalidProgramException extends BookingException {

    public static final String MESSAGE = "INVALID_PROGRAM";
    public static final String DESCRIPTION = "Invalid program";

    public InvalidProgramException() {
        super(MESSAGE);

        description = DESCRIPTION;
        status = HttpStatus.BAD_REQUEST;
    }
}
