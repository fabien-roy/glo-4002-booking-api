package ca.ulaval.glo4002.booking.program.rest.exceptions;

public class InvalidProgramException extends RuntimeException {

    public InvalidProgramException() {
        super("INVALID_PROGRAM");
    }
}
