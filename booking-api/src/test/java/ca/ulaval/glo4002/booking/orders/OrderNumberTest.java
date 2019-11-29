package ca.ulaval.glo4002.booking.orders;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.numbers.Number;
import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;

class OrderNumberTest {

	private OrderNumber orderNumber;

	@Test
	void constructing_shouldThrowInvalidFormatException_whenOrderNumberHasNoSeparator() {
		String orderNumberWithoutSeparator = "123TEAM";

		assertThrows(InvalidFormatException.class, () -> new OrderNumber(orderNumberWithoutSeparator));
	}

	@Test
	void constructing_shouldThrowInvalidFormatException_whenOrderNumberHasMultipleSeparator() {
		String orderNumberMultipleSeparators = "123" + OrderNumber.SEPARATOR + "TEAM" + OrderNumber.SEPARATOR + "YEAH";

		assertThrows(InvalidFormatException.class, () -> new OrderNumber(orderNumberMultipleSeparators));
	}

	@Test
	void toString_shouldReturnCorrectFormat() {
		Number anOrderNumber = new Number(1L);
		String aVendor = "VENDOR";
		String expectedOrderNumber = aVendor + OrderNumber.SEPARATOR + anOrderNumber.toString();

		orderNumber = new OrderNumber(anOrderNumber, aVendor);
		String textOrderNumber = orderNumber.toString();

		assertEquals(expectedOrderNumber, textOrderNumber);
	}

	@Test
	void equals_shouldReturnFalse_whenObjectIsNotNumber() {
		orderNumber = new OrderNumber(new Number(1L), "VENDOR");
		Object anObject = new Object();

		boolean isSameObject = orderNumber.equals(anObject);

		assertFalse(isSameObject);
	}

	@Test
	void equals_shouldReturnFalse_whenNumberIsNotSameValue() {
		orderNumber = new OrderNumber(new Number(1L), "VENDOR");
		OrderNumber differentOrderNumber = new OrderNumber(new Number(2L), "VENDOR");

		boolean isSameOrderNumber = orderNumber.equals(differentOrderNumber);

		assertFalse(isSameOrderNumber);
	}

	@Test
	void equals_shouldReturnFalse_whenVendorCodeIsNotSameValue() {
		orderNumber = new OrderNumber(new Number(1L), "VENDOR");
		OrderNumber otherOrderNumber = new OrderNumber(new Number(1L), "OTHER_VENDOR");

		boolean result = orderNumber.equals(otherOrderNumber);

		assertFalse(result);
	}

	@Test
	void equals_shouldReturnFalse_whenSameValue() {
		orderNumber = new OrderNumber(new Number(1L), "VENDOR");
		OrderNumber otherOrderNumber = new OrderNumber(new Number(1L), "VENDOR");

		boolean result = orderNumber.equals(otherOrderNumber);

		assertTrue(result);
	}

	@Test
	void hashCode_shouldReturnNumberPlusVendorCodeHashCode() {
		Number aNumber = new Number(1L);
		String aVendorCode = "VENDOR";
		int expectedHashCode = aNumber.hashCode() + aVendorCode.hashCode();
		orderNumber = new OrderNumber(aNumber, aVendorCode);

		int hashCode = orderNumber.hashCode();

		assertEquals(expectedHashCode, hashCode);
	}
}