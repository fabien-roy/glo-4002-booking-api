package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.Number;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderNumberTest {

    @Test
    void toString_shouldReturnCorrectFormat() {
        Number aNumber = new Number(1L);
        String aVendor = "VENDOR";
        String expectedResult = aVendor + OrderNumber.SEPARATOR + aNumber.toString();
        OrderNumber subject = new OrderNumber(aNumber, aVendor);

        String result = subject.toString();

        assertEquals(expectedResult, result);
    }
}