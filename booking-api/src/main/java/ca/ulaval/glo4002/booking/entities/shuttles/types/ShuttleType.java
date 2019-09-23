package ca.ulaval.glo4002.booking.entities.shuttles.types;

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
