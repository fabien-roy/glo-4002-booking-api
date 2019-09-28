package ca.ulaval.glo4002.booking.entities.vendors;

public abstract class Vendor {
    protected Long id;
    protected String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
