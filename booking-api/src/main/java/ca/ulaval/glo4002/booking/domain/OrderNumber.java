package ca.ulaval.glo4002.booking.domain;

public class OrderNumber {

    private Id id;
    private String vendorCode;
    private String orderNumber;

    public static final String SEPARATOR = "-";

    public OrderNumber(Id id, String vendorCode) {
        this.id = id;
        this.vendorCode = vendorCode;
    }

    public OrderNumber(String orderNumber) {
        // TODO : Parse
    }

    public Id getId() {
        return id;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
