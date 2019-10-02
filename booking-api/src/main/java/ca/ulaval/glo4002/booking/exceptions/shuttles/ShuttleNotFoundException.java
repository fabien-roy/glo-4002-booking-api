package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShuttleNotFoundException extends FestivalException {
	
	public ShuttleNotFoundException() {
		super(ExceptionConstants.Shuttle.NOT_FOUND_DESCRIPTION);

		error = ExceptionConstants.Shuttle.NOT_FOUND_ERROR;
		description = ExceptionConstants.Shuttle.NOT_FOUND_DESCRIPTION;
	}
}
