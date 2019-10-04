package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends HumanReadableException {

    public OrderNotFoundException(String orderNumber) {
        super(ExceptionConstants.Order.NOT_FOUND_ERROR);

        error = ExceptionConstants.Order.NOT_FOUND_ERROR;
        description = ExceptionConstants.Order.NOT_FOUND_DESCRIPTION.replace("{orderNumber}", orderNumber);
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
