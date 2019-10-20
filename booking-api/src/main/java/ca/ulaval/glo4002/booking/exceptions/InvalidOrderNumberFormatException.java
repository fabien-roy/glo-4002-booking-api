package ca.ulaval.glo4002.booking.exceptions;

public class InvalidOrderNumberFormatException extends BookingException {

    public InvalidOrderNumberFormatException() {
        super("INVALID_ORDER_NUMBER_FORMAT");

        description = "Invalid order number format";
    }
}
