package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.shuttles.Shuttle;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShuttleFactoryTest {

    private ShuttleFactory subject;

    @BeforeEach
    void setUpSubject() {
        subject = new ShuttleFactory();
    }

    @Test
    void build_shouldBuildEtSpaceshipShuttle_whenCategoryIsSupernova() {
        PassCategories category = PassCategories.SUPERNOVA;

        Shuttle shuttle = subject.build(category);

        assertEquals(ShuttleCategories.ETSPACESHIP, shuttle.getCategory());
        assertEquals(ShuttleFactory.ET_SPACESHIP_MAX_CAPACITY, shuttle.getMaxCapacity());
        assertEquals(ShuttleFactory.ET_SPACESHIP_PRICE, shuttle.getPrice());
    }

    @Test
    void build_shouldBuildMillenniumFalconShuttle_whenCategoryIsSupergiant() {
        PassCategories category = PassCategories.SUPERGIANT;

        Shuttle shuttle = subject.build(category);

        assertEquals(ShuttleCategories.MILLENIUMFALCON, shuttle.getCategory());
        assertEquals(ShuttleFactory.MILLENNIUM_FALCON_MAX_CAPACITY, shuttle.getMaxCapacity());
        assertEquals(ShuttleFactory.MILLENNIUM_FALCON_PRICE, shuttle.getPrice());
    }

    @Test
    void build_shouldBuildSpaceXShuttle_whenCategoryIsNebula() {
        PassCategories category = PassCategories.NEBULA;

        Shuttle shuttle = subject.build(category);

        assertEquals(ShuttleCategories.SPACEX, shuttle.getCategory());
        assertEquals(ShuttleFactory.SPACE_X_MAX_CAPACITY, shuttle.getMaxCapacity());
        assertEquals(ShuttleFactory.SPACE_X_PRICE, shuttle.getPrice());
    }
}