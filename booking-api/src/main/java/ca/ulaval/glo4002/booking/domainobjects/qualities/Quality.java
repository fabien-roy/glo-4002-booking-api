package ca.ulaval.glo4002.booking.domainobjects.qualities;

public abstract class Quality {

    protected Long id;
    protected String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
