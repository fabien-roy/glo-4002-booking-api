package ca.ulaval.glo4002.booking.parsers;

import org.junit.jupiter.api.BeforeEach;

public class InventoryParserTest {

    private InventoryParser subject;

    @BeforeEach
    void setup() {
        subject = new InventoryParser();
    }

    // TODO : Those tests are copied from InventoryTest (which was a bad place to be)

    /*
    @Test
    void whenOxygenTankIsAddedWithWithInvalidCategory_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.replaceStoredTanks(AN_INVALID_CATEGORY, 20L)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenGetInventoryByCategoryIsIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.getStoredTanksByCategoryId(AN_INVALID_CATEGORY)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }

    @Test
    void whenGetTankInUseByCategoryIsIsCalledWithAWrongCategoryID_thenShouldThrowOxygenCategoryNotFoundException() {
        OxygenCategoryNotFoundException thrown = assertThrows(
                OxygenCategoryNotFoundException.class,
                () -> subject.getInUseTanksByCategoryId(AN_INVALID_CATEGORY)
        );

        assertEquals(ExceptionConstants.Oxygen.CATEGORY_NOT_FOUND_MESSAGE, thrown.getMessage());
    }
    */
}
