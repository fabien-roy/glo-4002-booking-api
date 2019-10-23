package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.*;
import ca.ulaval.glo4002.booking.domain.passes.Pass;
import ca.ulaval.glo4002.booking.domain.passes.PassOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private OrderNumber orderNumber;
    private OrderDate orderDate;
    private PassOption passOption;
    private List<Pass> passes;
    private Money price;

    public Order(Id id) {
        this.orderNumber = new OrderNumber(id, null);
    }

    public Order(String vendorCode, OrderDate orderDate, PassOption passOption) {
        this.orderNumber = new OrderNumber(null, vendorCode);
        this.orderDate = orderDate;
        this.passOption = passOption;
        this.passes = new ArrayList<>(); // TODO : Add passes here
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
        return price;
    }

    // TODO : Test calculatePrice
    // TODO : When should we calculate order price?
    public void calculatePrice() {
        Money passPrice = passOption.calculatePrice(passes);
        passes.forEach(pass -> pass.setPrice(passPrice));

        calculateTotalPrice(passPrice);
    }

    private void calculateTotalPrice(Money passPrice) {
        BigDecimal numberOfPasses = BigDecimal.valueOf(passes.size());
        BigDecimal totalValue = passPrice.getValue().multiply(numberOfPasses);

        price = new Money(totalValue);
    }
}