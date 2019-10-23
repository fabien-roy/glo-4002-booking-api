package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.exceptions.BookingException;

public class OrderNotFoundException extends BookingException {

    public OrderNotFoundException(String orderNumber) {
        super("ORDER_NOT_FOUND");

        description = "Order with number " + orderNumber + " already exists";
    }
}
