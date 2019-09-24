package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.Order;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.oxygen.unitTypes.OxygenUnitType;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenProductionNotFoundException;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenUnitTypeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OxygenFactoryTest {
    private OxygenFactory subject;

    @BeforeEach
    public void setUp(){
        subject = new OxygenFactory();
    }

    @Test
    public void getOxygenCategoryById_shouldThrowOxygenCategoryNotFoundException_whenCategoryDoesNotExist() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.getOxygenCategoryById(-1L)
        );

        assertEquals(thrown.getMessage(), ExceptionConstants.OXYGEN_CATEGORY_NOT_FOUND_MESSAGE);
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
    public void getOxygenProductionById_shouldThrowOxygenProductionNotFoundException_whenProductionDoesNotExist() {
        OxygenProductionNotFoundException thrown = assertThrows(
                OxygenProductionNotFoundException.class,
                () -> subject.getOxygenProductionById(-1L)
        );

        assertEquals(thrown.getMessage(), ExceptionConstants.OXYGEN_PRODUCTION_NOT_FOUND_MESSAGE);
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
    public void getOxygenUnitTypeById_shouldThrowOxygenUnitTypeNotFoundException_whenUnitTypeDoesNotExist() {
        OxygenUnitTypeNotFoundException thrown = assertThrows(
                OxygenUnitTypeNotFoundException.class,
                () -> subject.getOxygenUnitTypeById(-1L)
        );

        assertEquals(thrown.getMessage(), ExceptionConstants.OXYGEN_UNIT_TYPE_NOT_FOUND_MESSAGE);
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

    //TODO: Split the "Factory" test above in another file when new name will be found

    @Test
    public void createOxygenTankCategoryA_WhenOrderIsForNebula(){
        Order mockedOrder = mock(Order.class);
    }
}
