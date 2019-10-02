package ca.ulaval.glo4002.booking.builders.oxygen;

import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.orders.Order;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.exceptions.oxygen.OxygenCategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class OxygenCategoryBuilderTest {

    private OxygenCategoryBuilder subject;
    private static final Long AN_INVALID_ID = -1L;
    private static final String AN_INVALID_NAME = "An invalid code";

    @BeforeEach
    public void setUp(){
        subject = new OxygenCategoryBuilder();
    }

    @Test
    public void buildById_shouldThrowOxygenCategoryNotFoundException_whenCategoryDoesNotExist() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.buildById(AN_INVALID_ID)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void buildById_shouldReturnCorrectOxygenCategory_whenCategoryIsE() {
        OxygenCategory oxygenCategory = subject.buildById(OxygenConstants.Categories.E_ID);

        validateOxygenCategory(
                oxygenCategory,
                OxygenConstants.Categories.E_ID,
                OxygenConstants.Categories.E_NAME,
                OxygenConstants.Categories.E_PRODUCTION_ID
        );
    }

    @Test
    public void buildById_shouldReturnCorrectOxygenCategory_whenCategoryIsB() {
        OxygenCategory oxygenCategory = subject.buildById(OxygenConstants.Categories.B_ID);

        validateOxygenCategory(
                oxygenCategory,
                OxygenConstants.Categories.B_ID,
                OxygenConstants.Categories.B_NAME,
                OxygenConstants.Categories.B_PRODUCTION_ID
        );
    }

    @Test
    public void buildById_shouldReturnCorrectOxygenCategory_whenCategoryIsA() {
        OxygenCategory oxygenCategory = subject.buildById(OxygenConstants.Categories.A_ID);

        validateOxygenCategory(
                oxygenCategory,
                OxygenConstants.Categories.A_ID,
                OxygenConstants.Categories.A_NAME,
                OxygenConstants.Categories.A_PRODUCTION_ID
        );
    }

    @Test
    public void buildByName_shouldThrowOxygenCategoryNotFoundException_whenCategoryDoesNotExist() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.buildByName(AN_INVALID_NAME)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_ERROR, thrown.getMessage());
    }

    @Test
    public void buildByName_shouldReturnCorrectOxygenCategory_whenCategoryIsE() {
        OxygenCategory oxygenCategory = subject.buildByName(OxygenConstants.Categories.E_NAME);

        validateOxygenCategory(
                oxygenCategory,
                OxygenConstants.Categories.E_ID,
                OxygenConstants.Categories.E_NAME,
                OxygenConstants.Categories.E_PRODUCTION_ID
        );
    }

    @Test
    public void buildByName_shouldReturnCorrectOxygenCategory_whenCategoryIsB() {
        OxygenCategory oxygenCategory = subject.buildByName(OxygenConstants.Categories.B_NAME);

        validateOxygenCategory(
                oxygenCategory,
                OxygenConstants.Categories.B_ID,
                OxygenConstants.Categories.B_NAME,
                OxygenConstants.Categories.B_PRODUCTION_ID
        );
    }

    @Test
    public void buildByName_shouldReturnCorrectOxygenCategory_whenCategoryIsA() {
        OxygenCategory oxygenCategory = subject.buildByName(OxygenConstants.Categories.A_NAME);

        validateOxygenCategory(
                oxygenCategory,
                OxygenConstants.Categories.A_ID,
                OxygenConstants.Categories.A_NAME,
                OxygenConstants.Categories.A_PRODUCTION_ID
        );
    }

    private void validateOxygenCategory(OxygenCategory oxygenCategory, Long id, String name, Long productionId) {
        assertNotNull(oxygenCategory);
        assertEquals(oxygenCategory.getId(), id);
        assertEquals(oxygenCategory.getName(), name);
        assertEquals(oxygenCategory.getProduction().getId(), productionId);
    }

    @Test
    public void createOxygenTankCategoryA_whenOrderIsForNebula(){
        // TODO : Do this test
        Order mockedOrder = mock(Order.class);
    }
}
