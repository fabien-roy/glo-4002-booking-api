package ca.ulaval.glo4002.booking.builders.oxygen;

import ca.ulaval.glo4002.booking.builders.Builder;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.OxygenTankOxygenUnitType;
import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.OxygenUnitType;
import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.SparkPlugsOxygenUnitType;
import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.WaterLitersOxygenUnitType;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenUnitTypeNotFoundException;

public class OxygenUnitTypeBuilder implements Builder<OxygenUnitType> {

    public OxygenUnitType buildById(Long id) {
        if (id.equals(OxygenConstants.UnitTypes.OXYGEN_TANKS_ID)) {
            return buildOxygenTanksOxygenUnitType();
        } else if (id.equals(OxygenConstants.UnitTypes.WATER_LITERS_ID)) {
            return buildWaterLitersOxygenUnitType();
        } else if (id.equals(OxygenConstants.UnitTypes.SPARK_PLUGS_ID)) {
            return buildSparkPlugsOxygenUnitType();
        } else {
            throw new OxygenUnitTypeNotFoundException();
        }
    }

    public OxygenUnitType buildByName(String name) {
        if (name.equals(OxygenConstants.UnitTypes.OXYGEN_TANKS_NAME)) {
            return buildOxygenTanksOxygenUnitType();
        } else if (name.equals(OxygenConstants.UnitTypes.WATER_LITERS_NAME)) {
            return buildWaterLitersOxygenUnitType();
        } else if (name.equals(OxygenConstants.UnitTypes.SPARK_PLUGS_NAME)) {
            return buildSparkPlugsOxygenUnitType();
        } else {
            throw new OxygenUnitTypeNotFoundException();
        }
    }

    private OxygenUnitType buildOxygenTanksOxygenUnitType() {
        return new OxygenTankOxygenUnitType();
    }

    private OxygenUnitType buildWaterLitersOxygenUnitType() {
        return new WaterLitersOxygenUnitType();
    }

    private OxygenUnitType buildSparkPlugsOxygenUnitType() {
        return new SparkPlugsOxygenUnitType();
    }
}
