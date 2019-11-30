package ca.ulaval.glo4002.booking.orders.domain;

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

	public OrderDate plusDays(int days) {
		return new OrderDate(this.value.plusDays(days));
	}

	public OrderDate minusDays(int days) {
		return new OrderDate(this.value.minusDays(days));
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

	@Override
	public String toString() {
		return value.toString();
	}

	// TODO : Test OrderDate.equals
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof OrderDate)) return false;

		OrderDate otherOrderDate = (OrderDate) other;

		return this.value.equals(otherOrderDate.getValue());
	}

	// TODO : Test OrderDate.hashCode
	@Override
	public int hashCode() {
		return value.hashCode();
	}
}