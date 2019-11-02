package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.exceptions.BookingException;
import org.springframework.http.HttpStatus;

public class OrderAlreadyCreatedException extends BookingException {

    public OrderAlreadyCreatedException(String orderNumber) {
        super("ORDER_ALREADY_CREATED");

        description = "Order with number " + orderNumber + " already created";
        status = HttpStatus.BAD_REQUEST;
    }
}
