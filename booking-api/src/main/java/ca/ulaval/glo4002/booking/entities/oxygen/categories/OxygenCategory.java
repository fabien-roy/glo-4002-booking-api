package ca.ulaval.glo4002.booking.entities.oxygen.categories;

import ca.ulaval.glo4002.booking.entities.Qualifiable;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.qualities.Quality;

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
