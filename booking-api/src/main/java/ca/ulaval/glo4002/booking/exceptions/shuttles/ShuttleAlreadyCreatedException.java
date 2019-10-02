package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ShuttleAlreadyCreatedException extends FestivalException {
	
	public ShuttleAlreadyCreatedException() {
		super(ExceptionConstants.Shuttle.ALREADY_CREATED_ERROR);

		error = ExceptionConstants.Shuttle.ALREADY_CREATED_ERROR;
		description = ExceptionConstants.Shuttle.ALREADY_CREATED_DESCRIPTION;
		httpStatus = HttpStatus.BAD_REQUEST;
	}

}
