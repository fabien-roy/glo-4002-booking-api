package ca.ulaval.glo4002.booking.domainobjects.passes;

import ca.ulaval.glo4002.booking.domainobjects.passes.categories.SupergiantPassCategory;
import ca.ulaval.glo4002.booking.domainobjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.domainobjects.qualities.SupergiantQuality;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupergiantPassCategoryTest {

    @Test
    public void construction_shouldSetQualityToSupergiant() {
        Map<PassOption, Double> pricePerOption = new HashMap<>();

        SupergiantPassCategory subject = new SupergiantPassCategory(pricePerOption);

        assertTrue(subject.getQuality() instanceof SupergiantQuality);
    }
}
