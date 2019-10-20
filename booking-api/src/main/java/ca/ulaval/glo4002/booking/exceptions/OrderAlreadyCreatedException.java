package ca.ulaval.glo4002.booking.exceptions;

public class OrderAlreadyCreatedException extends BookingException {

    public OrderAlreadyCreatedException(String orderNumber) {
        super("ORDER_ALREADY_CREATED");

        description = "Order with number " + orderNumber + " already created";
    }
}
