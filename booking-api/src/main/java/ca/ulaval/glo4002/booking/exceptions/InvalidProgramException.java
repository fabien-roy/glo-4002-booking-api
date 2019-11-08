package ca.ulaval.glo4002.booking.exceptions;

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