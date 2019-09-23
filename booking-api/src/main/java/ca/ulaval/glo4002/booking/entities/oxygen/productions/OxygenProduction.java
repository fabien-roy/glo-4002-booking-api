package ca.ulaval.glo4002.booking.entities.oxygen.productions;

import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.OxygenUnitType;

import java.time.Duration;

public abstract class OxygenProduction {
    protected Long id;
    protected String name;
    protected Double pricePerUnit;
    protected Integer producedUnits;
    protected Integer producedTanks;
    protected Duration productionTime;
    protected OxygenUnitType unitType;

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
