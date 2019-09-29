package ca.ulaval.glo4002.booking.domainObjects.passes;

import ca.ulaval.glo4002.booking.domainObjects.passes.categories.SupernovaPassCategory;
import ca.ulaval.glo4002.booking.domainObjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.domainObjects.qualities.SupernovaQuality;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupernovaPassCategoryTest {

    @Test
    public void construction_shouldSetQualityToSupergiant() {
        Map<PassOption, Double> pricePerOption = new HashMap<>();

        SupernovaPassCategory subject = new SupernovaPassCategory(pricePerOption);

        assertTrue(subject.getQuality() instanceof SupernovaQuality);
    }
}
