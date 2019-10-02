package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderInvalidDateException extends FestivalException {

    public OrderInvalidDateException(String startDate, String endDate) {
        super(ExceptionConstants.Order.INVALID_DATE_ERROR);

        error = ExceptionConstants.Order.NOT_FOUND_ERROR;
        description = ExceptionConstants.Order.INVALID_DATE_DESCRIPTION
                .replace("{startDate}", startDate)
                .replace("{endDate}", endDate);
    }
}
