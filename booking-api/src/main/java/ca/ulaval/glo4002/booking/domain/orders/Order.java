package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.domain.NumberGenerator;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassList;
import ca.ulaval.glo4002.booking.domain.passes.money.Money;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private OrderNumber orderNumber;
    private LocalDateTime orderDate;
    private PassList passList;

    public Order(OrderNumber orderNumber, LocalDateTime orderDate, PassList passList) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.passList = passList;
    }

    public OrderNumber getOrderNumber() {
        return orderNumber;
    }

    public String getVendorCode() {
        return orderNumber.getVendorCode();
    }

    public LocalDateTime getOrderDate() {
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