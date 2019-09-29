package ca.ulaval.glo4002.booking.validators;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class FestivalDateValidator {

	private FestivalDateValidator() {
		
	}
	
	public static boolean isOutsideFestivalDates(LocalDate date) {
		return date.isBefore(FestivalConstants.Dates.START_DATE) 
				|| date.isAfter(FestivalConstants.Dates.END_DATE);
	}

	public static boolean isOutsideOrderDates(LocalDateTime date) {
		return date.isBefore(FestivalConstants.Dates.ORDER_START_DATE_TIME)
				|| date.isAfter(FestivalConstants.Dates.ORDER_END_DATE_TIME);
	}
}
