package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.enums.PassCategories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OxygenFactoryTest {

    private OxygenFactory factory;

    @BeforeEach
    void setupFactory() {
        factory = new OxygenFactory();
    }

    @Test
    void build_shouldBuildOxygenCategoryA_whenCategoryIsNebula() {
        LocalDate aValidRequestDate = EventDate.START_DATE.minusDays(21);
        Integer aNumberOfTanks = 5;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(OxygenCategories.A, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.A, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldBuildOxygenCategoryB_whenCategoryIsSupergiant() {
        LocalDate aValidRequestDate = EventDate.START_DATE.minusDays(15);
        Integer aNumberOfTanks = 3;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(OxygenCategories.B, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldBuildOxygenCategoryE_whenCategoryIsSupernova() {
        LocalDate aValidRequestDate = EventDate.START_DATE;
        Integer aNumberOfTanks = 1;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(OxygenCategories.E, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldBuild5OxygenTanks_whenCategoryIsNebula() {
        LocalDate aValidRequestDate = EventDate.START_DATE.minusDays(21);
        Integer aNumberOfTanks = 5;
        Integer expectedNumberOfCreatedTanks = 5;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(OxygenCategories.A, aValidRequestDate, aNumberOfTanks);

        assertEquals(expectedNumberOfCreatedTanks, createdTanks.size());
    }

    @Test
    void build_shouldBuild3OxygenTanks_whenCategoryIsSupergiant() {
        LocalDate aValidRequestDate = EventDate.START_DATE.minusDays(15);
        Integer aNumberOfTanks = 3;
        Integer expectedNumberOfCreatedTanks = 3;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(OxygenCategories.B, aValidRequestDate, aNumberOfTanks);

        assertEquals(expectedNumberOfCreatedTanks, createdTanks.size());
    }

    @Test
    void build_shouldBuild1OxygenTanks_whenCategoryIsSupernova() {
        LocalDate aValidRequestDate = EventDate.START_DATE;
        Integer aNumberOfTanks = 1;
        Integer expectedNumberOfCreatedTanks = 1;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(OxygenCategories.E, aValidRequestDate, aNumberOfTanks);

        assertEquals(expectedNumberOfCreatedTanks, createdTanks.size());
    }

    @Test
    void buildCategory_shouldCategoryE_whenCategoryIsSupernova() {
        PassCategories passCategory = PassCategories.SUPERNOVA;

        OxygenCategories category = factory.buildCategory(passCategory);

        assertEquals(OxygenCategories.E, category);
    }

    @Test
    void buildCategory_shouldCategoryB_whenCategoryIsSupergiant() {
        PassCategories passCategory = PassCategories.SUPERGIANT;

        OxygenCategories category = factory.buildCategory(passCategory);

        assertEquals(OxygenCategories.B, category);
    }

    @Test
    void buildCategory_shouldCategoryA_whenCategoryIsNebula() {
        PassCategories passCategory = PassCategories.NEBULA;

        OxygenCategories category = factory.buildCategory(passCategory);

        assertEquals(OxygenCategories.A, category);
    }
}