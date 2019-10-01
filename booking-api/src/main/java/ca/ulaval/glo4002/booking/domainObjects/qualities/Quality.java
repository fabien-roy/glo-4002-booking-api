package ca.ulaval.glo4002.booking.domainObjects.qualities;

public abstract class Quality {

    protected Long id;
    protected String name;
    protected Long oxygenTanksNeededPerDay;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
