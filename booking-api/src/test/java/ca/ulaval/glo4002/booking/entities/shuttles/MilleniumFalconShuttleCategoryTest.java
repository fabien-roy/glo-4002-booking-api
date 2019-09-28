package ca.ulaval.glo4002.booking.entities.shuttles;

import ca.ulaval.glo4002.booking.entities.qualities.SupergiantQuality;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.MillenniumFalconShuttleCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MilleniumFalconShuttleCategoryTest {

    @Test
    public void construction_shouldSetQualityToSupergiant() {
        MillenniumFalconShuttleCategory subject= new MillenniumFalconShuttleCategory();

        assertTrue(subject.getQuality() instanceof SupergiantQuality);
    }
}
