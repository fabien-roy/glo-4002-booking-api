package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PassFactoryTest {
    private PassFactory subject;

    @BeforeEach
    public void setUp() {
        subject = new PassFactory();
    }

    @Test
    public void getPassCategoryById_shouldReturnNull_whenCategoryDoesNotExist() {
        PassCategory passCategory = subject.getPassCategoryById(-1L);

        assertNull(passCategory);
    }

    @Test
    public void getPassCategoryById_shouldReturnCorrectPassCategory_whenCategoryIsSupernova() {
        PassCategory passCategory = subject.getPassCategoryById(PassConstants.Categories.SUPERNOVA_ID);
        Map<PassOption, Double> pricePerOption = new HashMap<>();
        pricePerOption.put(subject.getPassOptionById(PassConstants.Options.PACKAGE_ID), PassConstants.Categories.SUPERNOVA_PACKAGE_PRICE);
        pricePerOption.put(subject.getPassOptionById(PassConstants.Options.SINGLE_PASS_ID), PassConstants.Categories.SUPERNOVA_SINGLE_PASS_PRICE);

        validatePassCategory(
                passCategory,
                PassConstants.Categories.SUPERNOVA_ID,
                PassConstants.Categories.SUPERNOVA_NAME,
                pricePerOption,
                PassConstants.Categories.SUPERNOVA_SHUTTLE_CATEGORY_ID,
                PassConstants.Categories.SUPERNOVA_OXYGEN_CATEGORY_ID
        );
    }

    @Test
    public void getPassCategoryById_shouldReturnCorrectPassCategory_whenCategoryIsSupergiant() {
        PassCategory passCategory = subject.getPassCategoryById(PassConstants.Categories.SUPERGIANT_ID);
        Map<PassOption, Double> pricePerOption = new HashMap<>();
        pricePerOption.put(subject.getPassOptionById(PassConstants.Options.PACKAGE_ID), PassConstants.Categories.SUPERGIANT_PACKAGE_PRICE);
        pricePerOption.put(subject.getPassOptionById(PassConstants.Options.SINGLE_PASS_ID), PassConstants.Categories.SUPERGIANT_SINGLE_PASS_PRICE);

        validatePassCategory(
                passCategory,
                PassConstants.Categories.SUPERGIANT_ID,
                PassConstants.Categories.SUPERGIANT_NAME,
                pricePerOption,
                PassConstants.Categories.SUPERGIANT_SHUTTLE_CATEGORY_ID,
                PassConstants.Categories.SUPERGIANT_OXYGEN_CATEGORY_ID
        );
    }

    @Test
    public void getPassCategoryById_shouldReturnCorrectPassCategory_whenCategoryIsNebula() {
        PassCategory passCategory = subject.getPassCategoryById(PassConstants.Categories.NEBULA_ID);
        Map<PassOption, Double> pricePerOption = new HashMap<>();
        pricePerOption.put(subject.getPassOptionById(PassConstants.Options.PACKAGE_ID), PassConstants.Categories.NEBULA_PACKAGE_PRICE);
        pricePerOption.put(subject.getPassOptionById(PassConstants.Options.SINGLE_PASS_ID), PassConstants.Categories.NEBULA_SINGLE_PASS_PRICE);

        validatePassCategory(
                passCategory,
                PassConstants.Categories.NEBULA_ID,
                PassConstants.Categories.NEBULA_NAME,
                pricePerOption,
                PassConstants.Categories.NEBULA_SHUTTLE_CATEGORY_ID,
                PassConstants.Categories.NEBULA_OXYGEN_CATEGORY_ID
        );
    }

    @Test
    public void getPassOptionById_shouldReturnNull_whenOptionDoesNotExist() {
        PassOption passOption = subject.getPassOptionById(-1L);

        assertNull(passOption);
    }

    @Test
    public void getPassOptionById_shouldReturnCorrectPassOption_whenCategoryIsPackage() {
        PassOption passOption = subject.getPassOptionById(PassConstants.Options.PACKAGE_ID);

        validatePassOption(
                passOption,
                PassConstants.Options.PACKAGE_ID,
                PassConstants.Options.PACKAGE_NAME
        );
    }

    @Test
    public void getPassOptionById_shouldReturnCorrectPassOption_whenCategoryIsSinglePass() {
        PassOption passOption = subject.getPassOptionById(PassConstants.Options.SINGLE_PASS_ID);

        validatePassOption(
                passOption,
                PassConstants.Options.SINGLE_PASS_ID,
                PassConstants.Options.SINGLE_PASS_NAME
        );
    }

    private void validatePassCategory(PassCategory passCategory, Long id, String name, Map<PassOption, Double> pricePerOption, Long shuttleCategoryId, Long oxygenCategoryId) {
        assertNotNull(passCategory);
        assertEquals(passCategory.getId(), id);
        assertEquals(passCategory.getName(), name);
        // TODO : Find a correct way to assert what is in pricePerOption
        // assertTrue(Maps.difference(passCategory.getPricePerOption(), pricePerOption).areEqual());
        assertEquals(passCategory.getShuttleCategory().getId(), shuttleCategoryId);
        assertEquals(passCategory.getOxygenCategory().getId(), oxygenCategoryId);
    }

    private void validatePassOption(PassOption passOption, Long id, String name) {
        assertNotNull(passOption);
        assertEquals(passOption.getId(), id);
        assertEquals(passOption.getName(), name);
    }
}
