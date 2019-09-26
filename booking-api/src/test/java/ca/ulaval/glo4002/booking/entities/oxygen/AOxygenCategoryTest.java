package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.entities.oxygen.categories.AOxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.qualities.NebulaQuality;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class AOxygenCategoryTest {

    @Test
    public void construction_shouldSetQualityToNebula() {
        OxygenProduction production = mock(OxygenProduction.class);

        AOxygenCategory subject = new AOxygenCategory(production);

        assertTrue(subject.getQuality() instanceof NebulaQuality);
    }
}
