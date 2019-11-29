package ca.ulaval.glo4002.booking.orders;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

public class OrderDate {

	public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2050, 1, 1, 0, 0);
	public static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2050, 7, 17, 0, 0);
	private LocalDateTime date;

	public OrderDate(String textDate) {
		LocalDateTime parsedDate;

		try {
			parsedDate = ZonedDateTime.parse(textDate, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
		} catch (Exception exception) {
			throw new InvalidFormatException();
		}

		validateOrderDate(parsedDate);

		this.date = parsedDate;
	}

	public LocalDateTime getDate() {
		return date;
	}

	@Override
	public String toString() {
		return date.toString();
	}

	private void validateOrderDate(LocalDateTime date) {
		if (date.isBefore(START_DATE_TIME) || date.isAfter(END_DATE_TIME)) {
			throw new InvalidOrderDateException();
		}
	}

}