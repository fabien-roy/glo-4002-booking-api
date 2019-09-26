package ca.ulaval.glo4002.booking.builders.shuttles;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.entities.shuttles.types.ShuttleType;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleTypeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShuttleTypeBuilderTest {

    private ShuttleTypeBuilder subject;
    private static final Long AN_INVALID_ID = -1L;
    private static final String AN_INVALID_NAME = "An invalid name";

    @BeforeEach
    public void setUp() {
        subject = new ShuttleTypeBuilder();
    }

    @Test
    public void buildById_shouldThrowShuttleTypeNotFoundException_whenTypeDoesNotExist() {
        ShuttleTypeNotFoundException thrown = assertThrows(
                ShuttleTypeNotFoundException.class,
                () -> subject.buildById(AN_INVALID_ID)
        );

        assertEquals(ExceptionConstants.SHUTTLE_TYPE_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildById_shouldReturnCorrectShuttleType_whenTypeIsDeparture() {
        ShuttleType shuttleType = subject.buildById(ShuttleConstants.Types.DEPARTURE_ID);

        validateShuttleType(
                shuttleType,
                ShuttleConstants.Types.DEPARTURE_ID,
                ShuttleConstants.Types.DEPARTURE_NAME
        );
    }

    @Test
    public void buildById_shouldReturnCorrectShuttleType_whenTypeIsArrival() {
        ShuttleType shuttleType = subject.buildById(ShuttleConstants.Types.ARRIVAL_ID);

        validateShuttleType(
                shuttleType,
                ShuttleConstants.Types.ARRIVAL_ID,
                ShuttleConstants.Types.ARRIVAL_NAME
        );
    }

    @Test
    public void buildByName_shouldThrowShuttleTypeNotFoundException_whenTypeDoesNotExist() {
        ShuttleTypeNotFoundException thrown = assertThrows(
                ShuttleTypeNotFoundException.class,
                () -> subject.buildByName(AN_INVALID_NAME)
        );

        assertEquals(ExceptionConstants.SHUTTLE_TYPE_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildByName_shouldReturnCorrectShuttleType_whenTypeIsDeparture() {
        ShuttleType shuttleType = subject.buildByName(ShuttleConstants.Types.DEPARTURE_NAME);

        validateShuttleType(
                shuttleType,
                ShuttleConstants.Types.DEPARTURE_ID,
                ShuttleConstants.Types.DEPARTURE_NAME
        );
    }

    @Test
    public void buildByName_shouldReturnCorrectShuttleType_whenTypeIsArrival() {
        ShuttleType shuttleType = subject.buildByName(ShuttleConstants.Types.ARRIVAL_NAME);

        validateShuttleType(
                shuttleType,
                ShuttleConstants.Types.ARRIVAL_ID,
                ShuttleConstants.Types.ARRIVAL_NAME
        );
    }

    private void validateShuttleType(ShuttleType shuttleType, Long id, String name) {
        assertNotNull(shuttleType);
        assertEquals(shuttleType.getId(), id);
        assertEquals(shuttleType.getName(), name);
    }
}
