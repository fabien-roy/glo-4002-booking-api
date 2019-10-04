package ca.ulaval.glo4002.booking.exceptions.trips;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TripNotFoundException extends FestivalException {
	
	public TripNotFoundException() {
		super(ExceptionConstants.Trip.NOT_FOUND_DESCRIPTION);

		error = ExceptionConstants.Trip.NOT_FOUND_ERROR;
		description = ExceptionConstants.Trip.NOT_FOUND_DESCRIPTION;
		httpStatus = HttpStatus.NOT_FOUND;
	}
}
