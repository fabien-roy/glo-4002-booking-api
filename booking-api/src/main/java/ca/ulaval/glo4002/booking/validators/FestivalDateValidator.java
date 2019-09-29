package ca.ulaval.glo4002.booking.validators;

import java.time.LocalDate;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;

public final class FestivalDateValidator {

	private FestivalDateValidator() {
		
	}
	
	public static boolean dateIsOutsideFestivalDates(LocalDate date) {
		return date.isBefore(FestivalConstants.Dates.START_DATE) 
				|| date.isAfter(FestivalConstants.Dates.END_DATE);
	}
}
