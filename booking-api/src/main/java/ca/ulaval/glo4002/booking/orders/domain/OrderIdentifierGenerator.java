package ca.ulaval.glo4002.booking.orders.domain;

public class OrderIdentifierGenerator {

    private OrderIdentifier nextNumber; // TODO : Make this thread safe

    public OrderIdentifierGenerator() {
        this.nextNumber = new OrderIdentifier(100000L);
    }

    public OrderIdentifier generate() {
        OrderIdentifier number = nextNumber;
        nextNumber = new OrderIdentifier(nextNumber.getValue() + 1);

        return number;
    }
}
