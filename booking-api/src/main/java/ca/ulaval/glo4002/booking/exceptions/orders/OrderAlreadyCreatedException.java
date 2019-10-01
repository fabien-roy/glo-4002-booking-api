package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.AlreadyCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderAlreadyCreatedException extends AlreadyCreatedException {

    public OrderAlreadyCreatedException() {
        super(ExceptionConstants.Order.ALREADY_CREATED_MESSAGE);
    }
}
