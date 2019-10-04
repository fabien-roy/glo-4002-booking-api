package ca.ulaval.glo4002.booking.domainobjects.oxygen.productions;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.unittypes.OxygenUnitType;

import java.time.Duration;

public abstract class OxygenProduction {

    protected Long id;
    protected String name;
    protected Double pricePerUnit;
    protected Long producedUnits;
    protected Long producedTanks;
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

    public Long getProducedUnits() {
        return producedUnits;
    }

    public Long getProducedTanks() {
        return producedTanks;
    }

    public Duration getProductionTime() {
        return productionTime;
    }

    public OxygenUnitType getUnitType() {
        return unitType;
    }
}
