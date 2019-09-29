package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static java.time.temporal.ChronoUnit.DAYS;

public class OxygenTankTest {

    private OxygenCategoryBuilder oxygenCategoryBuilder;
    private final LocalDate VALID_DATE = FestivalConstants.Dates.START_DATE.minus(30, DAYS);
    private final  LocalDate VALID_DATE_15DAYS_BEFORE_START = FestivalConstants.Dates.START_DATE.minus(15, DAYS);
    private final  LocalDate VALID_DATE_5DAYS_BEFORE_START = FestivalConstants.Dates.START_DATE.minus(5, DAYS);

    @BeforeEach
    void setup() {
        oxygenCategoryBuilder = new OxygenCategoryBuilder();
    }

    @Test
    void orderCategory_shouldBeNebula_whenCreatingOxygenTankWithCategoryA() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), VALID_DATE);

        assertEquals(tank.getOxygenTankCategory().getId(), OxygenConstants.Categories.A_ID);
    }

    @Test
    void orderCategory_shouldBeSupergiant_whenCreatingOxygenTankWithCategoryB() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.B_ID), VALID_DATE);

        assertEquals(tank.getOxygenTankCategory().getId(), OxygenConstants.Categories.B_ID);
    }

    @Test
    void orderCategory_shouldBeSupernova_whenCreatingOxygenTankWithCategoryE() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.E_ID), VALID_DATE);

        assertEquals(tank.getOxygenTankCategory().getId(), OxygenConstants.Categories.E_ID);
    }

    @Test
    void oxygenCategory_shouldBeCategoryB_whenOrderIsNebula_butInLessThan20DaysAndMoreThan10OfFestivalStart() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), VALID_DATE_15DAYS_BEFORE_START);

        assertEquals(tank.getOxygenTankCategory().getId(), OxygenConstants.Categories.B_ID);
        assertTrue(tank.getTimeProduced().isBefore(FestivalConstants.Dates.START_DATE));
    }

    @Test
    void oxygenCategory_shouldBeCategoryE_whenOrderIsNebula_butInLessThan10DayOfFestivalStart() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), VALID_DATE_5DAYS_BEFORE_START);

        assertEquals(tank.getOxygenTankCategory().getId(), OxygenConstants.Categories.E_ID);
        assertTrue(tank.getTimeProduced().isBefore(FestivalConstants.Dates.START_DATE));
    }


}
