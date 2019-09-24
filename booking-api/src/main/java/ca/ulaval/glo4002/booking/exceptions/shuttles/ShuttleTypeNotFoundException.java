package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShuttleTypeNotFoundException extends RuntimeException {

	public ShuttleTypeNotFoundException() {
		super(ExceptionConstants.SHUTTLE_TYPE_NOT_FOUND_MESSAGE);
	}
}
