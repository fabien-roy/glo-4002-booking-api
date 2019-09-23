package ca.ulaval.glo4002.booking.entities.oxygen;

public class OxygenProduction {
    private Long id;
    private String name;
    private Double pricePerUnit;
    private Integer producedUnits;
    private Integer producedTanks;
    private Long productionTime; // TODO : Oxygen production time in days?
    private OxygenUnitType unitType;

    public OxygenProduction(Long id, String name, Double pricePerUnit, Integer producedUnits, Integer producedTanks, Long productionTime, OxygenUnitType unitType) {
        this.id = id;
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.producedUnits = producedUnits;
        this.producedTanks = producedTanks;
        this.productionTime = productionTime;
        this.unitType = unitType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public Integer getProducedUnits() {
        return producedUnits;
    }

    public Integer getProducedTanks() {
        return producedTanks;
    }

    public Long getProductionTime() {
        return productionTime;
    }

    public OxygenUnitType getUnitType() {
        return unitType;
    }
}
