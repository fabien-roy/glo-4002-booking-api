package ca.ulaval.glo4002.booking.producers;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.factories.OxygenFactory;
import ca.ulaval.glo4002.booking.repositories.OxygenInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OxygenTankProducerTest {

    // TODO : OxygenTankProducerTest would need a little refactor (optional)

    private OxygenTankProducer producer;
    private OxygenInventory inventory;

    private static final LocalDate VALID_CATEGORY_A_BUILD_DATE = EventDate.START_DATE.minusDays(21);
    private static final LocalDate VALID_CATEGORY_E_BUILD_DATE = EventDate.START_DATE;
    private static final LocalDate INVALID_CATEGORY_A_BUILD_DATE = EventDate.START_DATE.minusDays(19);
    private static final LocalDate INVALID_CATEGORY_B_BUILD_DATE = EventDate.START_DATE.minusDays(9);

    private static final Integer CATEGORY_A_TANKS_NEEDED_BY_DAYS = 3;
    private static final Integer NUMBER_OF_TANK_A_BY_BUNDLE = 5;
    private static final Integer NUMBER_OF_TANK_B_BY_BUNDLE = 3;
    
    @BeforeEach
    void setUpProducer() {
        OxygenInventoryRepository inventoryRepository = mock(OxygenInventoryRepository.class);
        inventory = mock(OxygenInventory.class);
        when(inventoryRepository.getInventory()).thenReturn(inventory);

        OxygenFactory factory = new OxygenFactory();

        producer = new OxygenTankProducer(inventoryRepository, factory);
    }

    @Test
    void produce_shouldReturnAnEmptyList_whenReserveHasEnoughTanks() {
        OxygenCategories aCategory = OxygenCategories.A;
        when(inventory.requestTankByCategory(eq(aCategory), eq(aCategory), anyInt())).thenReturn(0);

        List<OxygenTank> createdTanks = producer.produce(aCategory, INVALID_CATEGORY_A_BUILD_DATE);

        assertTrue(createdTanks.isEmpty());
    }

    // TODO : Separated size and category tests
    @Test
    void produce_shouldReturnTheCorrectAmountOfTanksNeededToCoverReserve() {
        OxygenCategories aCategory = OxygenCategories.A;
        Integer numberTanksCreated = getNumberCreated(CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_A_BY_BUNDLE);
        when(inventory.requestTankByCategory(aCategory, aCategory, CATEGORY_A_TANKS_NEEDED_BY_DAYS)).thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

        List<OxygenTank> createdTanks = producer.produce(aCategory, VALID_CATEGORY_A_BUILD_DATE);

        assertEquals(numberTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.A, createdTanks.get(0).getCategory());
    }

    @Test
    void produce_shouldReturnTheCorrectAmountOfBTanks_withNoTimeToCreateATanks() {
        Integer numberOfTanksCreated = getNumberCreated(CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_B_BY_BUNDLE);
        when(inventory.requestTankByCategory(OxygenCategories.A, OxygenCategories.B, CATEGORY_A_TANKS_NEEDED_BY_DAYS)).thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

        List<OxygenTank> createdTanks = producer.produce(OxygenCategories.A, INVALID_CATEGORY_A_BUILD_DATE);

        assertEquals(numberOfTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory());
    }

    @Test
    void produce_shouldReturnTheCorrectAmountOfETanks_whenNoTimeToCreateCategoryAAndB() {
        Integer numberOfTanksCreated = getNumberCreated(CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_B_BY_BUNDLE);
        when(inventory.requestTankByCategory(OxygenCategories.A, OxygenCategories.E, CATEGORY_A_TANKS_NEEDED_BY_DAYS)).thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

        List<OxygenTank> createdTanks = producer.produce(OxygenCategories.A, INVALID_CATEGORY_B_BUILD_DATE);

        assertEquals(numberOfTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory());
    }

    @Test
    void produce_shouldReturnEmptyList_whenCategoryIsSupernovaButReserveCanCoverAllTanksNeeded() {
        when(inventory.requestTankByCategory(eq(OxygenCategories.E), any(), anyInt())).thenReturn(0);

        List<OxygenTank> createdTanks = producer.produce(OxygenCategories.E, VALID_CATEGORY_E_BUILD_DATE);

        assertTrue(createdTanks.isEmpty());
    }

    private Integer getNumberCreated(Integer quantityNeededByDays, Integer quantityByBundle) {
        return (int) (Math.ceil((quantityNeededByDays / (double) quantityByBundle)) * quantityByBundle);
    }
}