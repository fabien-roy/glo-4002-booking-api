package ca.ulaval.glo4002.booking.exceptions.oxygen;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OxygenTankDtoInvalidExeption extends FestivalException {
	public OxygenTankDtoInvalidExeption() {
		super(ExceptionConstants.Oxygen.DTO_INVALID_MESSAGE);
	}
}
