package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.exceptions.BookingException;
import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends BookingException {

    public OrderNotFoundException(String orderNumber) {
        super("ORDER_NOT_FOUND");

        description = "Order with number " + orderNumber + " already exists";
        status = HttpStatus.NOT_FOUND;
    }
}
