package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.oxygen.OxygenUnitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        assertNotNull(oxygenCategory);
        assertEquals(oxygenCategory.getId(), OxygenConstants.Categories.E_ID);
        assertEquals(oxygenCategory.getName(), OxygenConstants.Categories.E_NAME);
        assertEquals(oxygenCategory.getProduction().getId(), OxygenConstants.Categories.E_PRODUCTION_ID);
    }

    @Test
    public void getOxygenCategoryById_shouldReturnCorrectOxygenCategory_whenCategoryIsB() {
        OxygenCategory oxygenCategory = subject.getOxygenCategoryById(OxygenConstants.Categories.B_ID);

        assertNotNull(oxygenCategory);
        assertEquals(oxygenCategory.getId(), OxygenConstants.Categories.B_ID);
        assertEquals(oxygenCategory.getName(), OxygenConstants.Categories.B_NAME);
        assertEquals(oxygenCategory.getProduction().getId(), OxygenConstants.Categories.B_PRODUCTION_ID);
    }

    @Test
    public void getOxygenCategoryById_shouldReturnCorrectOxygenCategory_whenCategoryIsA() {
        OxygenCategory oxygenCategory = subject.getOxygenCategoryById(OxygenConstants.Categories.A_ID);

        assertNotNull(oxygenCategory);
        assertEquals(oxygenCategory.getId(), OxygenConstants.Categories.A_ID);
        assertEquals(oxygenCategory.getName(), OxygenConstants.Categories.A_NAME);
        assertEquals(oxygenCategory.getProduction().getId(), OxygenConstants.Categories.A_PRODUCTION_ID);
    }

    @Test
    public void getOxygenProductionById_shouldReturnNull_whenProductionDoesNotExist() {
        OxygenProduction oxygenProduction = subject.getOxygenProductionById(-1L);

        assertNull(oxygenProduction);
    }

    @Test
    public void getOxygenProductionById_shouldReturnCorrectOxygenProduction_whenProductionIsImmediate() {
        OxygenProduction oxygenProduction = subject.getOxygenProductionById(OxygenConstants.Productions.IMMEDIATE_ID);

        assertNotNull(oxygenProduction);
        assertEquals(oxygenProduction.getId(), OxygenConstants.Productions.IMMEDIATE_ID);
        assertEquals(oxygenProduction.getName(), OxygenConstants.Productions.IMMEDIATE_NAME);
        assertEquals(oxygenProduction.getPricePerUnit(), OxygenConstants.Productions.IMMEDIATE_PRICE_PER_UNIT);
        assertEquals(oxygenProduction.getProducedUnits(), OxygenConstants.Productions.IMMEDIATE_PRODUCED_UNITS);
        assertEquals(oxygenProduction.getProducedTanks(), OxygenConstants.Productions.IMMEDIATE_PRODUCED_TANKS);
        assertEquals(oxygenProduction.getProductionTime(), OxygenConstants.Productions.IMMEDIATE_PRODUCTION_TIME);
        assertEquals(oxygenProduction.getUnitType().getId(), OxygenConstants.Productions.IMMEDIATE_UNIT_TYPE_ID);
    }

    @Test
    public void getOxygenProductionById_shouldReturnCorrectOxygenProduction_whenProductionIsElectrolytes() {
        OxygenProduction oxygenProduction = subject.getOxygenProductionById(OxygenConstants.Productions.ELECTROLYTES_ID);

        assertNotNull(oxygenProduction);
        assertEquals(oxygenProduction.getId(), OxygenConstants.Productions.ELECTROLYTES_ID);
        assertEquals(oxygenProduction.getName(), OxygenConstants.Productions.ELECTROLYTES_NAME);
        assertEquals(oxygenProduction.getPricePerUnit(), OxygenConstants.Productions.ELECTROLYTES_PRICE_PER_UNIT);
        assertEquals(oxygenProduction.getProducedUnits(), OxygenConstants.Productions.ELECTROLYTES_PRODUCED_UNITS);
        assertEquals(oxygenProduction.getProducedTanks(), OxygenConstants.Productions.ELECTROLYTES_PRODUCED_TANKS);
        assertEquals(oxygenProduction.getProductionTime(), OxygenConstants.Productions.ELECTROLYTES_PRODUCTION_TIME);
        assertEquals(oxygenProduction.getUnitType().getId(), OxygenConstants.Productions.ELECTROLYTES_UNIT_TYPE_ID);
    }

    @Test
    public void getOxygenProductionById_shouldReturnCorrectOxygenProduction_whenProductionIsSparkPlugs() {
        OxygenProduction oxygenProduction = subject.getOxygenProductionById(OxygenConstants.Productions.SPARK_PLUGS_ID);

        assertNotNull(oxygenProduction);
        assertEquals(oxygenProduction.getId(), OxygenConstants.Productions.SPARK_PLUGS_ID);
        assertEquals(oxygenProduction.getName(), OxygenConstants.Productions.SPARK_PLUGS_NAME);
        assertEquals(oxygenProduction.getPricePerUnit(), OxygenConstants.Productions.SPARK_PLUGS_PRICE_PER_UNIT);
        assertEquals(oxygenProduction.getProducedUnits(), OxygenConstants.Productions.SPARK_PLUGS_PRODUCED_UNITS);
        assertEquals(oxygenProduction.getProducedTanks(), OxygenConstants.Productions.SPARK_PLUGS_PRODUCED_TANKS);
        assertEquals(oxygenProduction.getProductionTime(), OxygenConstants.Productions.SPARK_PLUGS_PRODUCTION_TIME);
        assertEquals(oxygenProduction.getUnitType().getId(), OxygenConstants.Productions.SPARK_PLUGS_UNIT_TYPE_ID);
    }

    @Test
    public void getOxygenUnitTypeById_shouldReturnNull_whenUnitTypeDoesNotExist() {
        OxygenUnitType oxygenUnitType = subject.getOxygenUnitTypeById(-1L);

        assertNull(oxygenUnitType);
    }

    @Test
    public void getOxygenUnitTypeById_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsOxygenTanks() {
        OxygenUnitType oxygenUnitType = subject.getOxygenUnitTypeById(OxygenConstants.UnitTypes.OXYGEN_TANKS_ID);

        assertNotNull(oxygenUnitType);
        assertEquals(oxygenUnitType.getId(), OxygenConstants.UnitTypes.OXYGEN_TANKS_ID);
        assertEquals(oxygenUnitType.getName(), OxygenConstants.UnitTypes.OXYGEN_TANKS_NAME);
    }

    @Test
    public void getOxygenUnitTypeById_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsWaterLiters() {
        OxygenUnitType oxygenUnitType = subject.getOxygenUnitTypeById(OxygenConstants.UnitTypes.WATER_LITERS_ID);

        assertNotNull(oxygenUnitType);
        assertEquals(oxygenUnitType.getId(), OxygenConstants.UnitTypes.WATER_LITERS_ID);
        assertEquals(oxygenUnitType.getName(), OxygenConstants.UnitTypes.WATER_LITERS_NAME);
    }

    @Test
    public void getOxygenUnitTypeById_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsSparkPlugs() {
        OxygenUnitType oxygenUnitType = subject.getOxygenUnitTypeById(OxygenConstants.UnitTypes.SPARK_PLUGS_ID);

        assertNotNull(oxygenUnitType);
        assertEquals(oxygenUnitType.getId(), OxygenConstants.UnitTypes.SPARK_PLUGS_ID);
        assertEquals(oxygenUnitType.getName(), OxygenConstants.UnitTypes.SPARK_PLUGS_NAME);
    }
}
