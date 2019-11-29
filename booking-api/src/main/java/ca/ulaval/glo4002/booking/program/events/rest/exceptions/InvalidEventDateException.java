package ca.ulaval.glo4002.booking.program.events.rest.exceptions;

public class InvalidEventDateException extends RuntimeException {

    public InvalidEventDateException() {
        super("INVALID_EVENT_DATE");
    }
}
