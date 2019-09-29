package ca.ulaval.glo4002.booking.domainObjects.shuttles;

import ca.ulaval.glo4002.booking.domainObjects.qualities.SupernovaQuality;
import ca.ulaval.glo4002.booking.domainObjects.shuttles.categories.EtSpaceshipShuttleCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EtSpaceshipShuttleCategoryTest {

    @Test
    public void construction_shouldSetQualityToSupernova() {
        EtSpaceshipShuttleCategory subject = new EtSpaceshipShuttleCategory();

        assertTrue(subject.getQuality() instanceof SupernovaQuality);
    }
}
