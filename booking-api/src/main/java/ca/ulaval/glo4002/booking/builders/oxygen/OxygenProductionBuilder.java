package ca.ulaval.glo4002.booking.builders.oxygen;

import ca.ulaval.glo4002.booking.builders.Builder;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.productions.ElectrolytesOxygenProduction;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.productions.ImmediateOxygenProduction;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.productions.SparkPlugsOxygenProduction;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenProductionNotFoundException;

public class OxygenProductionBuilder implements Builder<OxygenProduction> {

    private OxygenUnitTypeBuilder unitTypeBuilder = new OxygenUnitTypeBuilder();

    public OxygenProduction buildById(Long id) {
        if (id.equals(OxygenConstants.Productions.IMMEDIATE_ID)) {
            return buildImmediateOxygenProduction();
        } else if (id.equals(OxygenConstants.Productions.ELECTROLYTES_ID)) {
            return buildElectrolytesOxygenProduction();
        } else if (id.equals(OxygenConstants.Productions.SPARK_PLUGS_ID)) {
            return buildSparkPlugsOxygenProduction();
        } else {
            throw new OxygenProductionNotFoundException();
        }
    }

    public OxygenProduction buildByName(String name) {
        if (name.equals(OxygenConstants.Productions.IMMEDIATE_NAME)) {
            return buildImmediateOxygenProduction();
        } else if (name.equals(OxygenConstants.Productions.ELECTROLYTES_NAME)) {
            return buildElectrolytesOxygenProduction();
        } else if (name.equals(OxygenConstants.Productions.SPARK_PLUGS_NAME)) {
            return buildSparkPlugsOxygenProduction();
        } else {
            throw new OxygenProductionNotFoundException();
        }
    }

    private OxygenProduction buildImmediateOxygenProduction() {
        return new ImmediateOxygenProduction(unitTypeBuilder.buildById(OxygenConstants.Productions.IMMEDIATE_UNIT_TYPE_ID));
    }

    private OxygenProduction buildElectrolytesOxygenProduction() {
        return new ElectrolytesOxygenProduction(unitTypeBuilder.buildById(OxygenConstants.Productions.ELECTROLYTES_UNIT_TYPE_ID));
    }

    private OxygenProduction buildSparkPlugsOxygenProduction() {
        return new SparkPlugsOxygenProduction(unitTypeBuilder.buildById(OxygenConstants.Productions.SPARK_PLUGS_UNIT_TYPE_ID));
    }
}
