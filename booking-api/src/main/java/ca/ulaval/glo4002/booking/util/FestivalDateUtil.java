package ca.ulaval.glo4002.booking.util;

import ca.ulaval.glo4002.booking.constants.DateConstants;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateException;
import ca.ulaval.glo4002.booking.exceptions.dates.InvalidDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class FestivalDateUtil {

	private FestivalDateUtil() {
		
	}

    public static LocalDate toLocalDate(String date) {
        try {
            return LocalDate.parse(date, DateConstants.DATE_FORMATTER);
        } catch (Exception exception) {
            throw new InvalidDateException();
        }
    }

	public static LocalDateTime toLocalDateTime(String date) {
	    try {
            return ZonedDateTime.parse(date, DateConstants.DATE_TIME_FORMATTER).toLocalDateTime();
        } catch (Exception exception) {
	        throw new InvalidDateTimeException();
        }
	}

	public static String toZonedDateTimeString(LocalDate orderDate) {
		return toZonedDateTimeString(orderDate.atStartOfDay());
	}

    public static String toZonedDateTimeString(LocalDateTime orderDate) {
        return ZonedDateTime.of(orderDate, ZoneId.of("UTC")).toString().replaceAll("\\[UTC\\]", ""); //TODO: This is a ugly hack
    }

	public static boolean isOutsideFestivalDates(LocalDate date) {
		return date.isBefore(DateConstants.START_DATE)
				|| date.isAfter(DateConstants.END_DATE);
	}

	public static boolean isOutsideOrderDates(LocalDateTime date) {
		return date.isBefore(DateConstants.ORDER_START_DATE_TIME)
				|| date.isAfter(DateConstants.ORDER_END_DATE_TIME);
	}

	public static boolean isAfterFestivalStart(LocalDate date) {
		return date.isAfter(DateConstants.START_DATE);
	}
}
