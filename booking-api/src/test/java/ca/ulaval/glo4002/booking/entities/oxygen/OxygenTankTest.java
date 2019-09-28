package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.builders.passes.PassCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.entities.passes.categories.PassCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class OxygenTankTest {

    @Test
    void orderCategory_shouldBeNebula_whenCreatingOxygenTankWithCategoryA() {
        PassCategoryBuilder passCategoryBuilder = new PassCategoryBuilder();
        PassCategory passCategory = passCategoryBuilder.buildById(PassConstants.Categories.NEBULA_ID);

        OxygenTank tank = new OxygenTank(passCategory, 1, LocalDate.now());

    }

    @Test
    void orderCategory_shouldBeSupergiant_whenCreatingOxygenTankWithCategoryB() {

    }

    @Test
    void orderCategory_shouldBeSupernova_whenCreatingOxygenTankWithCategoryE() {

    }
}
