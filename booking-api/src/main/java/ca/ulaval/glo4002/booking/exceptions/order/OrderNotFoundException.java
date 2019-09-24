package ca.ulaval.glo4002.booking.exceptions.order;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() { super(ExceptionConstants.ORDER_NOT_FOUND_MESSAGE);}
}
