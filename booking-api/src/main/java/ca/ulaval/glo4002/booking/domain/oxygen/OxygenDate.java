package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.exceptions.oxygen.InvalidOxygenDateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OxygenDate {

	private LocalDateTime value;

	public OxygenDate(String value) {
		// TODO : OXY : This parsing must be in a Factory, not in the constructor
		try {
			this.value = ZonedDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
		} catch (Exception exception) {
			throw new InvalidOxygenDateFormatException();
		}
	}

	// TODO : Refactor
	public OxygenDate(LocalDate value) {
		this.value = value.atStartOfDay();
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public void addDays(int numberOfDays) {
		this.value = value.plusDays(numberOfDays);
	}

	public LocalDateTime getValue() {
		return value;
	}
}
