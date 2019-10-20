package ca.ulaval.glo4002.booking.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private OrderNumber orderNumber;
    private LocalDateTime orderDate;

    public Order(Id id) {
        this.orderNumber = new OrderNumber(id);
    }

    public Order(String orderNumber) {
        this.orderNumber = new OrderNumber(orderNumber);
    }

    public Id getId() {
        return orderNumber.getId();
    }

    public String getOrderNumber() {
        return orderNumber.getOrderNumber();
    }

    public String getVendorCode() {
        return orderNumber.getVendorCode();
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    // TODO : Order.getPrice()
    public Money getPrice() {
        return new Money(new BigDecimal(0));
    }
}
