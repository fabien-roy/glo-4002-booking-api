package ca.ulaval.glo4002.booking.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private Id id;
    private LocalDateTime orderDate;
    private String vendorCode;

    public Order(Id id) {
        this.id = id;
    }

    public Id getId() {
        return id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    // TODO : Order.getPrice()
    public Money getPrice() {
        return new Money(new BigDecimal(0));
    }

    public String getOrderNumber() {
        return vendorCode + "-" + id.getValue().toString();
    }
}
