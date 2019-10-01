package ca.ulaval.glo4002.booking.domainobjects.orders;

public abstract class OrderItem implements Priceable {

    protected Long id;

    public Long getId() {
        return id;
    }
}
