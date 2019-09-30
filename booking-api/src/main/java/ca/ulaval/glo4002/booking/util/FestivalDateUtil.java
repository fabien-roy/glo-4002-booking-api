package ca.ulaval.glo4002.booking.util;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.exceptions.InvalidDateException;
import ca.ulaval.glo4002.booking.exceptions.InvalidDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public final class FestivalDateUtil {

	private FestivalDateUtil() {
		
	}

    public static LocalDate toLocalDate(String date) {
        try {
            return LocalDate.parse(date, FestivalConstants.Dates.DATE_FORMATTER);
        } catch (Exception exception) {
            throw new InvalidDateException();
        }
    }

	public static LocalDateTime toLocalDateTime(String date) {
	    try {
            return ZonedDateTime.parse(date, FestivalConstants.Dates.DATE_TIME_FORMATTER).toLocalDateTime();
        } catch (Exception exception) {
	        throw new InvalidDateTimeException();
        }
	}

    // TODO : This should not work this way
    public static String toZonedDateTimeString(LocalDateTime orderDate) {
        return orderDate.toString() + ":00.000Z";
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
