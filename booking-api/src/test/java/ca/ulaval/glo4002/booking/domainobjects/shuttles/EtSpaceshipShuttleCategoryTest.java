package ca.ulaval.glo4002.booking.domainobjects.shuttles;

import ca.ulaval.glo4002.booking.domainobjects.qualities.SupernovaQuality;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.EtSpaceshipShuttleCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EtSpaceshipShuttleCategoryTest {

    @Test
    public void construction_shouldSetQualityToSupernova() {
        EtSpaceshipShuttleCategory subject = new EtSpaceshipShuttleCategory();

        assertTrue(subject.getQuality() instanceof SupernovaQuality);
    }
}
