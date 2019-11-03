package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassBundle;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private OrderNumber orderNumber;
    private LocalDateTime orderDate;
    private PassBundle passBundle;

    public Order(OrderNumber orderNumber, LocalDateTime orderDate, PassBundle passBundle) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.passBundle = passBundle;
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

    public PassBundle getPassBundle() {
        return passBundle;
    }

    public Money getPrice() {
        return passBundle.getPrice();
    }

    public List<Pass> getPasses() {
        return passBundle.getPasses();
    }
}