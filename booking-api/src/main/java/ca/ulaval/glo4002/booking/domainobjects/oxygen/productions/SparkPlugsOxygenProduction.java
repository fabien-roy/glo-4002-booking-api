package ca.ulaval.glo4002.booking.domainobjects.oxygen.productions;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.unittypes.OxygenUnitType;

public class SparkPlugsOxygenProduction extends OxygenProduction {

    public SparkPlugsOxygenProduction(OxygenUnitType unitType) {
        this.id = OxygenConstants.Productions.SPARK_PLUGS_ID;
        this.name = OxygenConstants.Productions.SPARK_PLUGS_NAME;
        this.pricePerUnit = OxygenConstants.Productions.SPARK_PLUGS_PRICE_PER_UNIT;
        this.producedUnits = OxygenConstants.Productions.SPARK_PLUGS_PRODUCED_UNITS;
        this.producedTanks = OxygenConstants.Productions.SPARK_PLUGS_PRODUCED_TANKS;
        this.productionTime = OxygenConstants.Productions.SPARK_PLUGS_PRODUCTION_TIME;
        this.unitType = unitType;
    }
}
