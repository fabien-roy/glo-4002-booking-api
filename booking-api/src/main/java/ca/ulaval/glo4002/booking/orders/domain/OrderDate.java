package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.program.events.domain.EventDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDate {

	private LocalDateTime value;

	public OrderDate(LocalDateTime value) {
		this.value = value;
	}

	public LocalDateTime getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public boolean isBefore(OrderDate orderDate) {
		return this.value.isBefore(orderDate.getValue());
	}

	public boolean isAfter(OrderDate orderDate) {
		return this.value.isAfter(orderDate.getValue());
	}

	public boolean isBetweenOrEquals(OrderDate lowerDate, OrderDate higherDate) {
		return !this.isBefore(lowerDate) && !this.isAfter(higherDate);
	}

	// TODO : Test
	public LocalDate toLocalDate() {
		return value.toLocalDate();
	}
}