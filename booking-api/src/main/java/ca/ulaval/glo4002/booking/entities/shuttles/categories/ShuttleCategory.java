package ca.ulaval.glo4002.booking.entities.shuttles.categories;

public abstract class ShuttleCategory {
    protected Long id;
    protected String name;
    protected Integer maxCapacity;
    protected Double price;

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
}
