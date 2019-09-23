package ca.ulaval.glo4002.booking.entities.oxygen;

public class OxygenCategory {
    private Long id;
    private String name;
    private OxygenProduction production;

    public OxygenCategory(Long id, String name, OxygenProduction production) {
        this.id = id;
        this.name = name;
        this.production = production;
    }

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
