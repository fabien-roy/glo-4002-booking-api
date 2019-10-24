package ca.ulaval.glo4002.booking.exceptions;

public class InvalidNumberFormatException extends BookingException {

    public InvalidNumberFormatException() {
        super("INVALID_ID_FORMAT");

        description = "Invalid id format";
    }
}
