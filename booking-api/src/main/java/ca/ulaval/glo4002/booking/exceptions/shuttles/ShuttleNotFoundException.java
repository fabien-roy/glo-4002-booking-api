package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShuttleNotFoundException extends FestivalException {
	
	private static final long serialVersionUID = 2019_09_23_0947L;

	public ShuttleNotFoundException() {
		super(ExceptionConstants.SHUTTLE_NOT_FOUND_MESSAGE);
	}
}
