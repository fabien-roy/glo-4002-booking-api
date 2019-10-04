package ca.ulaval.glo4002.booking.exceptions.passengers;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PassengerNotFoundException extends FestivalException {

	public PassengerNotFoundException() {
		super(ExceptionConstants.Passenger.NOT_FOUND_ERROR);
		
		error = ExceptionConstants.Passenger.NOT_FOUND_ERROR;
		description = ExceptionConstants.Passenger.NOT_FOUND_DESCRIPTION;
		httpStatus = HttpStatus.NOT_FOUND;
	}
}
