package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.domain.Id;
import ca.ulaval.glo4002.booking.domain.Money;
import ca.ulaval.glo4002.booking.domain.Pass;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private Long orderCategory; // TODO : Make OrderCategory class
    private OrderNumber orderNumber;
    private OrderDate orderDate;
    private List<Pass> passes;
    private Money price;

    public Order(Id id) {
        this.orderNumber = new OrderNumber(id, null);
    }

    public Order(String orderNumber) {
        this.orderNumber = new OrderNumber(orderNumber);
    }

    public Order(Long orderCategory, List<Pass> passes) {
        this.orderCategory = orderCategory;
        this.passes = passes;
        this.price = new Money();
        calculatePrice();
    }

    public Order(String vendorCode, OrderDate orderDate) {
        this.orderNumber = new OrderNumber(null, vendorCode);
        this.orderDate = orderDate;
    }

    public Order(String vendorCode, OrderDate orderDate, List<Pass> passes) {
        this.orderNumber = new OrderNumber(null, vendorCode);
        this.orderDate = orderDate;
        this.passes = passes;
        calculatePrice();
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

    // TODO : Refractor this
    private void calculatePrice() {
        // TODO : if package then price = calculatePackagePrice()
        if (orderCategory == 1L && passes.size() >= 5) {
            adjustPassesPrice();
        }

        passes.forEach(pass -> price.add(pass.getPrice()));

        if (orderCategory == 2L && passes.size() > 3) {
            BigDecimal discount = BigDecimal.valueOf(.1);
            price.applyDiscount(discount);
        }
    }

    // TODO : Refractor this
    private void adjustPassesPrice() {
        passes.forEach(pass -> pass.setPrice(new BigDecimal(90000)));
    }
}