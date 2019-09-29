package ca.ulaval.glo4002.booking.domainObjects.shuttles.types;

public abstract class ShuttleType {
    protected Long id;
    protected String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
