package ca.ulaval.glo4002.booking.interfaces.rest.exceptions;

public class InvalidFormatException extends RuntimeException {

    public InvalidFormatException() {
        super("INVALID_FORMAT");
    }
}
