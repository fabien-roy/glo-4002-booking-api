package ca.ulaval.glo4002.booking.shuttles.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}