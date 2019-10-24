package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.domain.passes.money.Money;

import java.util.List;

public class Order {

    private OrderNumber orderNumber;
    private OrderDate orderDate;
    private PassList passList;

    // TODO : Only used by tests...
    public Order(Number id) {
        this.orderNumber = new OrderNumber(id, null);
        this.passList = new PassList();
    }

    public Order(String vendorCode, OrderDate orderDate, PassList passList) {
        this.orderNumber = new OrderNumber(null, vendorCode);
        this.orderDate = orderDate;
        this.passList = passList;
    }

    public void setNumber(Number number) {
        orderNumber.setNumber(number);
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

    public PassList getPassList() {
        return passList;
    }

    public Money getPrice() {
        return passList.getPrice();
    }

    public List<Pass> getPasses() {
        return passList.getPasses();
    }
}