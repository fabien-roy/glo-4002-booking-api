package ca.ulaval.glo4002.booking.oxygen.domain;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.program.events.domain.EventDate;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OxygenFactoryTest {

    private OxygenFactory factory;
    private Festival festival;

    private OxygenCategory categoryA;
    private OxygenCategory categoryB;
    private OxygenCategory categoryE;

    @BeforeEach
    void setupFactory() {
        factory = new OxygenFactory(festival);

        categoryA = factory.buildCategory(PassCategories.NEBULA);
        categoryB = factory.buildCategory(PassCategories.SUPERGIANT);
        categoryE = factory.buildCategory(PassCategories.SUPERNOVA);
    }

    @BeforeEach
    void setUpConfiguration() {
        festival = mock(Festival.class);

        when(festival.getStartEventDate()).thenReturn(EventDate.getDefaultStartEventDate());
        when(festival.getEndEventDate()).thenReturn(EventDate.getDefaultEndEventDate());
    }

    @Test
    void build_shouldBuildOxygenCategoryA_whenCategoryIsNebula() {
        LocalDate aValidRequestDate = festival.getStartEventDate().minusDays(21).getValue();
        Integer aNumberOfTanks = 5;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(categoryA, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.A, createdTanks.get(0).getCategory().getCategory());
    }

    @Test
    void build_shouldBuildOxygenCategoryB_whenCategoryIsSupergiant() {
        LocalDate aValidRequestDate = festival.getStartEventDate().minusDays(15).getValue();
        Integer aNumberOfTanks = 3;

        List<OxygenTank> createdTanks = factory.buildOxygenTank(categoryB, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory().getCategory());
    }

    @Test
    void build_shouldBuildOxygenCategoryE_whenCategoryIsSupernova() {
        LocalDate aValidRequestDate = festival.getStartEventDate().getValue();
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