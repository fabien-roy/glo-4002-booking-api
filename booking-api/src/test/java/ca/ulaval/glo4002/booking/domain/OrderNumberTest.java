package ca.ulaval.glo4002.booking.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderNumberTest {

    private OrderNumber subject;

    @Test
    void getVendorCode_shouldReturnCorrectVendorCode() {
        String expectedVendorCode = "VENDOR";

        subject = new OrderNumber(expectedVendorCode + OrderNumber.SEPARATOR + "123");


    }

    @Test
    void getOrderNumber() {
    }
}