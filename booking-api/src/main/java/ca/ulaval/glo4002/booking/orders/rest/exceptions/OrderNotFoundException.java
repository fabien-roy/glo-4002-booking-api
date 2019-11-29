package ca.ulaval.glo4002.booking.orders.rest.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("ORDER_NOT_FOUND");
    }
}
