package ca.ulaval.glo4002.booking.exceptions.oxygen;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.DtoInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OxygenTankDtoInvalidException extends DtoInvalidException {

	public OxygenTankDtoInvalidException() {
		super(ExceptionConstants.Oxygen.TANK_DTO_INVALID_MESSAGE);
	}
}
