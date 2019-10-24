package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;

public class OrderNumber {

    private Number number;
    private String vendorCode;

    public static final String SEPARATOR = "-";

    public OrderNumber(Number number, String vendorCode) {
        this.number = number;
        this.vendorCode = vendorCode;
    }

    public Number getNumber() {
        return number;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    @Override
    public String toString() {
        return vendorCode + SEPARATOR + number.toString();
    }
}
