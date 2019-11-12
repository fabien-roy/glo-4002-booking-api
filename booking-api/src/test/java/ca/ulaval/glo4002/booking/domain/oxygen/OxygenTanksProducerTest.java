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
    private OxygenInventory inventory;
    private List<OxygenTank> createdTanks;
    private Integer numberOfDays;
    
    private static final LocalDate START_OF_FESTIVAL_DATE = LocalDate.of(2050, 07, 17);
    private static final LocalDate VALID_CATEGORY_A_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(21);
    private static final LocalDate VALID_CATEGORY_E_BUILD_DATE = START_OF_FESTIVAL_DATE;
    private static final LocalDate INVALID_CATEGORY_A_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(19);
    private static final LocalDate INVALID_CATEGORY_B_BUILD_DATE = START_OF_FESTIVAL_DATE.minusDays(9);
    
    private static final OxygenCategories CATEGORY_A = OxygenCategories.A;
    private static final OxygenCategories CATEGORY_B = OxygenCategories.B;
    private static final OxygenCategories CATEGORY_E = OxygenCategories.E;

    private static final Integer CATEGORY_A_TANKS_NEEDED_BY_DAYS = 3;
    private static final Integer NUMBER_OF_TANK_A_BY_BUNDLE = 5;
    private static final Integer NUMBER_OF_TANK_B_BY_BUNDLE = 3;
    
    @BeforeEach
    void setupFactory() {
        inventory = mock(OxygenInventory.class);
        OxygenTankFactory factory = new OxygenTankFactory();
        producer = new OxygenTanksProducer(inventory, factory);
        createdTanks = new ArrayList<>();
        numberOfDays = 1;
    }

    @Test
    void build_shouldReturnAnEmptyList_whenReserveCanCover() {
        when(inventory.requestTankByCategory(eq(CATEGORY_A), anyInt())).thenReturn(0);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_A, INVALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        assertTrue(createdTanks.isEmpty());
    }

    @Test
    void build_shouldReturnTheCorrectAmountOfTanksNeededToCoverReserve() {
        Integer numberTanksCreated = getNumberCreated(numberOfDays, CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_A_BY_BUNDLE);
        when(inventory.requestTankByCategory(CATEGORY_A, CATEGORY_A_TANKS_NEEDED_BY_DAYS)).thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_A, VALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        assertEquals(numberTanksCreated, createdTanks.size());
        assertEquals(CATEGORY_A, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldReturnTheCorrectAmountOfBTanks_withNoTimeToCreateATanks() {
        Integer numberOfTanksCreated = getNumberCreated(numberOfDays, CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_B_BY_BUNDLE);
        when(inventory.requestTankByCategory(CATEGORY_A, CATEGORY_A_TANKS_NEEDED_BY_DAYS)).thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_A, INVALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        assertEquals(numberOfTanksCreated, createdTanks.size());
        assertEquals(CATEGORY_B, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldReturnTheCorrectAmountOfETanks_whenNoTimeToCreateCategoryAAndB() {
        Integer numberOfTanksCreated = getNumberCreated(numberOfDays, CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_B_BY_BUNDLE);
        when(inventory.requestTankByCategory(CATEGORY_A, CATEGORY_A_TANKS_NEEDED_BY_DAYS)).thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_A, INVALID_CATEGORY_B_BUILD_DATE, numberOfDays);

        assertEquals(numberOfTanksCreated, createdTanks.size());
        assertEquals(CATEGORY_E, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldReturnTheCorrectAmountOfTanks_withOrderForMoreThanOneDays() {
        numberOfDays = 3;
        Integer numberOfTanksCreated = getNumberCreated(numberOfDays, CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_A_BY_BUNDLE);
        Integer numberOfTanksNeeded = CATEGORY_A_TANKS_NEEDED_BY_DAYS * numberOfDays;
        when(inventory.requestTankByCategory(CATEGORY_A, numberOfTanksNeeded)).thenReturn(numberOfTanksNeeded);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_A, VALID_CATEGORY_A_BUILD_DATE, numberOfDays);

        assertEquals(numberOfTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.A, createdTanks.get(0).getCategory());
    }

    @Test
    void build_shouldReturnEmptyList_whenCategoryIsSupernovaButReserveCanCoverAllTanksNeeded() {
        when(inventory.requestTankByCategory(eq(CATEGORY_E), anyInt())).thenReturn(0);

        createdTanks = producer.produceOxygenForOrder(CATEGORY_E, VALID_CATEGORY_E_BUILD_DATE, numberOfDays);

        assertTrue(createdTanks.isEmpty());
    }

    Integer getNumberCreated(Integer numberOfDays, Integer quantityNeededByDays, Integer quantityByBundle) {
        return (int) (Math.ceil((numberOfDays * quantityNeededByDays / (double) quantityByBundle)) * quantityByBundle);
    }

}