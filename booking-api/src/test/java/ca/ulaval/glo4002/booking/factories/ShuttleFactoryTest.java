package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.passes.PassCategories;
import ca.ulaval.glo4002.booking.shuttles.ShuttleCategories;
import ca.ulaval.glo4002.booking.shuttles.ShuttleFactory;
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
    void build_shouldBuildEtSpaceshipShuttle_whenCategoryIsEtSpaceship() {
        ShuttleCategories category = ShuttleCategories.ET_SPACESHIP;

        Shuttle shuttle = factory.build(category);

        assertEquals(ShuttleCategories.ET_SPACESHIP, shuttle.getCategory());
        assertEquals(ShuttleFactory.ET_SPACESHIP_MAX_CAPACITY, shuttle.getMaxCapacity());
        assertEquals(ShuttleFactory.ET_SPACESHIP_PRICE, shuttle.getPrice());
    }

    @Test
    void build_shouldBuildMillenniumFalconShuttle_whenCategoryIsMillenniumFalcon() {
        ShuttleCategories category = ShuttleCategories.MILLENNIUM_FALCON;

        Shuttle shuttle = factory.build(category);

        assertEquals(ShuttleCategories.MILLENNIUM_FALCON, shuttle.getCategory());
        assertEquals(ShuttleFactory.MILLENNIUM_FALCON_MAX_CAPACITY, shuttle.getMaxCapacity());
        assertEquals(ShuttleFactory.MILLENNIUM_FALCON_PRICE, shuttle.getPrice());
    }

    @Test
    void build_shouldBuildSpaceXShuttle_whenCategoryIsSpaceX() {
        ShuttleCategories category = ShuttleCategories.SPACE_X;

        Shuttle shuttle = factory.build(category);

        assertEquals(ShuttleCategories.SPACE_X, shuttle.getCategory());
        assertEquals(ShuttleFactory.SPACE_X_MAX_CAPACITY, shuttle.getMaxCapacity());
        assertEquals(ShuttleFactory.SPACE_X_PRICE, shuttle.getPrice());
    }

    @Test
    void buildCategory_shouldBuildEtSpaceship_whenCategoryIsSupernova() {
        PassCategories passCategory = PassCategories.SUPERNOVA;

        ShuttleCategories category = factory.buildCategory(passCategory);

        assertEquals(ShuttleCategories.ET_SPACESHIP, category);
    }

    @Test
    void buildCategory_shouldBuildMillenniumFalcon_whenCategoryIsSupergiant() {
        PassCategories passCategory = PassCategories.SUPERGIANT;

        ShuttleCategories category = factory.buildCategory(passCategory);

        assertEquals(ShuttleCategories.MILLENNIUM_FALCON, category);
    }

    @Test
    void buildCategory_shouldBuildSpaceX_whenCategoryIsNebula() {
        PassCategories passCategory = PassCategories.NEBULA;

        ShuttleCategories category = factory.buildCategory(passCategory);

        assertEquals(ShuttleCategories.SPACE_X, category);
    }
}