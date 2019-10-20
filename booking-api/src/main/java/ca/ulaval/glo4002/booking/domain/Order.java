package ca.ulaval.glo4002.booking.domain;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private Long orderCategory;
    private OrderNumber orderNumber;
    private OrderDate orderDate;
    private List<Pass> passes;
    private Money totalPrice;

    public Order(Id id) {
        this.orderNumber = new OrderNumber(id, null);
    }

    public Order(String orderNumber) {
        this.orderNumber = new OrderNumber(orderNumber);
    }

    public Order(Long orderCategory, List<Pass> passes) {
        this.orderCategory = orderCategory;
        this.passes = passes;
        this.totalPrice = new Money();
        calculateTotalPrice();
    }

    public Order(String vendorCode, OrderDate orderDate) {
        this.orderNumber = new OrderNumber(null, vendorCode);
        this.orderDate = orderDate;
    }

    public Order(String vendorCode, OrderDate orderDate, List<Pass> passes) {
        this.orderNumber = new OrderNumber(null, vendorCode);
        this.orderDate = orderDate;
        this.passes = passes;
        calculateTotalPrice();
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

    // TODO : Order.getPrice()
    public Money getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        // TODO : if package then totalPrice = calculatePackagePrice()
        if(orderCategory == 1L && passes.size() >= 5) {
            adjustPassesPrice();
        }

        passes.forEach(pass -> totalPrice.add(pass.getPrice()));

        if(orderCategory == 2L && passes.size() > 3) {
            totalPrice.applyDiscountPercentage(.10);
        }
    }

    private void adjustPassesPrice() {
        passes.forEach(pass -> pass.setPrice(new BigDecimal(90000)));
    }

}