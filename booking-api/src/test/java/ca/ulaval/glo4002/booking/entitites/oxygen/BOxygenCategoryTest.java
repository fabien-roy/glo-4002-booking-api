package ca.ulaval.glo4002.booking.entitites.oxygen;

import ca.ulaval.glo4002.booking.entities.oxygen.categories.BOxygenCategory;
import ca.ulaval.glo4002.booking.entities.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.entities.qualities.SupergiantQuality;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class BOxygenCategoryTest {

    @Test
    public void construction_shouldSetQualityToSupergiant() {
        OxygenProduction production = mock(OxygenProduction.class);

        BOxygenCategory subject = new BOxygenCategory(production);

        assertTrue(subject.getQuality() instanceof SupergiantQuality);
    }
}
