package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderInvalidDateException extends ControllerException {

    // TODO : ACP : Check date format
    public OrderInvalidDateException() {
        super(ExceptionConstants.Order.INVALID_DATE_ERROR);

        error = ExceptionConstants.Order.NOT_FOUND_ERROR;
        description = ExceptionConstants.Order.INVALID_DATE_DESCRIPTION
                .replace("{startDate}", DateConstants.ORDER_START_DATE_TIME.format(DateConstants.MESSAGE_DATE_TIME_FORMATTER))
                .replace("{endDate}", DateConstants.ORDER_END_DATE_TIME.format(DateConstants.MESSAGE_DATE_TIME_FORMATTER));
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
