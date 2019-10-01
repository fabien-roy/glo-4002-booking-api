package ca.ulaval.glo4002.booking.exceptions.orders;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.DtoInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderDtoInvalidException extends DtoInvalidException {

    public OrderDtoInvalidException(){ super(ExceptionConstants.Order.DTO_INVALID_MESSAGE); }
}
