package ca.ulaval.glo4002.booking.domainobjects.passes;

import ca.ulaval.glo4002.booking.domainobjects.passes.categories.SupernovaPassCategory;
import ca.ulaval.glo4002.booking.domainobjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.domainobjects.qualities.SupernovaQuality;
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
