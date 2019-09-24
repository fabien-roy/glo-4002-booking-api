package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.Order;
import ca.ulaval.glo4002.booking.entities.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.AOxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.BOxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.EOxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.ElectrolytesOxygenProduction;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.ImmediateOxygenProduction;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.SparkPlugsOxygenProduction;
import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.OxygenTankOxygenUnitType;
import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.OxygenUnitType;
import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.SparkPlugsOxygenUnitType;
import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.WaterLitersOxygenUnitType;
import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenProductionNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenUnitTypeNotFoundException;

import java.time.LocalDate;

public class OxygenFactory {

    public OxygenCategory getOxygenCategoryById(Long categoryId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (categoryId.equals(OxygenConstants.Categories.E_ID)) {
            return buildEOxygenCategory();
        } else if (categoryId.equals(OxygenConstants.Categories.B_ID)) {
            return buildBOxygenCategory();
        } else if (categoryId.equals(OxygenConstants.Categories.A_ID)) {
            return buildAOxygenCategory();
        } else {
            throw new OxygenCategoryNotFoundException();
        }
    }

    public OxygenProduction getOxygenProductionById(Long productionId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (productionId.equals(OxygenConstants.Productions.IMMEDIATE_ID)) {
            return buildImmediateOxygenProduction();
        } else if (productionId.equals(OxygenConstants.Productions.ELECTROLYTES_ID)) {
            return buildElectrolytesOxygenProduction();
        } else if (productionId.equals(OxygenConstants.Productions.SPARK_PLUGS_ID)) {
            return buildSparkPlugsOxygenProduction();
        } else {
            throw new OxygenProductionNotFoundException();
        }
    }

    public OxygenUnitType getOxygenUnitTypeById(Long unitTypeId) {
        // TODO : I wanted to do a switch-case, but apparently static final isn't const?
        if (unitTypeId.equals(OxygenConstants.UnitTypes.OXYGEN_TANKS_ID)) {
            return buildOxygenTanksOxygenUnitType();
        } else if (unitTypeId.equals(OxygenConstants.UnitTypes.WATER_LITERS_ID)) {
            return buildWaterLitersOxygenUnitType();
        } else if (unitTypeId.equals(OxygenConstants.UnitTypes.SPARK_PLUGS_ID)) {
            return buildSparkPlugsOxygenUnitType();
        } else {
            throw new OxygenUnitTypeNotFoundException();
        }
    }

    public OxygenTank createOxygenTank(PassCategory passCategory, Integer passNumber, LocalDate orderDate) {
       return null;
    }

    private OxygenCategory buildEOxygenCategory() {
        return new EOxygenCategory(getOxygenProductionById(OxygenConstants.Categories.E_PRODUCTION_ID));
    }

    private OxygenCategory buildBOxygenCategory() {
        return new BOxygenCategory(getOxygenProductionById(OxygenConstants.Categories.B_PRODUCTION_ID));
    }

    private OxygenCategory buildAOxygenCategory() {
        return new AOxygenCategory(getOxygenProductionById(OxygenConstants.Categories.A_PRODUCTION_ID));
    }

    private OxygenProduction buildImmediateOxygenProduction() {
        return new ImmediateOxygenProduction(getOxygenUnitTypeById(OxygenConstants.Productions.IMMEDIATE_UNIT_TYPE_ID));
    }

    private OxygenProduction buildElectrolytesOxygenProduction() {
        return new ElectrolytesOxygenProduction(getOxygenUnitTypeById(OxygenConstants.Productions.ELECTROLYTES_UNIT_TYPE_ID));
    }

    private OxygenProduction buildSparkPlugsOxygenProduction() {
        return new SparkPlugsOxygenProduction(getOxygenUnitTypeById(OxygenConstants.Productions.SPARK_PLUGS_UNIT_TYPE_ID));
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
