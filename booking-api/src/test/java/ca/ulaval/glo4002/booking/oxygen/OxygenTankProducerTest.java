package ca.ulaval.glo4002.booking.oxygen;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class OxygenTankProducerTest {

    private OxygenTankProducer producer;
    private OxygenInventoryRepository inventoryRepository;
    private OxygenHistoryRepository historyRepository;
    private OxygenInventory inventory;
    private OxygenHistory history;

    private static final LocalDate VALID_CATEGORY_A_BUILD_DATE = EventDate.START_DATE.minusDays(21);
    private static final LocalDate VALID_CATEGORY_E_BUILD_DATE = EventDate.START_DATE;
    private static final LocalDate INVALID_CATEGORY_A_BUILD_DATE = EventDate.START_DATE.minusDays(19);
    private static final LocalDate INVALID_CATEGORY_B_BUILD_DATE = EventDate.START_DATE.minusDays(9);

    private static final Integer CATEGORY_A_TANKS_NEEDED_BY_DAYS = 3;
    private static final Integer NUMBER_OF_TANK_A_BY_BUNDLE = 5;
    private static final Integer NUMBER_OF_TANK_B_BY_BUNDLE = 3;
    
    @BeforeEach
    void setUpProducer() {
        inventoryRepository = mock(OxygenInventoryRepository.class);
        historyRepository = mock(OxygenHistoryRepository.class);
        inventory = mock(OxygenInventory.class);
        history = mock(OxygenHistory.class);
        when(inventoryRepository.getInventory()).thenReturn(inventory);
        when(historyRepository.getHistory()).thenReturn(history);

        OxygenFactory factory = new OxygenFactory();

        producer = new OxygenTankProducer(inventoryRepository, historyRepository, factory);
    }

    @Test
    void produceForDay_shouldReturnAnEmptyList_whenReserveHasEnoughTanks() {
        OxygenCategories aCategory = OxygenCategories.A;
        when(inventory.requestTankByCategory(eq(aCategory), eq(aCategory), anyInt())).thenReturn(0);

        List<OxygenTank> createdTanks = producer.produceForDay(aCategory, INVALID_CATEGORY_A_BUILD_DATE);

        assertTrue(createdTanks.isEmpty());
    }

    @Test
    void produceForDay_shouldReturnTheCorrectAmountOfTanksNeededToCoverReserve() {
        OxygenCategories aCategory = OxygenCategories.A;
        Integer numberTanksCreated = getNumberCreated(CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_A_BY_BUNDLE);
        when(inventory.requestTankByCategory(aCategory, aCategory, CATEGORY_A_TANKS_NEEDED_BY_DAYS)).thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

        List<OxygenTank> createdTanks = producer.produceForDay(aCategory, VALID_CATEGORY_A_BUILD_DATE);

        assertEquals(numberTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.A, createdTanks.get(0).getCategory());
    }

    @Test
    void produceForDay_shouldReturnTheCorrectAmountOfBTanks_withNoTimeToCreateATanks() {
        Integer numberOfTanksCreated = getNumberCreated(CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_B_BY_BUNDLE);
        when(inventory.requestTankByCategory(OxygenCategories.A, OxygenCategories.B, CATEGORY_A_TANKS_NEEDED_BY_DAYS)).thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

        List<OxygenTank> createdTanks = producer.produceForDay(OxygenCategories.A, INVALID_CATEGORY_A_BUILD_DATE);

        assertEquals(numberOfTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory());
    }

    @Test
    void produceForDay_shouldReturnTheCorrectAmountOfETanks_whenNoTimeToCreateCategoryAAndB() {
        Integer numberOfTanksCreated = getNumberCreated(CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_B_BY_BUNDLE);
        when(inventory.requestTankByCategory(OxygenCategories.A, OxygenCategories.E, CATEGORY_A_TANKS_NEEDED_BY_DAYS)).thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

        List<OxygenTank> createdTanks = producer.produceForDay(OxygenCategories.A, INVALID_CATEGORY_B_BUILD_DATE);

        assertEquals(numberOfTanksCreated, createdTanks.size());
        assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory());
    }

    @Test
    void produceForDay_shouldReturnEmptyList_whenCategoryIsSupernovaButReserveCanCoverAllTanksNeeded() {
        when(inventory.requestTankByCategory(eq(OxygenCategories.E), any(), anyInt())).thenReturn(0);

        List<OxygenTank> createdTanks = producer.produceForDay(OxygenCategories.E, VALID_CATEGORY_E_BUILD_DATE);

        assertTrue(createdTanks.isEmpty());
    }

    @Test
    void produceForDay_shouldUpdateInventory() {
        when(inventory.requestTankByCategory(eq(OxygenCategories.E), any(), anyInt())).thenReturn(0);

        producer.produceForDay(OxygenCategories.E, VALID_CATEGORY_E_BUILD_DATE);

        verify(inventoryRepository).setInventory(any(OxygenInventory.class));
    }

    @Test
    void produceForDay_shouldUpdateHistory() {
        when(inventory.requestTankByCategory(eq(OxygenCategories.E), any(), anyInt())).thenReturn(0);

        producer.produceForDay(OxygenCategories.E, VALID_CATEGORY_E_BUILD_DATE);

        verify(inventoryRepository).setInventory(any(OxygenInventory.class));
    }

    // TODO : Tests for inventory calls (optionnal)

    // TODO : Tests for history calls

    private Integer getNumberCreated(Integer quantityNeededByDays, Integer quantityByBundle) {
        return (int) (Math.ceil((quantityNeededByDays / (double) quantityByBundle)) * quantityByBundle);
    }
}