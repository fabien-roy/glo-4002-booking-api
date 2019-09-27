package ca.ulaval.glo4002.booking.entitites.oxygen;

import ca.ulaval.glo4002.booking.entities.oxygen.categories.EOxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.qualities.SupernovaQuality;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class EOxygenCategoryTest {

    @Test
    public void construction_shouldSetQualityToSupernova() {
        OxygenProduction production = mock(OxygenProduction.class);

        EOxygenCategory subject = new EOxygenCategory(production);

        assertTrue(subject.getQuality() instanceof SupernovaQuality);
    }
}
