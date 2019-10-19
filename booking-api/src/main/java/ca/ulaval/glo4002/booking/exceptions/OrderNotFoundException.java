package ca.ulaval.glo4002.booking.exceptions;

public class OrderNotFoundException extends BookingException {

    public OrderNotFoundException(String orderNumber) {
        super("ORDER_NOT_FOUND");

        description = "Order with number " + orderNumber + " already exists";
    }
}
