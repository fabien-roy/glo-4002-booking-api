package ca.ulaval.glo4002.booking.orders;

import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.interfaces.InvalidFormatException;

public class OrderNumber {

	private Number orderNumber;
	private String vendorCode;

	public static final String SEPARATOR = "-";

	public OrderNumber(Number orderNumber, String vendorCode) {
		this.orderNumber = orderNumber;
		this.vendorCode = vendorCode;
	}

	public OrderNumber(String orderNumber) {
		validateOrderNumber(orderNumber);

		int separatorIndex = orderNumber.indexOf(SEPARATOR);

		String parsedVendorCode = orderNumber.substring(0, separatorIndex);
		String parsedNumber = orderNumber.substring(separatorIndex + 1);

		this.orderNumber = new Number(parsedNumber);
		this.vendorCode = parsedVendorCode;
	}

	public Number getOrderNumber() {
		return orderNumber;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	@Override
	public String toString() {
		return vendorCode + SEPARATOR + orderNumber.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof OrderNumber))
			return false;

		OrderNumber otherOrderNumber = (OrderNumber) other;

		return orderNumber.equals(otherOrderNumber.getOrderNumber())
				&& vendorCode.equals(otherOrderNumber.getVendorCode());
	}

	@Override
	public int hashCode() {
		return orderNumber.hashCode() + vendorCode.hashCode();
	}

	private void validateOrderNumber(String orderNumber) {
		int numberOfSeparators = orderNumber.split(SEPARATOR).length - 1;

		if (numberOfSeparators != 1) {
			throw new InvalidFormatException();
		}
	}
}
