package ca.ulaval.glo4002.booking.domain;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import ca.ulaval.glo4002.booking.exceptions.genericException;

public class OxygenDate {
	private LocalDateTime date;

	public OxygenDate(String date) {
		try {
			this.date = ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
		} catch (Exception exception) {
			String error = "Invalid oxygen date format";
			throw new genericException(error);
		}
	}

	@Override
	public String toString() {
		return this.date.toString();
	}
}
