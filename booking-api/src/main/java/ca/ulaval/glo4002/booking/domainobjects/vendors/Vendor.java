package ca.ulaval.glo4002.booking.domainobjects.vendors;

public abstract class Vendor {

    protected Long id;
    protected String code;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
