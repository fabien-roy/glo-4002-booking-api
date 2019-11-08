package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OxygenTanksProducerTest {
	
    private OxygenTanksProducer producer;
    private OxygenTankInventory inventory;
    private List<OxygenTank> createdTanks;
    private Integer numberOfDays;
    
    private static final LocalDate START_OF_FESTIVAL_DATE = LocalDate.of(2050, 07, 17);
    private static final LocalDate VALID_CATEGORY_A_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(21);
    private static final LocalDate VALID_CATEGORY_B_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(15);
    private static final LocalDate VALID_CATEGORY_E_BUILD_DATE = START_OF_FESTIVAL_DATE;
    private static final LocalDate INVALID_CATEGORY_A_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(19);
    private static final LocalDate INVALID_CATEGORY_B_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(9);
    
    private static final OxygenCategories CATEGORY_A = OxygenCategories.A;
    private static final OxygenCategories CATEGORY_B = OxygenCategories.B;
    private static final OxygenCategories CATEGORY_E = OxygenCategories.E;

    private static final Integer CATEGORY_A_TANKS_NEEDED_BY_DAYS = 3;
    private static final Integer CATEGORY_B_TANKS_NEEDED_BY_DAYS = 3;
    private static final Integer CATEGORY_E_TANKS_NEEDED_BY_DAYS = 5;
    private static final Integer NUMBER_OF_TANK_A_BY_BUNDLE = 5;
    private static final Integer NUMBER_OF_TANK_B_BY_BUNDLE = 3;
    private static final Integer NUMBER_OF_TANK_E_BY_BUNDLE = 1;
    
    @BeforeEach
    void setupFactory() {
        inventory = mock(OxygenTankInventory.class);
        OxygenTankFactory factory = new OxygenTankFactory();
        producer = new OxygenTanksProducer(inventory, factory);
        createdTanks = new ArrayList<>();
        numberOfDays = 1;
    }
    
    @Test
    void build_shouldReturnEmptyList_whenCategoryIsNebulaButReserveCanCoverAllTanksNeeded() {
        when(inventory.requestTankByCategory(eq(CATEGORY_A), anyInt())).thenReturn(0);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_A, VALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        assertTrue(createdTanks.isEmpty());
    }

    @Test
    void build_shouldReturnAnEmptyList_whenCategoryIsNebulaButReserveForCategoryAAndBCanCoverAllTankNeededAndStill10DaysBeforeStart() {
        when(inventory.requestTankByCategory(eq(CATEGORY_A), anyInt())).thenReturn(2);
        when(inventory.requestTankByCategory(eq(CATEGORY_B), anyInt())).thenReturn(0);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_A, INVALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        assertTrue(createdTanks.isEmpty());
    }

    @Test
    void build_shouldReturnAnEmptyList_whenCategoryIsNebulaButReserveForCategoryAAndBCanCoverAllTankNeededAndLessThan10DaysBeforeStart() {
        when(inventory.requestTankByCategory(eq(CATEGORY_A), anyInt())).thenReturn(2);
        when(inventory.requestTankByCategory(eq(CATEGORY_B), anyInt())).thenReturn(1);
        when(inventory.requestTankByCategory(eq(CATEGORY_E), anyInt())).thenReturn(0);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_A, INVALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        assertTrue(createdTanks.isEmpty());
    }

    @Test
    void build_shouldReturnTheCorrectAmountOfTanks_whenCategoryIsNebula() {
        Integer numberTanksCreated = getNumberCreated(numberOfDays, CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_A_BY_BUNDLE);
        when(inventory.requestTankByCategory(CATEGORY_A, CATEGORY_A_TANKS_NEEDED_BY_DAYS)).thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_A, VALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        assertEquals(numberTanksCreated, createdTanks.size());
    }

    @Test
    void build_shouldReturnEmptyList_whenCategoryIsSupergiantButReserveCanCoverAllTanksNeeded() {
        when(inventory.requestTankByCategory(eq(CATEGORY_B), anyInt())).thenReturn(0);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_B, VALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        assertTrue(createdTanks.isEmpty());
    }

    @Test
    void build_shouldReturnEmptyList_whenCategoryIsSupernovaButReserveCanCoverAllTanksNeeded() {
        when(inventory.requestTankByCategory(eq(CATEGORY_E), anyInt())).thenReturn(0);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_E, VALID_CATEGORY_E_BUILD_DATE, numberOfDays);

        assertTrue(createdTanks.isEmpty());
    }

    @Test
    void build_shouldReturnAnEmptyList_whenCategoryIsNebulaButReserveForCategoryBAndECanCoverAllTankNeededAndLessThen10DaysBeforeStart() {
        when(inventory.requestTankByCategory(eq(CATEGORY_B), anyInt())).thenReturn(2);
        when(inventory.requestTankByCategory(eq(CATEGORY_E), anyInt())).thenReturn(0);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_B, INVALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        assertTrue(createdTanks.isEmpty());
    }

    /*@Test
    void build_shouldBuild10OxygenTankCategoryIsNebulaAndNoTankInReserveAndOrderIsForThreeDays() {
        numberOfDays = 3;
        Long numberofTanksCreated = getNumberCreated(numberOfDays, CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_A_BY_BUNDLE);
        Integer numberOfTanksNeeded = CATEGORY_A_TANKS_NEEDED_BY_DAYS * numberOfDays;
        when(inventory.requestTankByCategory(CATEGORY_A, numberOfTanksNeeded)).thenReturn(numberOfTanksNeeded);

        createdTanks = factory.buildOxygenTank(CATEGORY_A, VALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        assertEquals(numberofTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.A, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldBuildEnoughOxygenTankCategoryB_whenCategoryIsSuperGiantNoTankInReserveFor1Days() {
        Long numberofTanksCreated = getNumberCreated(numberOfDays, CATEGORY_B_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_B_BY_BUNDLE);
        Integer numberOfTanksNeeded = CATEGORY_B_TANKS_NEEDED_BY_DAYS * numberOfDays;
        when(inventory.requestTankByCategory(CATEGORY_B, numberOfTanksNeeded)).thenReturn(numberOfTanksNeeded);

        createdTanks = factory.buildOxygenTank(CATEGORY_B, VALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        assertEquals(numberofTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldBuildEnoughOxygenTankCategoryB_whenCategoryIsSuperGiantNoTankInReserveFor3Days() {
        numberOfDays = 3;
        Long numberofTanksCreated = getNumberCreated(numberOfDays, CATEGORY_B_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_B_BY_BUNDLE);
        Integer numberOfTanksNeeded = CATEGORY_B_TANKS_NEEDED_BY_DAYS * numberOfDays;
        when(inventory.requestTankByCategory(CATEGORY_B, numberOfTanksNeeded)).thenReturn(numberOfTanksNeeded);

        createdTanks = factory.buildOxygenTank(CATEGORY_B, VALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        assertEquals(numberofTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldBuildEnoughOxygenTankCategoryE_whenCategoryIsSupernovaAndNoTankInReserve() {
        Long numberofTanksCreated = getNumberCreated(numberOfDays, CATEGORY_E_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_E_BY_BUNDLE);
        Integer numberOfTanksNeeded = CATEGORY_E_TANKS_NEEDED_BY_DAYS * numberOfDays;
        when(inventory.requestTankByCategory(CATEGORY_E, numberOfTanksNeeded)).thenReturn(numberOfTanksNeeded);

        createdTanks = factory.buildOxygenTank(CATEGORY_E, VALID_CATEGORY_E_BUILD_DATE, numberOfDays);

        assertEquals(numberofTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldBuildEnoughOxygenTanksCategoryE_whenCategoryIsSupernovaForThreeDaysAndNoTankInReserve() {
        numberOfDays = 3;
        Long numberofTanksCreated = getNumberCreated(numberOfDays, CATEGORY_E_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_E_BY_BUNDLE);
        Integer numberOfTanksNeeded = CATEGORY_E_TANKS_NEEDED_BY_DAYS * numberOfDays;
        when(inventory.requestTankByCategory(CATEGORY_E, numberOfTanksNeeded)).thenReturn(numberOfTanksNeeded);

        createdTanks = factory.buildOxygenTank(CATEGORY_E, VALID_CATEGORY_E_BUILD_DATE, numberOfDays);

        assertEquals(numberofTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldReturnAnEmptyList_whenCategoryIsNebulaButReserveForCategoryAABAndECanCoverAllTankNeededAndLessThen10DaysBeforeStart() {
        when(inventory.requestTankByCategory(CATEGORY_A, 3)).thenReturn(2);
        when(inventory.requestTankByCategory(CATEGORY_B, 2)).thenReturn(2);
        when(inventory.requestTankByCategory(CATEGORY_E, 2)).thenReturn(0);

        createdTanks = factory.buildOxygenTank(CATEGORY_A, INVALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        assertTrue(createdTanks.isEmpty());
    }

    @Test
    void build_shouldReturnThreeCategoryBTank_whenCategoryIsNebulaButReserveForCategoryACanCoverOneTankAndStillMoreThen10DaysBeforeStart() {
        when(inventory.requestTankByCategory(CATEGORY_A, 3)).thenReturn(2);
        when(inventory.requestTankByCategory(CATEGORY_B, 2)).thenReturn(2);

        createdTanks = factory.buildOxygenTank(CATEGORY_A, VALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(3, createdTanks.size());
        assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldReturnOneCategoryETank_whenCategoryIsNebulaButReserveForCategoryAAndBCanCoverTwoTanksAndRequestDateIsLessThen10DaysBeforeStart() {
        when(inventory.requestTankByCategory(CATEGORY_A, 3)).thenReturn(2);
        when(inventory.requestTankByCategory(CATEGORY_B, 2)).thenReturn(1);
        when(inventory.requestTankByCategory(CATEGORY_E, 1)).thenReturn(1);

        createdTanks = factory.buildOxygenTank(CATEGORY_A, INVALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(1, createdTanks.size());
        assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldReturnOneCategoryETank_whenCategoryIsSupergiantButReserveForCategoryBCanCoverTwoTanksAndRequestDateIsLessThen10DaysBeforeStart() {
        when(inventory.requestTankByCategory(CATEGORY_B, 3)).thenReturn(1);
        when(inventory.requestTankByCategory(CATEGORY_E, 1)).thenReturn(1);

        createdTanks = factory.buildOxygenTank(CATEGORY_B, INVALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        // TODO : OXY : magic number
        assertEquals(1, createdTanks.size());
        assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldUpdateTheNumberOfTankInUseInInventory_whenFactoryNeedToCreateNewTanks() {
        when(inventory.requestTankByCategory(CATEGORY_A, 3)).thenReturn(3);

        createdTanks = factory.buildOxygenTank(CATEGORY_A, VALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        verify(inventory, times(1)).addTanksToInventory(CATEGORY_A, createdTanks);
    }

    @Test
    void build_shouldReserveTheCorrectAmmountOfNewTankCreatedInInventory_whenFactoryNeedToCreateNewTanks() {
        when(inventory.requestTankByCategory(CATEGORY_A, 3)).thenReturn(3,0);

        factory.buildOxygenTank(CATEGORY_A, VALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        verify(inventory, times(2)).requestTankByCategory(CATEGORY_A, 3);
    }*/

    Integer getNumberCreated(Integer numberOfDays, Integer quantityNeededByDays, Integer quantityByBundle) {
        return (int) (Math.ceil((numberOfDays * quantityNeededByDays / (double) quantityByBundle)) * quantityByBundle);
    }

}