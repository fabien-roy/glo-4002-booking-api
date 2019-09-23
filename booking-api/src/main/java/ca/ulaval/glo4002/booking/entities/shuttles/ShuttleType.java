package ca.ulaval.glo4002.booking.entities.shuttles;

public class ShuttleType {
    private Long id;
    private String name;

    public ShuttleType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
