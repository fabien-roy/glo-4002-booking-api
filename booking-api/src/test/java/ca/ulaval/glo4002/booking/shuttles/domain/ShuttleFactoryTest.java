package ca.ulaval.glo4002.booking.shuttles.domain;

import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShuttleFactoryTest {

    private ShuttleFactory factory;

    @BeforeEach
    void setUpFactory() {
        factory = new ShuttleFactory();
    }

    @Test
    void create_shouldCreateEtSpaceshipShuttle_whenCategoryIsEtSpaceship() {
        ShuttleCategories category = ShuttleCategories.ET_SPACESHIP;

        Shuttle shuttle = factory.create(category);

        assertEquals(ShuttleCategories.ET_SPACESHIP, shuttle.getCategory());
        assertEquals(ShuttleFactory.ET_SPACESHIP_MAX_CAPACITY, shuttle.getMaxCapacity());
        assertEquals(ShuttleFactory.ET_SPACESHIP_PRICE, shuttle.getPrice());
    }

    @Test
    void create_shouldCreateMillenniumFalconShuttle_whenCategoryIsMillenniumFalcon() {
        ShuttleCategories category = ShuttleCategories.MILLENNIUM_FALCON;

        Shuttle shuttle = factory.create(category);

        assertEquals(ShuttleCategories.MILLENNIUM_FALCON, shuttle.getCategory());
        assertEquals(ShuttleFactory.MILLENNIUM_FALCON_MAX_CAPACITY, shuttle.getMaxCapacity());
        assertEquals(ShuttleFactory.MILLENNIUM_FALCON_PRICE, shuttle.getPrice());
    }

    @Test
    void create_shouldCreateSpaceXShuttle_whenCategoryIsSpaceX() {
        ShuttleCategories category = ShuttleCategories.SPACE_X;

        Shuttle shuttle = factory.create(category);

        assertEquals(ShuttleCategories.SPACE_X, shuttle.getCategory());
        assertEquals(ShuttleFactory.SPACE_X_MAX_CAPACITY, shuttle.getMaxCapacity());
        assertEquals(ShuttleFactory.SPACE_X_PRICE, shuttle.getPrice());
    }

    @Test
    void createCategory_shouldCreateEtSpaceship_whenCategoryIsSupernova() {
        PassCategories passCategory = PassCategories.SUPERNOVA;

        ShuttleCategories category = factory.createCategory(passCategory);

        assertEquals(ShuttleCategories.ET_SPACESHIP, category);
    }

    @Test
    void createCategory_shouldCreateMillenniumFalcon_whenCategoryIsSupergiant() {
        PassCategories passCategory = PassCategories.SUPERGIANT;

        ShuttleCategories category = factory.createCategory(passCategory);

        assertEquals(ShuttleCategories.MILLENNIUM_FALCON, category);
    }

    @Test
    void createCategory_shouldCreateSpaceX_whenCategoryIsNebula() {
        PassCategories passCategory = PassCategories.NEBULA;

        ShuttleCategories category = factory.createCategory(passCategory);

        assertEquals(ShuttleCategories.SPACE_X, category);
    }
}