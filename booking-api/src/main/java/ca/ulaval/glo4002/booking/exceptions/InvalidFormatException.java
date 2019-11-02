package ca.ulaval.glo4002.booking.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidFormatException extends BookingException {

    public InvalidFormatException() {
        super("INVALID_FORMAT");

        description = "Invalid format";
        status = HttpStatus.BAD_REQUEST;
    }
}
