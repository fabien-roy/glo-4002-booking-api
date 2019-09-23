package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.oxygen.OxygenUnitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class OxygenFactoryTest {
    private OxygenFactory subject;

    @BeforeEach
    public void setUp(){
        subject = new OxygenFactory();
    }

    @Test
    public void getOxygenCategoryById_shouldReturnNull_whenCategoryDoesNotExist() {
        OxygenCategory oxygenCategory = subject.getOxygenCategoryById(-1L);

        assertNull(oxygenCategory);
    }

    @Test
    public void getOxygenCategoryById_shouldReturnCorrectOxygenCategory_whenCategoryIsE() {
        OxygenCategory oxygenCategory = subject.getOxygenCategoryById(OxygenConstants.Categories.E_ID);

        validateOxygenCategory(
                oxygenCategory,
                OxygenConstants.Categories.E_ID,
                OxygenConstants.Categories.E_NAME,
                OxygenConstants.Categories.E_PRODUCTION_ID
        );
    }

    @Test
    public void getOxygenCategoryById_shouldReturnCorrectOxygenCategory_whenCategoryIsB() {
        OxygenCategory oxygenCategory = subject.getOxygenCategoryById(OxygenConstants.Categories.B_ID);

        validateOxygenCategory(
                oxygenCategory,
                OxygenConstants.Categories.B_ID,
                OxygenConstants.Categories.B_NAME,
                OxygenConstants.Categories.B_PRODUCTION_ID
        );
    }

    @Test
    public void getOxygenCategoryById_shouldReturnCorrectOxygenCategory_whenCategoryIsA() {
        OxygenCategory oxygenCategory = subject.getOxygenCategoryById(OxygenConstants.Categories.A_ID);

        validateOxygenCategory(
                oxygenCategory,
                OxygenConstants.Categories.A_ID,
                OxygenConstants.Categories.A_NAME,
                OxygenConstants.Categories.A_PRODUCTION_ID
        );
    }

    @Test
    public void getOxygenProductionById_shouldReturnNull_whenProductionDoesNotExist() {
        OxygenProduction oxygenProduction = subject.getOxygenProductionById(-1L);

        assertNull(oxygenProduction);
    }

    @Test
    public void getOxygenProductionById_shouldReturnCorrectOxygenProduction_whenProductionIsImmediate() {
        OxygenProduction oxygenProduction = subject.getOxygenProductionById(OxygenConstants.Productions.IMMEDIATE_ID);

        validateOxygenProduction(
                oxygenProduction,
                OxygenConstants.Productions.IMMEDIATE_ID,
                OxygenConstants.Productions.IMMEDIATE_NAME,
                OxygenConstants.Productions.IMMEDIATE_PRICE_PER_UNIT,
                OxygenConstants.Productions.IMMEDIATE_PRODUCED_UNITS,
                OxygenConstants.Productions.IMMEDIATE_PRODUCED_TANKS,
                OxygenConstants.Productions.IMMEDIATE_PRODUCTION_TIME,
                OxygenConstants.Productions.IMMEDIATE_UNIT_TYPE_ID
        );
    }

    @Test
    public void getOxygenProductionById_shouldReturnCorrectOxygenProduction_whenProductionIsElectrolytes() {
        OxygenProduction oxygenProduction = subject.getOxygenProductionById(OxygenConstants.Productions.ELECTROLYTES_ID);

        validateOxygenProduction(
                oxygenProduction,
                OxygenConstants.Productions.ELECTROLYTES_ID,
                OxygenConstants.Productions.ELECTROLYTES_NAME,
                OxygenConstants.Productions.ELECTROLYTES_PRICE_PER_UNIT,
                OxygenConstants.Productions.ELECTROLYTES_PRODUCED_UNITS,
                OxygenConstants.Productions.ELECTROLYTES_PRODUCED_TANKS,
                OxygenConstants.Productions.ELECTROLYTES_PRODUCTION_TIME,
                OxygenConstants.Productions.ELECTROLYTES_UNIT_TYPE_ID
        );
    }

    @Test
    public void getOxygenProductionById_shouldReturnCorrectOxygenProduction_whenProductionIsSparkPlugs() {
        OxygenProduction oxygenProduction = subject.getOxygenProductionById(OxygenConstants.Productions.SPARK_PLUGS_ID);

        validateOxygenProduction(
                oxygenProduction,
                OxygenConstants.Productions.SPARK_PLUGS_ID,
                OxygenConstants.Productions.SPARK_PLUGS_NAME,
                OxygenConstants.Productions.SPARK_PLUGS_PRICE_PER_UNIT,
                OxygenConstants.Productions.SPARK_PLUGS_PRODUCED_UNITS,
                OxygenConstants.Productions.SPARK_PLUGS_PRODUCED_TANKS,
                OxygenConstants.Productions.SPARK_PLUGS_PRODUCTION_TIME,
                OxygenConstants.Productions.SPARK_PLUGS_UNIT_TYPE_ID
        );
    }

    @Test
    public void getOxygenUnitTypeById_shouldReturnNull_whenUnitTypeDoesNotExist() {
        OxygenUnitType oxygenUnitType = subject.getOxygenUnitTypeById(-1L);

        assertNull(oxygenUnitType);
    }

    @Test
    public void getOxygenUnitTypeById_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsOxygenTanks() {
        OxygenUnitType oxygenUnitType = subject.getOxygenUnitTypeById(OxygenConstants.UnitTypes.OXYGEN_TANKS_ID);

        validateOxygenUnitType(
                oxygenUnitType,
                OxygenConstants.UnitTypes.OXYGEN_TANKS_ID,
                OxygenConstants.UnitTypes.OXYGEN_TANKS_NAME
        );
    }

    @Test
    public void getOxygenUnitTypeById_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsWaterLiters() {
        OxygenUnitType oxygenUnitType = subject.getOxygenUnitTypeById(OxygenConstants.UnitTypes.WATER_LITERS_ID);

        validateOxygenUnitType(
                oxygenUnitType,
                OxygenConstants.UnitTypes.WATER_LITERS_ID,
                OxygenConstants.UnitTypes.WATER_LITERS_NAME
        );
    }

    @Test
    public void getOxygenUnitTypeById_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsSparkPlugs() {
        OxygenUnitType oxygenUnitType = subject.getOxygenUnitTypeById(OxygenConstants.UnitTypes.SPARK_PLUGS_ID);

        validateOxygenUnitType(
                oxygenUnitType,
                OxygenConstants.UnitTypes.SPARK_PLUGS_ID,
                OxygenConstants.UnitTypes.SPARK_PLUGS_NAME
        );
    }

    private void validateOxygenCategory(OxygenCategory oxygenCategory, Long id, String name, Long productionId) {
        assertNotNull(oxygenCategory);
        assertEquals(oxygenCategory.getId(), id);
        assertEquals(oxygenCategory.getName(), name);
        assertEquals(oxygenCategory.getProduction().getId(), productionId);
    }

    private void validateOxygenProduction(OxygenProduction oxygenProduction, Long id, String name, Double pricePerUnit, Integer producedUnits, Integer producedTanks, Duration productionTime, Long unitTypeId) {
        assertNotNull(oxygenProduction);
        assertEquals(oxygenProduction.getId(), id);
        assertEquals(oxygenProduction.getName(), name);
        assertEquals(oxygenProduction.getPricePerUnit(), pricePerUnit);
        assertEquals(oxygenProduction.getProducedUnits(), producedUnits);
        assertEquals(oxygenProduction.getProducedTanks(), producedTanks);
        assertEquals(oxygenProduction.getProductionTime(), productionTime);
        assertEquals(oxygenProduction.getUnitType().getId(), unitTypeId);
    }

    private void validateOxygenUnitType(OxygenUnitType oxygenUnitType, Long id, String name) {
        assertNotNull(oxygenUnitType);
        assertEquals(oxygenUnitType.getId(), id);
        assertEquals(oxygenUnitType.getName(), name);
    }
}
