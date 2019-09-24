package ca.ulaval.glo4002.booking.entities.oxygen.productions;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.OxygenUnitType;

public class ElectrolytesOxygenProduction extends OxygenProduction {

    public ElectrolytesOxygenProduction(OxygenUnitType unitType) {
        this.id = OxygenConstants.Productions.ELECTROLYTES_ID;
        this.name = OxygenConstants.Productions.ELECTROLYTES_NAME;
        this.pricePerUnit = OxygenConstants.Productions.ELECTROLYTES_PRICE_PER_UNIT;
        this.producedUnits = OxygenConstants.Productions.ELECTROLYTES_PRODUCED_UNITS;
        this.producedTanks = OxygenConstants.Productions.ELECTROLYTES_PRODUCED_TANKS;
        this.productionTime = OxygenConstants.Productions.ELECTROLYTES_PRODUCTION_TIME;
        this.unitType = unitType;
    }
}
