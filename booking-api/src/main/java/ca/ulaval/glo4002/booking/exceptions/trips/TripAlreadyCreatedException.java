package ca.ulaval.glo4002.booking.exceptions.trips;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TripAlreadyCreatedException extends FestivalException {
	
	public TripAlreadyCreatedException() {
		super(ExceptionConstants.Trip.ALREADY_CREATED_ERROR);

		error = ExceptionConstants.Trip.ALREADY_CREATED_ERROR;
		description = ExceptionConstants.Trip.ALREADY_CREATED_DESCRIPTION;
		httpStatus = HttpStatus.BAD_REQUEST;
	}

}
