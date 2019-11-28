package ca.ulaval.glo4002.booking.oxygen;

import java.time.LocalDate;

// TODO : Remove OxygenDate, use EventDate
public class OxygenDate {

	private LocalDate date;

	public OxygenDate(LocalDate value) {
		this.date = value;
	}

	@Override
	public String toString() {
		return date.toString();
	}

	public void addDays(int numberOfDays) {
		this.date = date.plusDays(numberOfDays);
	}

	public LocalDate getValue() {
		return date;
	}
}
