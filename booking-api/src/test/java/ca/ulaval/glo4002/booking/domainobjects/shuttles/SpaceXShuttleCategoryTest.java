package ca.ulaval.glo4002.booking.domainobjects.shuttles;

import ca.ulaval.glo4002.booking.domainobjects.qualities.NebulaQuality;
import ca.ulaval.glo4002.booking.domainobjects.shuttles.categories.SpaceXShuttleCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpaceXShuttleCategoryTest {

    @Test
    public void construction_shouldSetQualityToNebula() {
        SpaceXShuttleCategory subject = new SpaceXShuttleCategory();

        assertTrue(subject.getQuality() instanceof NebulaQuality);
    }
}
