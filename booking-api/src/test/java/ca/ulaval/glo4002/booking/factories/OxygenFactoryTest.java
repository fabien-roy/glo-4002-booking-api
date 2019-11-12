package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OxygenFactoryTest {

    private OxygenFactory factory;
    private List<OxygenTank> createdTanks;

    private static final LocalDate START_OF_FESTIVAL_DATE = LocalDate.of(2050, 07, 17);
    private static final LocalDate VALID_CATEGORY_A_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(21);
    private static final LocalDate VALID_CATEGORY_B_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(15);
    private static final LocalDate VALID_CATEGORY_E_BUILD_DATE = START_OF_FESTIVAL_DATE;

    private static final OxygenCategories CATEGORY_A = OxygenCategories.A;
    private static final OxygenCategories CATEGORY_B = OxygenCategories.B;
    private static final OxygenCategories CATEGORY_E = OxygenCategories.E;

    private static final Integer NUMBER_OF_TANK_A_BY_BUNDLE = 5;
    private static final Integer NUMBER_OF_TANK_B_BY_BUNDLE = 3;
    private static final Integer NUMBER_OF_TANK_E_BY_BUNDLE = 1;

    @BeforeEach
    void setupFactory() {
        factory = new OxygenFactory();
        createdTanks = new ArrayList<>();
    }

    @Test
    void build_shouldBuild5OxygenTankCategoryA_whenCategoryIsNebula() {
        createdTanks = factory.buildOxygenTank(CATEGORY_A, VALID_CATEGORY_A_BUILD_DATE, NUMBER_OF_TANK_A_BY_BUNDLE);
        assertEquals(NUMBER_OF_TANK_A_BY_BUNDLE, createdTanks.size());
        assertEquals(OxygenCategories.A, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldBuild3OxygenTankCategoryB_whenCategoryIsSupergiant() {
        createdTanks = factory.buildOxygenTank(CATEGORY_B, VALID_CATEGORY_B_BUILD_DATE, NUMBER_OF_TANK_B_BY_BUNDLE);
        assertEquals(NUMBER_OF_TANK_B_BY_BUNDLE, createdTanks.size());
        assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldBuild1OxygenTankCategoryE_whenCategoryIsSupernova() {
        createdTanks = factory.buildOxygenTank(CATEGORY_E, VALID_CATEGORY_E_BUILD_DATE, NUMBER_OF_TANK_E_BY_BUNDLE);
        assertEquals(NUMBER_OF_TANK_E_BY_BUNDLE, createdTanks.size());
        assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory());
    }
}