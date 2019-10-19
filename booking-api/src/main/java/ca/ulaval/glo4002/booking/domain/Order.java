package ca.ulaval.glo4002.booking.domain;

public class Order {

    private StringId orderNumber;

    public Order(String orderNumber) {
        this.orderNumber = new StringId(orderNumber);
    }

    public String getOrderNumber() {
        return orderNumber.getValue();
    }
}
