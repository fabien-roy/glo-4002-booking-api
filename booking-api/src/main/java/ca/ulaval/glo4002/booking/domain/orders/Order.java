package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.*;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.Passes;
import ca.ulaval.glo4002.booking.domain.passes.money.Money;

import java.util.List;

public class Order {

    private OrderNumber orderNumber;
    private OrderDate orderDate;
    private Passes passes;

    public Order(Id id) {
        this.orderNumber = new OrderNumber(id, null);
    }

    public Order(String vendorCode, OrderDate orderDate, Passes passes) {
        this.orderNumber = new OrderNumber(null, vendorCode);
        this.orderDate = orderDate;
        this.passes = passes;
    }

    public Id getId() {
        return orderNumber.getId();
    }

    public void setId(Id id) {
        orderNumber.setId(id);
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

    public Money getPrice() {
        return passes.getPrice();
    }
}