package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.exceptions.orders.InvalidOrderNumberFormatException;

public class OrderNumber {

    private Number number;
    private String vendorCode;

    public static final String SEPARATOR = "-";

    public OrderNumber(Number number, String vendorCode) {
        this.number = number;
        this.vendorCode = vendorCode;
    }

    public OrderNumber(String orderNumber) {
        validateOrderNumber(orderNumber);

        int separatorIndex = orderNumber.indexOf(SEPARATOR);

        String vendorCode = orderNumber.substring(0, separatorIndex);
        String number = orderNumber.substring(separatorIndex + 1);

        this.number = new Number(number);
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

    private void validateOrderNumber(String orderNumber) {
        int numberOfSeparators = orderNumber.split(SEPARATOR).length - 1;

        if (numberOfSeparators != 1) {
            throw new InvalidOrderNumberFormatException();
        }
    }
}
