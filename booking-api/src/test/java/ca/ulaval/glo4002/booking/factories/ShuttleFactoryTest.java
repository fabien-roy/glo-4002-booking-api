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

        validateShuttleCategory(
                shuttleCategory,
                ShuttleConstants.Categories.ET_SPACESHIP_ID,
                ShuttleConstants.Categories.ET_SPACESHIP_NAME,
                ShuttleConstants.Categories.ET_SPACESHIP_MAX_CAPACITY,
                ShuttleConstants.Categories.ET_SPACESHIP_PRICE
        );
    }

    @Test
    public void getShuttleCategoryById_shouldReturnCorrectShuttleCategory_whenCategoryIsMillenniumFalcon() {
        ShuttleCategory shuttleCategory = subject.getShuttleCategoryById(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID);

        validateShuttleCategory(
                shuttleCategory,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_ID,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_NAME,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_MAX_CAPACITY,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_PRICE
        );
    }

    @Test
    public void getShuttleCategoryById_shouldReturnCorrectShuttleCategory_whenCategoryIsSpaceX() {
        ShuttleCategory shuttleCategory = subject.getShuttleCategoryById(ShuttleConstants.Categories.SPACE_X_ID);

        validateShuttleCategory(
                shuttleCategory,
                ShuttleConstants.Categories.SPACE_X_ID,
                ShuttleConstants.Categories.SPACE_X_NAME,
                ShuttleConstants.Categories.SPACE_X_MAX_CAPACITY,
                ShuttleConstants.Categories.SPACE_X_PRICE
        );
    }

    @Test
    public void getShuttleTypeById_shouldReturnNull_whenTypeDoesNotExist() {
        ShuttleType shuttleType = subject.getShuttleTypeById(-1L);

        assertNull(shuttleType);
    }

    @Test
    public void getShuttleTypeById_shouldReturnCorrectShuttleType_whenTypeIsDeparture() {
        ShuttleType shuttleType = subject.getShuttleTypeById(ShuttleConstants.Types.DEPARTURE_ID);

        validateShuttleType(
                shuttleType,
                ShuttleConstants.Types.DEPARTURE_ID,
                ShuttleConstants.Types.DEPARTURE_NAME
        );
    }

    @Test
    public void getShuttleTypeById_shouldReturnCorrectShuttleType_whenTypeIsArrival() {
        ShuttleType shuttleType = subject.getShuttleTypeById(ShuttleConstants.Types.ARRIVAL_ID);

        validateShuttleType(
                shuttleType,
                ShuttleConstants.Types.ARRIVAL_ID,
                ShuttleConstants.Types.ARRIVAL_NAME
        );
    }

    private void validateShuttleCategory(ShuttleCategory shuttleCategory, Long id, String name, Integer maxCapacity, Double price) {
        assertNotNull(shuttleCategory);
        assertEquals(shuttleCategory.getId(), id);
        assertEquals(shuttleCategory.getName(), name);
        assertEquals(shuttleCategory.getMaxCapacity(), maxCapacity);
        assertEquals(shuttleCategory.getPrice(), price);
    }

    private void validateShuttleType(ShuttleType shuttleType, Long id, String name) {
        assertNotNull(shuttleType);
        assertEquals(shuttleType.getId(), id);
        assertEquals(shuttleType.getName(), name);
    }
}
