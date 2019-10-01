package ca.ulaval.glo4002.booking.domainobjects.trips.types;

public abstract class TripType {

    protected Long id;
    protected String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
