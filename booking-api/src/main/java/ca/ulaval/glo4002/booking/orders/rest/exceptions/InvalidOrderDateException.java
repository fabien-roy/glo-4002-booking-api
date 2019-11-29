package ca.ulaval.glo4002.booking.orders.rest.exceptions;

public class InvalidOrderDateException extends RuntimeException {

    public InvalidOrderDateException() {
        super("INVALID_ORDER_DATE");
    }
}
