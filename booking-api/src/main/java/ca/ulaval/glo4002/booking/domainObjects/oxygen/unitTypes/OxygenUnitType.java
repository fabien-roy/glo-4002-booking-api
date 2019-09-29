package ca.ulaval.glo4002.booking.domainObjects.oxygen.unitTypes;

public abstract class OxygenUnitType {
    protected Long id;
    protected String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
