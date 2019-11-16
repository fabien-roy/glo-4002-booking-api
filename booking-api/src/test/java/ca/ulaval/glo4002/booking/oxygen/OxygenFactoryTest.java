package ca.ulaval.glo4002.booking.oxygen;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.passes.PassCategories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OxygenFactoryTest {

    private OxygenFactory factory;

    private OxygenCategory categoryA;
    private OxygenCategory categoryB;
    private OxygenCategory categoryE;

    @BeforeEach
    void setupFactory() {
        factory = new OxygenFactory();
        categoryA = factory.buildCategory(PassCategories.NEBULA);
        categoryB = factory.buildCategory(PassCategories.SUPERGIANT);
        categoryE = factory.buildCategory(PassCategories.SUPERNOVA);
    }

    @Test
    void build_shouldBuildOxygenCategoryA_whenCategoryIsNebula() {
        LocalDate aValidRequestDate = EventDate.START_DATE.minusDays(21);
        Integer aNumberOfTanks = 5;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(categoryA, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.A, createdTanks.get(0).getCategory().getCategory());
    }

    @Test
    void build_shouldBuildOxygenCategoryB_whenCategoryIsSupergiant() {
        LocalDate aValidRequestDate = EventDate.START_DATE.minusDays(15);
        Integer aNumberOfTanks = 3;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(categoryB, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory().getCategory());
    }

    @Test
    void build_shouldBuildOxygenCategoryE_whenCategoryIsSupernova() {
        LocalDate aValidRequestDate = EventDate.START_DATE;
        Integer aNumberOfTanks = 1;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(categoryE, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory().getCategory());
    }

    @Test
    void buildCategory_shouldReturnCategoryE_whenCategoryIsSupernova() {
        PassCategories passCategory = PassCategories.SUPERNOVA;

        OxygenCategory category = factory.buildCategory(passCategory);

        assertEquals(OxygenCategories.E, category.getCategory());
    }

    @Test
    void buildCategory_shouldReturnCategoryB_whenCategoryIsSupergiant() {
        PassCategories passCategory = PassCategories.SUPERGIANT;

        OxygenCategory category = factory.buildCategory(passCategory);

        assertEquals(OxygenCategories.B, category.getCategory());
    }

    @Test
    void buildCategory_shouldReturnCategoryA_whenCategoryIsNebula() {
        PassCategories passCategory = PassCategories.NEBULA;

        OxygenCategory category = factory.buildCategory(passCategory);

        assertEquals(OxygenCategories.A, category.getCategory());
    }

    @Test
    void buildCategory_shouldReturnCategoryE_whenCategoryIsE() {
        OxygenCategories oxygenCategory = OxygenCategories.E;

        OxygenCategory category = factory.buildCategory(oxygenCategory);

        assertEquals(OxygenCategories.E, category.getCategory());
    }

    @Test
    void buildCategory_shouldReturnCategoryB_whenCategoryIsB() {
        OxygenCategories oxygenCategory = OxygenCategories.B;

        OxygenCategory category = factory.buildCategory(oxygenCategory);

        assertEquals(OxygenCategories.B, category.getCategory());
    }

    @Test
    void buildCategory_shouldReturnCategoryA_whenCategoryIsA() {
        OxygenCategories oxygenCategory = OxygenCategories.A;

        OxygenCategory category = factory.buildCategory(oxygenCategory);

        assertEquals(OxygenCategories.A, category.getCategory());
    }

    @Test
    void buildCategoryForRequestDate_shouldReturnNewCategoryA_whenDateIsInRange() {
        OxygenCategory category = factory.buildCategoryForRequestDate(LocalDate.of(2050, 6, 27), OxygenCategories.A);

        assertEquals(OxygenCategories.A, category.getCategory());
    }

    @Test
    void buildCategoryForRequestDate_shouldReturnNewCategoryB_whenDateIsNotInRangeForAButOkForB() {
        OxygenCategory category = factory.buildCategoryForRequestDate(LocalDate.of(2050, 7, 7), OxygenCategories.A);

        assertEquals(OxygenCategories.B, category.getCategory());
    }

    @Test
    void buildCategoryForRequestDate_shouldReturnNewCategoryE_whenDateIsNotInRangeForAOrBButOkForE() {
        OxygenCategory category = factory.buildCategoryForRequestDate(LocalDate.of(2050, 7, 17), OxygenCategories.A);

        assertEquals(OxygenCategories.E, category.getCategory());
    }

    @Test
    void buildCategoryForRequestDate_shouldReturnNewCategoryB_whenDateIsInRange() {
        OxygenCategory category = factory.buildCategoryForRequestDate(LocalDate.of(2050, 7, 7), OxygenCategories.B);

        assertEquals(OxygenCategories.B, category.getCategory());
    }

    @Test
    void buildCategoryForRequestDate_shouldReturnNewCategoryE_whenDateIsNotInRangeFoBButOkForE() {
        OxygenCategory category = factory.buildCategoryForRequestDate(LocalDate.of(2050, 7, 17), OxygenCategories.B);

        assertEquals(OxygenCategories.E, category.getCategory());
    }

    @Test
    void buildCategoryForRequestDate_shouldReturnNewCategoryE_whenDateIsInRange() {
        OxygenCategory category = factory.buildCategoryForRequestDate(LocalDate.of(2050, 7, 17), OxygenCategories.E);

        assertEquals(OxygenCategories.E, category.getCategory());
    }

}