package ca.ulaval.glo4002.booking.exceptions;

public class InvalidIdFormatException extends BookingException {

    public InvalidIdFormatException() {
        super("INVALID_ID_FORMAT");

        description = "Invalid id format";
    }
}
