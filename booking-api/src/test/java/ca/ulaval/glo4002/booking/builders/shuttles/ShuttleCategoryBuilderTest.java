package ca.ulaval.glo4002.booking.builders.shuttles;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.ShuttleConstants;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.ShuttleCategory;
import ca.ulaval.glo4002.booking.exceptions.shuttles.ShuttleCategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShuttleCategoryBuilderTest {

    private ShuttleCategoryBuilder subject;
    private static final Long AN_INVALID_ID = -1L;
    private static final String AN_INVALID_NAME = "An invalid name";

    @BeforeEach
    public void setUp() {
        subject = new ShuttleCategoryBuilder();
    }

    @Test
    public void buildById_shouldThrowShuttleCategoryNotFoundException_whenCategoryDoesNotExist() {
        ShuttleCategoryNotFoundException thrown = assertThrows(
                ShuttleCategoryNotFoundException.class,
                () -> subject.buildById(AN_INVALID_ID)
        );

        assertEquals(ExceptionConstants.SHUTTLE_CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildById_shouldReturnCorrectShuttleCategory_whenCategoryIsEtSpaceship() {
        ShuttleCategory shuttleCategory = subject.buildById(ShuttleConstants.Categories.ET_SPACESHIP_ID);

        validateShuttleCategory(
                shuttleCategory,
                ShuttleConstants.Categories.ET_SPACESHIP_ID,
                ShuttleConstants.Categories.ET_SPACESHIP_NAME,
                ShuttleConstants.Categories.ET_SPACESHIP_MAX_CAPACITY,
                ShuttleConstants.Categories.ET_SPACESHIP_PRICE
        );
    }

    @Test
    public void buildById_shouldReturnCorrectShuttleCategory_whenCategoryIsMillenniumFalcon() {
        ShuttleCategory shuttleCategory = subject.buildById(ShuttleConstants.Categories.MILLENNIUM_FALCON_ID);

        validateShuttleCategory(
                shuttleCategory,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_ID,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_NAME,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_MAX_CAPACITY,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_PRICE
        );
    }

    @Test
    public void buildById_shouldReturnCorrectShuttleCategory_whenCategoryIsSpaceX() {
        ShuttleCategory shuttleCategory = subject.buildById(ShuttleConstants.Categories.SPACE_X_ID);

        validateShuttleCategory(
                shuttleCategory,
                ShuttleConstants.Categories.SPACE_X_ID,
                ShuttleConstants.Categories.SPACE_X_NAME,
                ShuttleConstants.Categories.SPACE_X_MAX_CAPACITY,
                ShuttleConstants.Categories.SPACE_X_PRICE
        );
    }

    @Test
    public void buildByName_shouldThrowShuttleCategoryNotFoundException_whenCategoryDoesNotExist() {
        ShuttleCategoryNotFoundException thrown = assertThrows(
                ShuttleCategoryNotFoundException.class,
                () -> subject.buildByName(AN_INVALID_NAME)
        );

        assertEquals(ExceptionConstants.SHUTTLE_CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    public void buildByName_shouldReturnCorrectShuttleCategory_whenCategoryIsEtSpaceship() {
        ShuttleCategory shuttleCategory = subject.buildByName(ShuttleConstants.Categories.ET_SPACESHIP_NAME);

        validateShuttleCategory(
                shuttleCategory,
                ShuttleConstants.Categories.ET_SPACESHIP_ID,
                ShuttleConstants.Categories.ET_SPACESHIP_NAME,
                ShuttleConstants.Categories.ET_SPACESHIP_MAX_CAPACITY,
                ShuttleConstants.Categories.ET_SPACESHIP_PRICE
        );
    }

    @Test
    public void buildByName_shouldReturnCorrectShuttleCategory_whenCategoryIsMillenniumFalcon() {
        ShuttleCategory shuttleCategory = subject.buildByName(ShuttleConstants.Categories.MILLENNIUM_FALCON_NAME);

        validateShuttleCategory(
                shuttleCategory,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_ID,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_NAME,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_MAX_CAPACITY,
                ShuttleConstants.Categories.MILLENNIUM_FALCON_PRICE
        );
    }

    @Test
    public void buildByName_shouldReturnCorrectShuttleCategory_whenCategoryIsSpaceX() {
        ShuttleCategory shuttleCategory = subject.buildByName(ShuttleConstants.Categories.SPACE_X_NAME);

        validateShuttleCategory(
                shuttleCategory,
                ShuttleConstants.Categories.SPACE_X_ID,
                ShuttleConstants.Categories.SPACE_X_NAME,
                ShuttleConstants.Categories.SPACE_X_MAX_CAPACITY,
                ShuttleConstants.Categories.SPACE_X_PRICE
        );
    }

    private void validateShuttleCategory(ShuttleCategory shuttleCategory, Long id, String name, Integer maxCapacity, Double price) {
        assertNotNull(shuttleCategory);
        assertEquals(shuttleCategory.getId(), id);
        assertEquals(shuttleCategory.getName(), name);
        assertEquals(shuttleCategory.getMaxCapacity(), maxCapacity);
        assertEquals(shuttleCategory.getPrice(), price);
    }
}
