package ca.ulaval.glo4002.booking.entities;

public class Vendor {
    private Long id;
    private String name;

    public Vendor(Long id, String name) {
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
