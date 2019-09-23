package ca.ulaval.glo4002.booking.entities.oxygen;

import java.time.Duration;

public class OxygenProduction {
    private Long id;
    private String name;
    private Double pricePerUnit;
    private Integer producedUnits;
    private Integer producedTanks;
    private Duration productionTime; // TODO : Oxygen production time in days?
    private OxygenUnitType unitType;

    public OxygenProduction(Long id, String name, Double pricePerUnit, Integer producedUnits, Integer producedTanks, Duration productionTime, OxygenUnitType unitType) {
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

    public Duration getProductionTime() {
        return productionTime;
    }

    public OxygenUnitType getUnitType() {
        return unitType;
    }
}
