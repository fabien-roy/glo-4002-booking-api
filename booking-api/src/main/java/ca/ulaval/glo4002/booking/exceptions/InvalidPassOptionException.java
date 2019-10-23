package ca.ulaval.glo4002.booking.exceptions;

public class InvalidPassOptionException extends BookingException {

    public InvalidPassOptionException() {
        super("INVALID_PASS_OPTION");

        description = "Invalid pass option";
    }
}

