package ca.ulaval.glo4002.booking.exceptions;

public class InvalidOrderFormatException extends BookingException {

    public InvalidOrderFormatException() {
        super("INVALID_ORDER_FORMAT");

        description = "Invalid order format";
    }
}
