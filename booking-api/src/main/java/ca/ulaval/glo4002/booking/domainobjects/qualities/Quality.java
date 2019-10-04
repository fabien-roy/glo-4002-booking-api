package ca.ulaval.glo4002.booking.domainobjects.qualities;

public abstract class Quality {

    protected Long id;
    protected String name;
    protected Long oxygenTanksNeededPerDay; // TODO : OXY : Useless?

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getOxygenTanksNeededPerDay(){ return oxygenTanksNeededPerDay; }
}
