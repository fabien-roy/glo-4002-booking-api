package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OxygenTankTest {

    private OxygenCategoryBuilder oxygenCategoryBuilder;

    @BeforeEach
    void setup() {
        oxygenCategoryBuilder = new OxygenCategoryBuilder();
    }

    @Test
    void orderCategory_shouldBeNebula_whenCreatingOxygenTankWithCategoryA() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), LocalDate.parse("2050-01-01"));

        assertEquals(tank.getOxygenTankCategory().getId(), OxygenConstants.Categories.A_ID);
    }

    @Test
    void orderCategory_shouldBeSupergiant_whenCreatingOxygenTankWithCategoryB() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.B_ID), LocalDate.parse("2050-01-01"));

        assertEquals(tank.getOxygenTankCategory().getId(), OxygenConstants.Categories.B_ID);
    }

    @Test
    void orderCategory_shouldBeSupernova_whenCreatingOxygenTankWithCategoryE() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.E_ID), LocalDate.parse("2050-01-01"));

        assertEquals(tank.getOxygenTankCategory().getId(), OxygenConstants.Categories.E_ID);
    }

    @Test
    void oxygenCategory_shouldBeCategoryB_whenOrderIsNebula_butInLessThan20DaysAndMoreThan10OfFestivalStart() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), LocalDate.parse("2050-07-05") );

        assertEquals(tank.getOxygenTankCategory().getId(), OxygenConstants.Categories.B_ID);
    }

    @Test
    void oxygenCategory_shouldBeCategoryE_whenOrderIsNebula_butInLessThan10DayOfFestivalStart() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), LocalDate.parse("2050-07-11") );

        assertEquals(tank.getOxygenTankCategory().getId(), OxygenConstants.Categories.B_ID);
    }


}
