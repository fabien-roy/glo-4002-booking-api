package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ShuttleFullException extends FestivalException {
	
	public ShuttleFullException() {
		super(ExceptionConstants.Shuttle.FULL_ERROR);

		error = ExceptionConstants.Shuttle.FULL_ERROR;
		description = ExceptionConstants.Shuttle.FULL_DESCRIPTION;
		httpStatus = HttpStatus.BAD_REQUEST;
	}

}
