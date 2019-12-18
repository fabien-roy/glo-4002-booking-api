package ca.ulaval.glo4002.booking.oxygen.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
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
    private FestivalConfiguration festivalConfiguration;

    private OxygenProduction categoryA;
    private OxygenProduction categoryB;
    private OxygenProduction categoryE;

    @BeforeEach
    void setupFactory() {
        factory = new OxygenFactory(festivalConfiguration);

        categoryA = factory.createCategory(PassCategories.NEBULA);
        categoryB = factory.createCategory(PassCategories.SUPERGIANT);
        categoryE = factory.createCategory(PassCategories.SUPERNOVA);
    }

    @BeforeEach
    void setUpConfiguration() {
        festivalConfiguration = mock(FestivalConfiguration.class);

        when(festivalConfiguration.getStartEventDate()).thenReturn(FestivalConfiguration.getDefaultStartEventDate());
        when(festivalConfiguration.getEndEventDate()).thenReturn(FestivalConfiguration.getDefaultEndEventDate());
    }

    @Test
    void create_shouldCreateOxygenCategoryA_whenCategoryIsNebula() {
        LocalDate aValidRequestDate = festivalConfiguration.getStartEventDate().minusDays(21).getValue();
        Integer aNumberOfTanks = 5;

        List<OxygenTank> createdTanks = factory.createOxygenTank(categoryA, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.A, createdTanks.get(0).getCategory().getCategory());
    }

    @Test
    void create_shouldCreateOxygenCategoryB_whenCategoryIsSupergiant() {
        LocalDate aValidRequestDate = festivalConfiguration.getStartEventDate().minusDays(15).getValue();
        Integer aNumberOfTanks = 3;

        List<OxygenTank> createdTanks = factory.createOxygenTank(categoryB, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory().getCategory());
    }

    @Test
    void create_shouldCreateOxygenCategoryE_whenCategoryIsSupernova() {
        LocalDate aValidRequestDate = festivalConfiguration.getStartEventDate().getValue();
        Integer aNumberOfTanks = 1;

        List<OxygenTank> createdTanks = factory.createOxygenTank(categoryE, aValidRequestDate, aNumberOfTanks);

        assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory().getCategory());
    }

    @Test
    void createCategory_shouldReturnCategoryE_whenCategoryIsSupernova() {
        PassCategories passCategory = PassCategories.SUPERNOVA;

        OxygenProduction category = factory.createCategory(passCategory);

        assertEquals(OxygenCategories.E, category.getCategory());
    }

    @Test
    void createCategory_shouldReturnCategoryB_whenCategoryIsSupergiant() {
        PassCategories passCategory = PassCategories.SUPERGIANT;

        OxygenProduction category = factory.createCategory(passCategory);

        assertEquals(OxygenCategories.B, category.getCategory());
    }

    @Test
    void createCategory_shouldReturnCategoryA_whenCategoryIsNebula() {
        PassCategories passCategory = PassCategories.NEBULA;

        OxygenProduction category = factory.createCategory(passCategory);

        assertEquals(OxygenCategories.A, category.getCategory());
    }

    @Test
    void createCategory_shouldReturnCategoryE_whenCategoryIsE() {
        OxygenCategories oxygenCategory = OxygenCategories.E;

        OxygenProduction category = factory.createProduction(oxygenCategory);

        assertEquals(OxygenCategories.E, category.getCategory());
    }

    @Test
    void createCategory_shouldReturnCategoryB_whenCategoryIsB() {
        OxygenCategories oxygenCategory = OxygenCategories.B;

        OxygenProduction category = factory.createProduction(oxygenCategory);

        assertEquals(OxygenCategories.B, category.getCategory());
    }

    @Test
    void createCategory_shouldReturnCategoryA_whenCategoryIsA() {
        OxygenCategories oxygenCategory = OxygenCategories.A;

        OxygenProduction category = factory.createProduction(oxygenCategory);

        assertEquals(OxygenCategories.A, category.getCategory());
    }

    @Test
    void createCategoryForRequestDate_shouldReturnNewCategoryA_whenDateIsInRange() {
        OxygenProduction category = factory.createCategoryForRequestDate(LocalDate.of(2050, 6, 27), OxygenCategories.A);

        assertEquals(OxygenCategories.A, category.getCategory());
    }

    @Test
    void createCategoryForRequestDate_shouldReturnNewCategoryB_whenDateIsNotInRangeForAButOkForB() {
        OxygenProduction category = factory.createCategoryForRequestDate(LocalDate.of(2050, 7, 7), OxygenCategories.A);

        assertEquals(OxygenCategories.B, category.getCategory());
    }

    @Test
    void createCategoryForRequestDate_shouldReturnNewCategoryE_whenDateIsNotInRangeForAOrBButOkForE() {
        OxygenProduction category = factory.createCategoryForRequestDate(LocalDate.of(2050, 7, 17), OxygenCategories.A);

        assertEquals(OxygenCategories.E, category.getCategory());
    }

    @Test
    void createCategoryForRequestDate_shouldReturnNewCategoryB_whenDateIsInRange() {
        OxygenProduction category = factory.createCategoryForRequestDate(LocalDate.of(2050, 7, 7), OxygenCategories.B);

        assertEquals(OxygenCategories.B, category.getCategory());
    }

    @Test
    void createCategoryForRequestDate_shouldReturnNewCategoryE_whenDateIsNotInRangeFoBButOkForE() {
        OxygenProduction category = factory.createCategoryForRequestDate(LocalDate.of(2050, 7, 17), OxygenCategories.B);

        assertEquals(OxygenCategories.E, category.getCategory());
    }

    @Test
    void createCategoryForRequestDate_shouldReturnNewCategoryE_whenDateIsInRange() {
        OxygenProduction category = factory.createCategoryForRequestDate(LocalDate.of(2050, 7, 17), OxygenCategories.E);

        assertEquals(OxygenCategories.E, category.getCategory());
    }
}