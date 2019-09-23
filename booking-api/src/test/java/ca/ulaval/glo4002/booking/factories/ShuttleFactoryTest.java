package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.entities.shuttles.ShuttleCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.ShuttleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShuttleFactoryTest {
    private ShuttleFactory subject;

    @BeforeEach
    public void setUp() {
        subject = new ShuttleFactory();
    }

    @Test
    public void getShuttleCategoryById_shouldReturnNull_whenCategoryDoesNotExist() {
        ShuttleCategory shuttleCategory = subject.getShuttleCategoryById(-1L);

        assertNull(shuttleCategory);
    }

    @Test
    public void getShuttleCategoryById_shouldReturnCorrectShuttleCategory_whenCategoryIsEtSpaceship() {
        ShuttleCategory shuttleCategory = subject.getShuttleCategoryById(ShuttleConstants.Categories.ET_SPACESHIP_ID);

        assertNotNull(shuttleCategory);
        assertEquals(shuttleCategory.getId(), ShuttleConstants.Categories.ET_SPACESHIP_ID);
        assertEquals(shuttleCategory.getName(), ShuttleConstants.Categories.ET_SPACESHIP_NAME);
        assertEquals(shuttleCategory.getMaxCapacity(), ShuttleConstants.Categories.ET_SPACESHIP_MAX_CAPACITY);
        assertEquals(shuttleCategory.getPrice(), ShuttleConstants.Categories.ET_SPACESHIP_PRICE);
    }

    @Test
    public void getShuttleCategoryById_shouldReturnCorrectShuttleCategory_whenCategoryIsMillenniumFalcon() {
        ShuttleCategory shuttleCategory = subject.getShuttleCategoryById(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID);

        assertNotNull(shuttleCategory);
        assertEquals(shuttleCategory.getId(), ShuttleConstants.Categories.MILLENNIUM_FALCON_ID);
        assertEquals(shuttleCategory.getName(), ShuttleConstants.Categories.MILLENNIUM_FALCON_NAME);
        assertEquals(shuttleCategory.getMaxCapacity(), ShuttleConstants.Categories.MILLENNIUM_FALCON_MAX_CAPACITY);
        assertEquals(shuttleCategory.getPrice(), ShuttleConstants.Categories.MILLENNIUM_FALCON_PRICE);
    }

    @Test
    public void getShuttleCategoryById_shouldReturnCorrectShuttleCategory_whenCategoryIsSpaceX() {
        ShuttleCategory shuttleCategory = subject.getShuttleCategoryById(ShuttleConstants.Categories.SPACE_X_ID);

        assertNotNull(shuttleCategory);
        assertEquals(shuttleCategory.getId(), ShuttleConstants.Categories.SPACE_X_ID);
        assertEquals(shuttleCategory.getName(), ShuttleConstants.Categories.SPACE_X_NAME);
        assertEquals(shuttleCategory.getMaxCapacity(), ShuttleConstants.Categories.SPACE_X_MAX_CAPACITY);
        assertEquals(shuttleCategory.getPrice(), ShuttleConstants.Categories.SPACE_X_PRICE);
    }

    @Test
    public void getShuttleTypeById_shouldReturnNull_whenTypeDoesNotExist() {
        ShuttleType shuttleType = subject.getShuttleTypeById(-1L);

        assertNull(shuttleType);
    }

    @Test
    public void getShuttleTypeById_shouldReturnCorrectShuttleType_whenTypeIsDeparture() {
        ShuttleType shuttleType = subject.getShuttleTypeById(ShuttleConstants.Types.DEPARTURE_ID);

        assertNotNull(shuttleType);
        assertEquals(shuttleType.getId(), ShuttleConstants.Types.DEPARTURE_ID);
        assertEquals(shuttleType.getName(), ShuttleConstants.Types.DEPARTURE_NAME);
    }

    @Test
    public void getShuttleTypeById_shouldReturnCorrectShuttleType_whenTypeIsArrival() {
        ShuttleType shuttleType = subject.getShuttleTypeById(ShuttleConstants.Types.ARRIVAL_ID);

        assertNotNull(shuttleType);
        assertEquals(shuttleType.getId(), ShuttleConstants.Types.ARRIVAL_ID);
        assertEquals(shuttleType.getName(), ShuttleConstants.Types.ARRIVAL_NAME);
    }
}
