package ca.ulaval.glo4002.booking.domainobjects.oxygen;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.BOxygenCategory;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.productions.OxygenProduction;
import ca.ulaval.glo4002.booking.domainobjects.qualities.SupergiantQuality;
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
