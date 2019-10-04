package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.HumanReadableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderInvalidFormatException extends HumanReadableException {

    public OrderInvalidFormatException(){
        super(ExceptionConstants.Order.INVALID_FORMAT_ERROR);

        error = ExceptionConstants.Order.INVALID_FORMAT_ERROR;
        description = ExceptionConstants.Order.INVALID_FORMAT_DESCRIPTION;
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
