package ca.ulaval.glo4002.booking.builders.oxygen;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.unittypes.OxygenUnitType;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenUnitTypeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OxygenUnitTypeBuilderTest {

    private OxygenUnitTypeBuilder subject;
    private static final Long AN_INVALID_ID = -1L;
    private static final String AN_INVALID_NAME = "An invalid code";

    @BeforeEach
    public void setUp(){
        subject = new OxygenUnitTypeBuilder();
    }

    @Test
    public void buildById_shouldThrowOxygenUnitTypeNotFoundException_whenUnitTypeDoesNotExist() {
        OxygenUnitTypeNotFoundException thrown = assertThrows(
                OxygenUnitTypeNotFoundException.class,
                () -> subject.buildById(AN_INVALID_ID)
        );

        assertEquals(ExceptionConstants.Oxygen.UNIT_TYPE_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildById_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsOxygenTanks() {
        OxygenUnitType oxygenUnitType = subject.buildById(OxygenConstants.UnitTypes.OXYGEN_TANKS_ID);

        validateOxygenUnitType(
                oxygenUnitType,
                OxygenConstants.UnitTypes.OXYGEN_TANKS_ID,
                OxygenConstants.UnitTypes.OXYGEN_TANKS_NAME
        );
    }

    @Test
    public void buildById_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsWaterLiters() {
        OxygenUnitType oxygenUnitType = subject.buildById(OxygenConstants.UnitTypes.WATER_LITERS_ID);

        validateOxygenUnitType(
                oxygenUnitType,
                OxygenConstants.UnitTypes.WATER_LITERS_ID,
                OxygenConstants.UnitTypes.WATER_LITERS_NAME
        );
    }

    @Test
    public void buildById_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsSparkPlugs() {
        OxygenUnitType oxygenUnitType = subject.buildById(OxygenConstants.UnitTypes.SPARK_PLUGS_ID);

        validateOxygenUnitType(
                oxygenUnitType,
                OxygenConstants.UnitTypes.SPARK_PLUGS_ID,
                OxygenConstants.UnitTypes.SPARK_PLUGS_NAME
        );
    }

    @Test
    public void buildByName_shouldThrowOxygenUnitTypeNotFoundException_whenUnitTypeDoesNotExist() {
        OxygenUnitTypeNotFoundException thrown = assertThrows(
                OxygenUnitTypeNotFoundException.class,
                () -> subject.buildByName(AN_INVALID_NAME)
        );

        assertEquals(ExceptionConstants.Oxygen.UNIT_TYPE_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildByName_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsOxygenTanks() {
        OxygenUnitType oxygenUnitType = subject.buildByName(OxygenConstants.UnitTypes.OXYGEN_TANKS_NAME);

        validateOxygenUnitType(
                oxygenUnitType,
                OxygenConstants.UnitTypes.OXYGEN_TANKS_ID,
                OxygenConstants.UnitTypes.OXYGEN_TANKS_NAME
        );
    }

    @Test
    public void buildByName_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsWaterLiters() {
        OxygenUnitType oxygenUnitType = subject.buildByName(OxygenConstants.UnitTypes.WATER_LITERS_NAME);

        validateOxygenUnitType(
                oxygenUnitType,
                OxygenConstants.UnitTypes.WATER_LITERS_ID,
                OxygenConstants.UnitTypes.WATER_LITERS_NAME
        );
    }

    @Test
    public void buildByName_shouldReturnCorrectOxygenUnitType_whenUnitTypeIsSparkPlugs() {
        OxygenUnitType oxygenUnitType = subject.buildByName(OxygenConstants.UnitTypes.SPARK_PLUGS_NAME);

        validateOxygenUnitType(
                oxygenUnitType,
                OxygenConstants.UnitTypes.SPARK_PLUGS_ID,
                OxygenConstants.UnitTypes.SPARK_PLUGS_NAME
        );
    }

    private void validateOxygenUnitType(OxygenUnitType oxygenUnitType, Long id, String name) {
        assertNotNull(oxygenUnitType);
        assertEquals(oxygenUnitType.getId(), id);
        assertEquals(oxygenUnitType.getName(), name);
    }
}
