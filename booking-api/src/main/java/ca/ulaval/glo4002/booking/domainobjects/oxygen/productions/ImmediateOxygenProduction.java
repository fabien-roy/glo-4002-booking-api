package ca.ulaval.glo4002.booking.domainobjects.oxygen.productions;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.unitTypes.OxygenUnitType;

public class ImmediateOxygenProduction extends OxygenProduction {

    public ImmediateOxygenProduction(OxygenUnitType unitType) {
        this.id = OxygenConstants.Productions.IMMEDIATE_ID;
        this.name = OxygenConstants.Productions.IMMEDIATE_NAME;
        this.pricePerUnit = OxygenConstants.Productions.IMMEDIATE_PRICE_PER_UNIT;
        this.producedUnits = OxygenConstants.Productions.IMMEDIATE_PRODUCED_UNITS;
        this.producedTanks = OxygenConstants.Productions.IMMEDIATE_PRODUCED_TANKS;
        this.productionTime = OxygenConstants.Productions.IMMEDIATE_PRODUCTION_TIME;
        this.unitType = unitType;
    }
}
