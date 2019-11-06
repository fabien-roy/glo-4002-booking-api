package ca.ulaval.glo4002.booking.domain.oxygen;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;


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

}