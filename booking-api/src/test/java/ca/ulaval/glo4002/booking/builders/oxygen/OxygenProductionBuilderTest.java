package ca.ulaval.glo4002.booking.builders.oxygen;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenProductionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class OxygenProductionBuilderTest {

    private OxygenProductionBuilder subject;
    private static final Long AN_INVALID_ID = -1L;
    private static final String AN_INVALID_NAME = "An invalid name";

    @BeforeEach
    public void setUp(){
        subject = new OxygenProductionBuilder();
    }

    @Test
    public void buildById_shouldThrowOxygenProductionNotFoundException_whenProductionDoesNotExist() {
        OxygenProductionNotFoundException thrown = assertThrows(
                OxygenProductionNotFoundException.class,
                () -> subject.buildById(AN_INVALID_ID)
        );

        assertEquals(ExceptionConstants.OXYGEN_PRODUCTION_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildById_shouldReturnCorrectOxygenProduction_whenProductionIsImmediate() {
        OxygenProduction oxygenProduction = subject.buildById(OxygenConstants.Productions.IMMEDIATE_ID);

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
    public void buildById_shouldReturnCorrectOxygenProduction_whenProductionIsElectrolytes() {
        OxygenProduction oxygenProduction = subject.buildById(OxygenConstants.Productions.ELECTROLYTES_ID);

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
    public void buildById_shouldReturnCorrectOxygenProduction_whenProductionIsSparkPlugs() {
        OxygenProduction oxygenProduction = subject.buildById(OxygenConstants.Productions.SPARK_PLUGS_ID);

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
    public void buildByName_shouldThrowOxygenProductionNotFoundException_whenProductionDoesNotExist() {
        OxygenProductionNotFoundException thrown = assertThrows(
                OxygenProductionNotFoundException.class,
                () -> subject.buildByName(AN_INVALID_NAME)
        );

        assertEquals(ExceptionConstants.OXYGEN_PRODUCTION_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildByName_shouldReturnCorrectOxygenProduction_whenProductionIsImmediate() {
        OxygenProduction oxygenProduction = subject.buildByName(OxygenConstants.Productions.IMMEDIATE_NAME);

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
    public void buildByName_shouldReturnCorrectOxygenProduction_whenProductionIsElectrolytes() {
        OxygenProduction oxygenProduction = subject.buildByName(OxygenConstants.Productions.ELECTROLYTES_NAME);

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
    public void buildByName_shouldReturnCorrectOxygenProduction_whenProductionIsSparkPlugs() {
        OxygenProduction oxygenProduction = subject.buildByName(OxygenConstants.Productions.SPARK_PLUGS_NAME);

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
}
