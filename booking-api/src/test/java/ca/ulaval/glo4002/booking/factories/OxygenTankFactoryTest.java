package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.enums.OxygenCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OxygenTankFactoryTest {

    private OxygenTankFactory factory;
    private OxygenTankInventory inventory;
    private List<OxygenTank> createdTanks;

    private static final LocalDate START_OF_FESTIVAL_DATE = LocalDate.of(2050, 07, 17);
    private static final LocalDate VALID_CATEGORY_A_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(21);
    private static final LocalDate VALID_CATEGORY_B_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(15);
    private static final LocalDate VALID_CATEGORY_E_BUILD_DATE = START_OF_FESTIVAL_DATE;
    private static final LocalDate INVALID_CATEGORY_A_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(19);
    private static final LocalDate INVALID_CATEGORY_B_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(9);

    @BeforeEach
    void setupFactory() {
        inventory = mock(OxygenTankInventory.class);
        factory = new OxygenTankFactory(inventory);
        createdTanks = new ArrayList<>();
    }

    @Test
    void build_shouldReturnEmptyList_whenCategoryIsNebulaButReserveCanCoverAllTanksNeeded() {
        Long numberOfDays = 1L;
        OxygenCategory category = OxygenCategory.A;
        when(inventory.requestTankByCategory(category, 3)).thenReturn(0);

        createdTanks = factory.buildOxygenTank(category, START_OF_FESTIVAL_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(0, createdTanks.size());
    }

    // TODO : OXY : Maybe not needed test for Nebula cover the two other cases as well
    @Test
    void build_shouldReturnEmptyList_whenCategoryIsSupergiantButReserveCanCoverAllTanksNeeded() {
        Long numberOfDays = 1L;
        OxygenCategory category = OxygenCategory.B;
        when(inventory.requestTankByCategory(category, 3)).thenReturn(0);

        createdTanks = factory.buildOxygenTank(category, START_OF_FESTIVAL_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(0, createdTanks.size());
    }

    // TODO : OXY :  Maybe not needed test for Nebula cover the two other cases as well
    @Test
    void build_shouldReturnEmptyList_whenCategoryIsSupernovaButReserveCanCoverAllTanksNeeded() {
        Long numberOfDays = 1L;
        OxygenCategory category = OxygenCategory.E;
        when(inventory.requestTankByCategory(category, 5)).thenReturn(0);

        createdTanks = factory.buildOxygenTank(category, START_OF_FESTIVAL_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(0, createdTanks.size());
    }

    @Test
    void build_shouldBuildFiveOxygenTankCategoryA_whenCategoryIsNebulaAndNoTankInReserve() {
        Long numberOfDays = 1L;
        OxygenCategory category = OxygenCategory.A;
        when(inventory.requestTankByCategory(category, 3)).thenReturn(3);

        createdTanks = factory.buildOxygenTank(category, VALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(5, createdTanks.size());
    }

    @Test
    void build_shouldBuildTenOxygenTankCategoryIsNebulaAndNoTankInReserveAndOrderIsForThreeDays() {
        Long numberOfDays = 3L;
        OxygenCategory category = OxygenCategory.A;
        when(inventory.requestTankByCategory(category, 9)).thenReturn(9);

        createdTanks = factory.buildOxygenTank(category, VALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(10, createdTanks.size());
    }

    @Test
    void build_shouldBuildThreeOxygenTankCategoryB_whenCategoryIsSuperGiantAndNoTankInReserve() {
        Long numberOfDays = 1L;
        OxygenCategory category = OxygenCategory.B;
        when(inventory.requestTankByCategory(category, 3)).thenReturn(3);

        createdTanks = factory.buildOxygenTank(category, VALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(3, createdTanks.size());
    }

    @Test
    void build_shouldBuildNineOxygenTankCategoryB_whenCategoryIsSuperGiantAndNoTankInReserve() {
        Long numberOfDays = 3L;
        OxygenCategory category = OxygenCategory.B;
        when(inventory.requestTankByCategory(category, 9)).thenReturn(9);

        createdTanks = factory.buildOxygenTank(category, VALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(9, createdTanks.size());

    }

    @Test
    void build_shouldBuildFiveOxygenTankCategoryE_whenCategoryIsSupernovaAndNoTankInReserve() {
        Long numberOfDays = 1L;
        OxygenCategory category = OxygenCategory.E;
        when(inventory.requestTankByCategory(category, 5)).thenReturn(5);

        createdTanks = factory.buildOxygenTank(category, START_OF_FESTIVAL_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(5, createdTanks.size());
    }

    @Test
    void build_shouldBuildFifteenOxygenTankCategoryE_whenCategoryIsSupernovaForThreeDaysAndNoTankInReserve() {
        Long numberOfDays = 3L;
        OxygenCategory category = OxygenCategory.E;
        when(inventory.requestTankByCategory(category, 15)).thenReturn(15);

        createdTanks = factory.buildOxygenTank(category, START_OF_FESTIVAL_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(15, createdTanks.size());
    }

    @Test
    void build_shouldReturnEmptyList_whenCategoryIsNebulaButReserveForCategoryAAndBCanCoverAllTankNeededAndStill10DaysBeforeStart() {
        Long numberOfDays = 1L;
        OxygenCategory category = OxygenCategory.A;
        when(inventory.requestTankByCategory(category, 3)).thenReturn(2);
        when(inventory.requestTankByCategory(OxygenCategory.B, 2)).thenReturn(0);

        createdTanks = factory.buildOxygenTank(category, INVALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(0, createdTanks.size());
    }

    @Test
    void build_shouldReturnThreeCategoryBTank_whenCategoryIsNebulaButReserveForCategoryACanCoverOneTankAndStillMoreThan10DaysBeforeStart() {
        Long numberOfDays = 1L;
        OxygenCategory category = OxygenCategory.A;
        when(inventory.requestTankByCategory(category, 3)).thenReturn(2);
        when(inventory.requestTankByCategory(OxygenCategory.B, 2)).thenReturn(2);

        createdTanks = factory.buildOxygenTank(category, VALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(3, createdTanks.size());
    }

    @Test
    void build_shouldReturnOneCategoryETank_whenCategoryIsNebulaButReserveForCategoryAAndBCanCoverTwoTanksAndLessThan10DasBeforeStart() {
        Long numberOfDays = 1L;
        OxygenCategory category = OxygenCategory.A;
        when(inventory.requestTankByCategory(category, 3)).thenReturn(2);
        when(inventory.requestTankByCategory(OxygenCategory.B, 2)).thenReturn(1);
        when(inventory.requestTankByCategory(OxygenCategory.E, 1)).thenReturn(1);

        createdTanks = factory.buildOxygenTank(category, INVALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(1, createdTanks.size());
        assertEquals(OxygenCategory.E, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldReturnOneCategoryETank_whenCategoryIsSupergiantButReserveForCategoryBCanCoverTwoTanksAndLessThanTenDaysBeforeStart() {
        Long numberOfDays = 1L;
        OxygenCategory category = OxygenCategory.B;
        when(inventory.requestTankByCategory(category, 3)).thenReturn(1);
        when(inventory.requestTankByCategory(OxygenCategory.E, 1)).thenReturn(1);

        createdTanks = factory.buildOxygenTank(category, INVALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(1, createdTanks.size());
        assertEquals(OxygenCategory.E, createdTanks.get(0).getCategory());
    }
}