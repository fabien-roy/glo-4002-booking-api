package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

public class OrderNumber {

	private OrderIdentifier orderIdentifier;
	private String vendorCode;

	public static final String SEPARATOR = "-";

	public OrderNumber(OrderIdentifier orderIdentifier, String vendorCode) {
		this.orderIdentifier = orderIdentifier;
		this.vendorCode = vendorCode;
	}

	public OrderNumber(String orderIdentifier) {
		validateOrderNumber(orderIdentifier);

		int separatorIndex = orderIdentifier.indexOf(SEPARATOR);

		String parsedVendorCode = orderIdentifier.substring(0, separatorIndex);
		String parsedNumber = orderIdentifier.substring(separatorIndex + 1);

		this.orderIdentifier = new OrderIdentifier(parsedNumber);
		this.vendorCode = parsedVendorCode;
	}

	public OrderIdentifier getOrderIdentifier() {
		return orderIdentifier;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	@Override
	public String toString() {
		return vendorCode + SEPARATOR + orderIdentifier.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof OrderNumber))
			return false;

		OrderNumber otherOrderNumber = (OrderNumber) other;

		return orderIdentifier.equals(otherOrderNumber.getOrderIdentifier())
				&& vendorCode.equals(otherOrderNumber.getVendorCode());
	}

	@Override
	public int hashCode() {
		return orderIdentifier.hashCode() + vendorCode.hashCode();
	}

	private void validateOrderNumber(String orderNumber) {
		int numberOfSeparators = orderNumber.split(SEPARATOR).length - 1;

		if (numberOfSeparators != 1) {
			throw new InvalidFormatException();
		}
	}
}
