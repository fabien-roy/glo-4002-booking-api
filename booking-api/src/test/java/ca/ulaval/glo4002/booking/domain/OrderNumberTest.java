package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.exceptions.InvalidOrderNumberFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderNumberTest {

    private OrderNumber subject;

    @Test
    void constructing_shouldThrowInvalidOrderNumberFormatException_whenOrderNumberHasNoSeparator() {
        String orderNumberWithoutSeparator = "123TEAM";

        assertThrows(
                InvalidOrderNumberFormatException.class,
                () -> new OrderNumber(orderNumberWithoutSeparator)
        );
    }

    @Test
    void constructing_shouldThrowInvalidOrderNumberFormatException_whenOrderNumberHasMultipleSeparator() {
        String orderNumberMultipleSeparators = "123" + OrderNumber.SEPARATOR + "TEAM" + OrderNumber.SEPARATOR + "YEAH";

        assertThrows(
                InvalidOrderNumberFormatException.class,
                () -> new OrderNumber(orderNumberMultipleSeparators)
        );
    }

    @Test
    void getVendorCode_shouldReturnCorrectVendorCode() {
        String expectedVendorCode = "VENDOR";

        subject = new OrderNumber(expectedVendorCode + OrderNumber.SEPARATOR + "123");

        assertEquals(expectedVendorCode, subject.getVendorCode());
    }

    @Test
    void getId_shouldReturnCorrectId() {
        Id expectedId = new Id(1L);

        subject = new OrderNumber("VENDOR" + OrderNumber.SEPARATOR + expectedId.toString());

        assertEquals(expectedId.getValue(), subject.getId().getValue());
    }
}