package ca.ulaval.glo4002.booking.entities.oxygen;

public class OxygenUnitType {
    private Long id;
    private String name;

    public OxygenUnitType(Long id, String name) {
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
