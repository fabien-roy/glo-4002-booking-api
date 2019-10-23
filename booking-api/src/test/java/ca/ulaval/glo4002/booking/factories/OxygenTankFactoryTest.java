package ca.ulaval.glo4002.booking.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.enterprise.module.bootstrap.BootException;

import ca.ulaval.glo4002.booking.domain.OxygenTank;
import ca.ulaval.glo4002.booking.domain.OxygenTankInventory;
import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenTankFactoryTest {

	private OxygenTankFactory factory;
	private OxygenTankInventory inventory;
	private List<OxygenTank> createdTanks;
	
	private static final LocalDate START_OF_FESTIVAL_DATE = LocalDate.of(2050, 07, 17);
	
	@BeforeEach
	void setupFactory() {
		inventory = mock(OxygenTankInventory.class);
		factory = new OxygenTankFactory(inventory);
		createdTanks = new ArrayList<>();
	}
	
	@Test
	void build_shouldReturnEmptyList_whenCategoryIsNebulaButReserveCanCoverAllTanksNeeded() throws BootException {
		Long numberOfDays = 1L;
		OxygenTankCategory category = OxygenTankCategory.CATEGORY_A;
		when(inventory.requestTankByCategory(category, 3L)).thenReturn(0L);
		
		createdTanks = factory.buildOxygenTankByCategory(category, START_OF_FESTIVAL_DATE, numberOfDays);
		
		assertTrue(createdTanks.size() == 0);
	}
	
	// TODO : Maybe not needed test for Nebula cover the two other cases as well
	@Test
	void build_shouldReturnEmptyList_whenCategoryIsSupergiantButReserveCanCoverAllTanksNeeded() throws BootException {
		Long numberOfDays = 1L;
		OxygenTankCategory category = OxygenTankCategory.CATEGORY_B;
		when(inventory.requestTankByCategory(category, 3L)).thenReturn(0L);
		
		createdTanks = factory.buildOxygenTankByCategory(category, START_OF_FESTIVAL_DATE, numberOfDays);
		
		assertTrue(createdTanks.size() == 0);
	}

	// TODO : Maybe not needed test for Nebula cover the two other cases as well
	@Test
	void build_shouldReturnEmptyList_whenCategoryIsSupernovaButReserveCanCoverAllTanksNeeded() throws BootException {
		Long numberOfDays = 1L;
		OxygenTankCategory category = OxygenTankCategory.CATEGORY_E;
		when(inventory.requestTankByCategory(category, 3L)).thenReturn(0L);
		
		createdTanks = factory.buildOxygenTankByCategory(category, START_OF_FESTIVAL_DATE, numberOfDays);
		
		assertTrue(createdTanks.size() == 0);
	}
	
    @Test
    void build_shouldBuildThreeOxygenTankCategoryA_whenCategoryIsNebulaAndNoTankInReserve() throws BootException {
    	Long numberOfDays = 1L;
    	OxygenTankCategory category = OxygenTankCategory.CATEGORY_A;
    	LocalDate requestDate = START_OF_FESTIVAL_DATE.minusDays(21);
    	when(inventory.requestTankByCategory(category, 3L)).thenReturn(3L);
    	
    	createdTanks = factory.buildOxygenTankByCategory(category, requestDate, numberOfDays);
    	
    	assertTrue(createdTanks.size() == 3);
    }
    
    @Test
    void build_shouldReturnEmptyList_whenCategoryIsNebulaButReserveForCategoryAAndBCanCoverAllTankNeededAndStill10DaysBeforeStart () throws BootException {
    	Long numberOfDays = 1L;
    	OxygenTankCategory category = OxygenTankCategory.CATEGORY_A;
    	LocalDate requestDate = START_OF_FESTIVAL_DATE.minusDays(15);
    	when(inventory.requestTankByCategory(category, 3L)).thenReturn(2L);
    	when(inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_B, 2L)).thenReturn(0L);
    	
    	createdTanks = factory.buildOxygenTankByCategory(category, requestDate, numberOfDays);
    	
    	assertTrue(createdTanks.size() == 0);
    }
    
    @Test
    void build_shouldReturnTwoCategoryBTank_whenCategoryIsNebulaButReserveForCategoryACanCoverOneTankAndStill10DaysBeforeStart () throws BootException {
    	Long numberOfDays = 1L;
    	OxygenTankCategory category = OxygenTankCategory.CATEGORY_A;
    	LocalDate requestDate = START_OF_FESTIVAL_DATE.minusDays(15);
    	when(inventory.requestTankByCategory(category, 3L)).thenReturn(2L);
    	when(inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_B, 2L)).thenReturn(2L);
    	
    	createdTanks = factory.buildOxygenTankByCategory(category, requestDate, numberOfDays);
    	
    	assertTrue(createdTanks.size() == 2);
    	assertEquals(OxygenTankCategory.CATEGORY_B, createdTanks.get(0).getCategory());
    	assertEquals(OxygenTankCategory.CATEGORY_B, createdTanks.get(1).getCategory());
    }
}
