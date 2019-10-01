package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.ExceptionConstants;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.exceptions.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;

public class OxygenTankTest {

    private OxygenCategoryBuilder oxygenCategoryBuilder;
    private final LocalDate VALID_DATE = FestivalConstants.Dates.START_DATE.minus(30, DAYS);
    private final LocalDate VALID_DATE_15DAYS_BEFORE_START = FestivalConstants.Dates.START_DATE.minus(15, DAYS);
    private final LocalDate VALID_DATE_5DAYS_BEFORE_START = FestivalConstants.Dates.START_DATE.minus(5, DAYS);
    private final LocalDate INVALID_DATE = FestivalConstants.Dates.START_DATE.plus(1, DAYS);

    @BeforeEach
    void setup() {
        oxygenCategoryBuilder = new OxygenCategoryBuilder();
    }

    @Test
    void orderCategory_shouldBeNebula_whenCreatingOxygenTankWithCategoryA() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), VALID_DATE);

        assertEquals(OxygenConstants.Categories.A_ID, tank.getOxygenTankCategory().getId());
    }

    @Test
    void orderCategory_shouldBeSupergiant_whenCreatingOxygenTankWithCategoryB() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.B_ID), VALID_DATE);

        assertEquals(OxygenConstants.Categories.B_ID, tank.getOxygenTankCategory().getId());
    }

    @Test
    void orderCategory_shouldBeSupernova_whenCreatingOxygenTankWithCategoryE() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.E_ID), VALID_DATE);

        assertEquals(OxygenConstants.Categories.E_ID, tank.getOxygenTankCategory().getId());
    }

    @Test
    void oxygenCategory_shouldBeCategoryB_whenOrderIsNebula_butInLessThan20DaysAndMoreThan10OfFestivalStart() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), VALID_DATE_15DAYS_BEFORE_START);

        assertEquals(OxygenConstants.Categories.B_ID, tank.getOxygenTankCategory().getId());
        assertTrue(tank.getTimeProduced().isBefore(FestivalConstants.Dates.START_DATE));
    }

    @Test
    void oxygenCategory_shouldBeCategoryE_whenOrderIsNebula_butInLessThan10DayOfFestivalStart() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), VALID_DATE_5DAYS_BEFORE_START);

        assertEquals(OxygenConstants.Categories.E_ID, tank.getOxygenTankCategory().getId());
        assertTrue(tank.getTimeProduced().isBefore(FestivalConstants.Dates.START_DATE));
    }

    @Test
    void oxygenCategory_shouldBeCategoryE_whenOrderIsOnTheStartingDateOfFestival() {
        OxygenTank tank = new OxygenTank(oxygenCategoryBuilder
                .buildById(OxygenConstants.Categories.A_ID), FestivalConstants.Dates.START_DATE);

        assertEquals(OxygenConstants.Categories.E_ID, tank.getOxygenTankCategory().getId());
        assertTrue(tank.getTimeProduced().isEqual(FestivalConstants.Dates.START_DATE));
    }

    @Test
    void createOxygenTank_afterTheStartingDateOfFestival_shouldThrowInvalidEventDateException() {
        InvalidDateException thrown = assertThrows(
                InvalidDateException.class,
                () -> new OxygenTank(oxygenCategoryBuilder.buildById(OxygenConstants.Categories.A_ID), INVALID_DATE)
        );

        assertEquals(ExceptionConstants.INVALID_DATE_MESSAGE, thrown.getMessage());
    }

}
