package ca.ulaval.glo4002.booking.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidFormatException extends BookingException {

    public static final String MESSAGE = "INVALID_FORMAT";
    public static final String DESCRIPTION = "Invalid format";

    public InvalidFormatException() {
        super(MESSAGE);

        description = DESCRIPTION;
        status = HttpStatus.BAD_REQUEST;
    }
}
