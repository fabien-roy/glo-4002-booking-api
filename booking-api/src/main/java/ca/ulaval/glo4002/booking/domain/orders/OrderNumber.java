package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.Id;
import ca.ulaval.glo4002.booking.exceptions.InvalidOrderNumberFormatException;

public class OrderNumber {

    private Id id;
    private String vendorCode;

    public static final String SEPARATOR = "-";

    public OrderNumber(Id id, String vendorCode) {
        this.id = id;
        this.vendorCode = vendorCode;
    }

    public OrderNumber(String orderNumber) {
        validateOrderNumber(orderNumber);

        int separatorIndex = orderNumber.indexOf(SEPARATOR);

        String vendorCode = orderNumber.substring(0, separatorIndex);
        String id = orderNumber.substring(separatorIndex + 1);

        this.id = new Id(id);
        this.vendorCode = vendorCode;
    }

    private void validateOrderNumber(String orderNumber) {
        int numberOfSeparators = orderNumber.split(SEPARATOR).length - 1;

        if (numberOfSeparators != 1) {
            throw new InvalidOrderNumberFormatException();
        }
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public String getOrderNumber() {
        return id.toString() + SEPARATOR + vendorCode;
    }
}
