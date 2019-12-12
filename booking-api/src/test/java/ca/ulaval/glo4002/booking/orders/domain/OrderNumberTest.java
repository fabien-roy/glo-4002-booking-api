package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.interfaces.rest.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderNumberTest {

	private OrderNumber orderNumber;

	@Test
	void constructing_shouldThrowInvalidFormatException_whenOrderNumberIsInvalid() {
		String invalidOrderNumber = "invalid" + OrderNumber.SEPARATOR + "TEAM";

		assertThrows(InvalidFormatException.class, () -> new OrderNumber(invalidOrderNumber));
	}

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
		OrderIdentifier anOrderIdentifier = new OrderIdentifier(1L);
		String aVendor = "VENDOR";
		String expectedOrderNumber = aVendor + OrderNumber.SEPARATOR + anOrderIdentifier.toString();

		orderNumber = new OrderNumber(anOrderIdentifier, aVendor);
		String textOrderNumber = orderNumber.toString();

		assertEquals(expectedOrderNumber, textOrderNumber);
	}

	@Test
	void equals_shouldReturnFalse_whenObjectIsNotNumber() {
		orderNumber = new OrderNumber(new OrderIdentifier(1L), "VENDOR");
		Object anObject = new Object();

		boolean isSameObject = orderNumber.equals(anObject);

		assertFalse(isSameObject);
	}

	@Test
	void equals_shouldReturnFalse_whenNumberIsNotSameValue() {
		orderNumber = new OrderNumber(new OrderIdentifier(1L), "VENDOR");
		OrderNumber differentOrderNumber = new OrderNumber(new OrderIdentifier(2L), "VENDOR");

		boolean isSameOrderNumber = orderNumber.equals(differentOrderNumber);

		assertFalse(isSameOrderNumber);
	}

	@Test
	void equals_shouldReturnFalse_whenVendorCodeIsNotSameValue() {
		orderNumber = new OrderNumber(new OrderIdentifier(1L), "VENDOR");
		OrderNumber otherOrderNumber = new OrderNumber(new OrderIdentifier(1L), "OTHER_VENDOR");

		boolean result = orderNumber.equals(otherOrderNumber);

		assertFalse(result);
	}

	@Test
	void equals_shouldReturnFalse_whenSameValue() {
		orderNumber = new OrderNumber(new OrderIdentifier(1L), "VENDOR");
		OrderNumber otherOrderNumber = new OrderNumber(new OrderIdentifier(1L), "VENDOR");

		boolean result = orderNumber.equals(otherOrderNumber);

		assertTrue(result);
	}
}