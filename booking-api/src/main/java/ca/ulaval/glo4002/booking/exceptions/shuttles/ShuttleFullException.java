package ca.ulaval.glo4002.booking.exceptions.shuttles;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.exceptions.FestivalException;

public class ShuttleFullException extends FestivalException {
	
	public ShuttleFullException() {
		super(ExceptionConstants.SHUTTLE_FULL_MESSAGE);
	}

}
