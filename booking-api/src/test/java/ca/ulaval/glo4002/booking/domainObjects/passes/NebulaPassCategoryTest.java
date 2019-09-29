package ca.ulaval.glo4002.booking.domainObjects.passes;

import ca.ulaval.glo4002.booking.domainObjects.passes.categories.NebulaPassCategory;
import ca.ulaval.glo4002.booking.domainObjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.domainObjects.qualities.NebulaQuality;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NebulaPassCategoryTest {

    @Test
    public void construction_shouldSetQualityToNebula() {
        Map<PassOption, Double> pricePerOption = new HashMap<>();

        NebulaPassCategory subject = new NebulaPassCategory(pricePerOption);

        assertTrue(subject.getQuality() instanceof NebulaQuality);
    }
}
