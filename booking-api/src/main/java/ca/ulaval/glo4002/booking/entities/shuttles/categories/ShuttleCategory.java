package ca.ulaval.glo4002.booking.entities.shuttles.categories;

import ca.ulaval.glo4002.booking.entities.Qualifiable;
import ca.ulaval.glo4002.booking.entities.qualities.Quality;

public abstract class ShuttleCategory implements Qualifiable {
    protected Long id;
    protected String name;
    protected Integer maxCapacity;
    protected Double price;
    protected Quality quality;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Double getPrice() {
        return price;
    }

    public Quality getQuality() {
        return quality;
    }
}
