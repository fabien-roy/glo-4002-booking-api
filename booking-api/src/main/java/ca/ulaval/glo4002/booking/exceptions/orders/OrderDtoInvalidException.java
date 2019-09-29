package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderDtoInvalidException extends FestivalException {

    public OrderDtoInvalidException(){ super(ExceptionConstants.ORDER_DTO_INVALID_MESSAGE); }
}
