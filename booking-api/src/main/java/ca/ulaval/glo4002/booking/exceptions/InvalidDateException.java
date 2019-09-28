package ca.ulaval.glo4002.booking.exceptions;

import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;

public class InvalidDateException extends RuntimeException {
	
	public InvalidDateException(Response.Status response) {
		super(ExceptionConstants.INVALID_EVENT_DATE_MESSAGE);
	}

}
