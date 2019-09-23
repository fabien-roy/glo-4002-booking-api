package ca.ulaval.glo4002.booking.entities.oxygen.categories;

import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;

public abstract class OxygenCategory {
    protected Long id;
    protected String name;
    protected OxygenProduction production;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OxygenProduction getProduction() {
        return production;
    }
}
