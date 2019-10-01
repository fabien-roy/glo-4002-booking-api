package ca.ulaval.glo4002.booking.domainobjects.oxygen.categories;

import ca.ulaval.glo4002.booking.domainobjects.Qualifiable;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;

public abstract class OxygenCategory implements Qualifiable {

    protected Long id;
    protected String name;
    protected Quality quality;
    protected OxygenProduction production;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Quality getQuality() {
        return quality;
    }

    public OxygenProduction getProduction() {
        return production;
    }
}
