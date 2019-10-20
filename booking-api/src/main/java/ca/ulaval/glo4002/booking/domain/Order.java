package ca.ulaval.glo4002.booking.domain;

import java.math.BigDecimal;

public class Order {

    private OrderNumber orderNumber;
    private OrderDate orderDate;

    public Order(Id id) {
        this.orderNumber = new OrderNumber(id, null);
    }

    public Order(String orderNumber) {
        this.orderNumber = new OrderNumber(orderNumber);
    }

    public Order(String vendorCode, OrderDate orderDate) {
        this.orderNumber = new OrderNumber(null, vendorCode);
        this.orderDate = orderDate;
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

    public OrderDate getOrderDate() {
        return orderDate;
    }

    // TODO : Order.getPrice()
    public Money getPrice() {
        return new Money(new BigDecimal(0));
    }
}
