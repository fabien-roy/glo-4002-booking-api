package ca.ulaval.glo4002.booking.orders.rest.exceptions;

public class OrderNotFoundException extends RuntimeException {

    private final String orderNumber;

    public OrderNotFoundException(String orderNumber) {
        super("ORDER_NOT_FOUND");

        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
