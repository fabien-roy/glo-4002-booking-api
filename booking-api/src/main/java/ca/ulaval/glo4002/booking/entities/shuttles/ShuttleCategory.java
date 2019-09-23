package ca.ulaval.glo4002.booking.entities.shuttles;

public class ShuttleCategory {
    private Long id;
    private String name;
    private Integer maxCapacity;
    private Double price;

    public ShuttleCategory(Long id, String name, Integer maxCapacity, Double price) {
        this.id = id;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.price = price;
    }

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
