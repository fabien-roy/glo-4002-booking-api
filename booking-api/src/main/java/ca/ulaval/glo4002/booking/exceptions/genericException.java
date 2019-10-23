package ca.ulaval.glo4002.booking.exceptions;

//TODO its only to test if we can put exeptions directly inside functions, like ask in code review, by having a generic exeption creator.
public class genericException extends BookingException {

	public genericException(String exception) {
		super(exception.toUpperCase());

		description = exception;
	}
}
