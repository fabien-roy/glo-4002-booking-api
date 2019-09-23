package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.oxygen.OxygenUnitType;

public class OxygenFactory {

    public OxygenCategory getOxygenCategoryById(Long categoryId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (categoryId.equals(OxygenConstants.Categories.E_ID)) {
            return buildOxygenCategoryE();
        } else if (categoryId.equals(OxygenConstants.Categories.B_ID)) {
            return buildOxygenCategoryB();
        } else if (categoryId.equals(OxygenConstants.Categories.A_ID)) {
            return buildOxygenCategoryA();
        }

        return null; // TODO : Throw exception?
    }

    public OxygenProduction getOxygenProductionById(Long productionId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (productionId.equals(OxygenConstants.Productions.IMMEDIATE_ID)) {
            return buildOxygenProductionImmediate();
        } else if (productionId.equals(OxygenConstants.Productions.ELECTROLYTES_ID)) {
            return buildOxygenProductionElectrolytes();
        } else if (productionId.equals(OxygenConstants.Productions.SPARK_PLUGS_ID)) {
            return buildOxygenProductionSparkPlugs();
        }

        return null; // TODO : Throw exception?
    }

    public OxygenUnitType getOxygenUnitTypeById(Long unitTypeId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (unitTypeId.equals(OxygenConstants.UnitTypes.OXYGEN_TANKS_ID)) {
            return buildOxygenUnitTypeOxygenTanks();
        } else if (unitTypeId.equals(OxygenConstants.UnitTypes.WATER_LITERS_ID)) {
            return buildOxygenUnitTypeWaterLiters();
        } else if (unitTypeId.equals(OxygenConstants.UnitTypes.SPARK_PLUGS_ID)) {
            return buildOxygenUnitTypeSparkPlugs();
        }

        return null; // TODO : Throw exception?
    }

    private OxygenCategory buildOxygenCategoryE() {
        return new OxygenCategory(
                OxygenConstants.Categories.E_ID,
                OxygenConstants.Categories.E_NAME,
                getOxygenProductionById(OxygenConstants.Categories.E_PRODUCTION_ID));
    }

    private OxygenCategory buildOxygenCategoryB() {
        return new OxygenCategory(
                OxygenConstants.Categories.B_ID,
                OxygenConstants.Categories.B_NAME,
                getOxygenProductionById(OxygenConstants.Categories.B_PRODUCTION_ID));
    }

    private OxygenCategory buildOxygenCategoryA() {
        return new OxygenCategory(
                OxygenConstants.Categories.A_ID,
                OxygenConstants.Categories.A_NAME,
                getOxygenProductionById(OxygenConstants.Categories.A_PRODUCTION_ID));
    }

    private OxygenProduction buildOxygenProductionImmediate() {
        return new OxygenProduction(
                OxygenConstants.Productions.IMMEDIATE_ID,
                OxygenConstants.Productions.IMMEDIATE_NAME,
                OxygenConstants.Productions.IMMEDIATE_PRICE_PER_UNIT,
                OxygenConstants.Productions.IMMEDIATE_PRODUCED_UNITS,
                OxygenConstants.Productions.IMMEDIATE_PRODUCED_TANKS,
                OxygenConstants.Productions.IMMEDIATE_PRODUCTION_TIME,
                getOxygenUnitTypeById(OxygenConstants.Productions.IMMEDIATE_UNIT_TYPE_ID)
        );
    }

    private OxygenProduction buildOxygenProductionElectrolytes() {
        return new OxygenProduction(
                OxygenConstants.Productions.ELECTROLYTES_ID,
                OxygenConstants.Productions.ELECTROLYTES_NAME,
                OxygenConstants.Productions.ELECTROLYTES_PRICE_PER_UNIT,
                OxygenConstants.Productions.ELECTROLYTES_PRODUCED_UNITS,
                OxygenConstants.Productions.ELECTROLYTES_PRODUCED_TANKS,
                OxygenConstants.Productions.ELECTROLYTES_PRODUCTION_TIME,
                getOxygenUnitTypeById(OxygenConstants.Productions.ELECTROLYTES_UNIT_TYPE_ID)
        );
    }

    private OxygenProduction buildOxygenProductionSparkPlugs() {
        return new OxygenProduction(
                OxygenConstants.Productions.SPARK_PLUGS_ID,
                OxygenConstants.Productions.SPARK_PLUGS_NAME,
                OxygenConstants.Productions.SPARK_PLUGS_PRICE_PER_UNIT,
                OxygenConstants.Productions.SPARK_PLUGS_PRODUCED_UNITS,
                OxygenConstants.Productions.SPARK_PLUGS_PRODUCED_TANKS,
                OxygenConstants.Productions.SPARK_PLUGS_PRODUCTION_TIME,
                getOxygenUnitTypeById(OxygenConstants.Productions.SPARK_PLUGS_UNIT_TYPE_ID)
        );
    }

    private OxygenUnitType buildOxygenUnitTypeOxygenTanks() {
        return new OxygenUnitType(
                OxygenConstants.UnitTypes.OXYGEN_TANKS_ID,
                OxygenConstants.UnitTypes.OXYGEN_TANKS_NAME
        );
    }

    private OxygenUnitType buildOxygenUnitTypeWaterLiters() {
        return new OxygenUnitType(
                OxygenConstants.UnitTypes.WATER_LITERS_ID,
                OxygenConstants.UnitTypes.WATER_LITERS_NAME
        );
    }

    private OxygenUnitType buildOxygenUnitTypeSparkPlugs() {
        return new OxygenUnitType(
                OxygenConstants.UnitTypes.SPARK_PLUGS_ID,
                OxygenConstants.UnitTypes.SPARK_PLUGS_NAME
        );
    }
}
