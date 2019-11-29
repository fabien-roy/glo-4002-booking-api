package ca.ulaval.glo4002.booking.oxygen.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.oxygen.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.program.events.EventDate;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;

public class OxygenTankProducerTest {

	// TODO : Mock OxygenFactory in OxygenTankProducer tests

	private OxygenTankProducer producer;
	private Festival festival;
	private OxygenInventoryRepository inventoryRepository;
	private OxygenHistoryRepository historyRepository;
	private OxygenInventory inventory;
	private OxygenHistory history;

	private static final LocalDate VALID_CATEGORY_A_BUILD_DATE = EventDate.getDefaultStartEventDate().minusDays(21).getValue();
	private static final LocalDate VALID_CATEGORY_E_BUILD_DATE = EventDate.getDefaultStartEventDate().getValue();
	private static final LocalDate INVALID_CATEGORY_A_BUILD_DATE = EventDate.getDefaultStartEventDate().minusDays(19).getValue();
	private static final LocalDate INVALID_CATEGORY_B_BUILD_DATE = EventDate.getDefaultStartEventDate().minusDays(9).getValue();

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

		OxygenFactory factory = new OxygenFactory(festival);

		producer = new OxygenTankProducer(inventoryRepository, historyRepository, factory);
	}

	@BeforeEach
	void setUpConfiguration() {
		festival = mock(Festival.class);

		when(festival.getStartEventDate()).thenReturn(EventDate.getDefaultStartEventDate());
		when(festival.getEndEventDate()).thenReturn(EventDate.getDefaultEndEventDate());
	}

	@Test
	void produceOxygenForOrder_shouldReturnAnEmptyList_whenReserveHasEnoughTanks() {
		OxygenCategories aCategory = OxygenCategories.A;
		when(inventory.requestTankByCategory(eq(aCategory), eq(aCategory), anyInt())).thenReturn(0);

		List<OxygenTank> createdTanks = producer.produceOxygenForOrder(aCategory, INVALID_CATEGORY_A_BUILD_DATE);

		assertTrue(createdTanks.isEmpty());
	}

	@Test
	void produceOxygenForOrder_shouldReturnTheCorrectAmountOfTanksNeededToCoverReserve() {
		OxygenCategories aCategory = OxygenCategories.A;
		Integer numberTanksCreated = getNumberCreated(CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_A_BY_BUNDLE);
		when(inventory.requestTankByCategory(aCategory, aCategory, CATEGORY_A_TANKS_NEEDED_BY_DAYS))
				.thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

		List<OxygenTank> createdTanks = producer.produceOxygenForOrder(aCategory, VALID_CATEGORY_A_BUILD_DATE);

		assertEquals(numberTanksCreated, createdTanks.size());
		assertEquals(OxygenCategories.A, createdTanks.get(0).getCategory().getCategory());
	}

	@Test
	void produceOxygenForOrder_shouldReturnTheCorrectAmountOfBTanks_withNoTimeToCreateATanks() {
		Integer numberOfTanksCreated = getNumberCreated(CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_B_BY_BUNDLE);
		when(inventory.requestTankByCategory(OxygenCategories.A, OxygenCategories.B, CATEGORY_A_TANKS_NEEDED_BY_DAYS))
				.thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

		List<OxygenTank> createdTanks = producer.produceOxygenForOrder(OxygenCategories.A,
				INVALID_CATEGORY_A_BUILD_DATE);

		assertEquals(numberOfTanksCreated, createdTanks.size());
		assertEquals(OxygenCategories.B, createdTanks.get(0).getCategory().getCategory());
	}

	@Test
	void produceOxygenForOrder_shouldReturnTheCorrectAmountOfETanks_whenNoTimeToCreateCategoryAAndB() {
		Integer numberOfTanksCreated = getNumberCreated(CATEGORY_A_TANKS_NEEDED_BY_DAYS, NUMBER_OF_TANK_B_BY_BUNDLE);
		when(inventory.requestTankByCategory(OxygenCategories.A, OxygenCategories.E, CATEGORY_A_TANKS_NEEDED_BY_DAYS))
				.thenReturn(CATEGORY_A_TANKS_NEEDED_BY_DAYS);

		List<OxygenTank> createdTanks = producer.produceOxygenForOrder(OxygenCategories.A,
				INVALID_CATEGORY_B_BUILD_DATE);

		assertEquals(numberOfTanksCreated, createdTanks.size());
		assertEquals(OxygenCategories.E, createdTanks.get(0).getCategory().getCategory());
	}

	@Test
	void produceOxygenForOrder_shouldReturnEmptyList_whenCategoryIsSupernovaButReserveCanCoverAllTanksNeeded() {
		when(inventory.requestTankByCategory(eq(OxygenCategories.E), any(), anyInt())).thenReturn(0);

		List<OxygenTank> createdTanks = producer.produceOxygenForOrder(OxygenCategories.E, VALID_CATEGORY_E_BUILD_DATE);

		assertTrue(createdTanks.isEmpty());
	}

	@Test
	void produceOxygenForOrder_shouldUpdateInventory() {
		when(inventory.requestTankByCategory(eq(OxygenCategories.E), any(), anyInt())).thenReturn(0);

		producer.produceOxygenForOrder(OxygenCategories.E, VALID_CATEGORY_E_BUILD_DATE);

		verify(inventoryRepository).setInventory(any(OxygenInventory.class));
	}

	@Test
	void produceOxygenForOrder_shouldUpdateHistory() {
		when(inventory.requestTankByCategory(eq(OxygenCategories.E), any(), anyInt())).thenReturn(0);

		producer.produceOxygenForOrder(OxygenCategories.E, VALID_CATEGORY_E_BUILD_DATE);

		verify(inventoryRepository).setInventory(any(OxygenInventory.class));
	}

	@Test
	void produceOxygenByQuantity_shouldReturnAnEmptyList_whenReserveHasEnoughTanks() {
		OxygenCategories aCategory = OxygenCategories.A;
		OxygenCategory category = mock(OxygenCategory.class);
		when(inventory.requestTankByCategory(eq(aCategory), eq(aCategory), anyInt())).thenReturn(0);

		List<OxygenTank> createdTanks = producer.produceOxygenByQuantity(category, INVALID_CATEGORY_A_BUILD_DATE, 1);

		assertTrue(createdTanks.isEmpty());
	}

	@Test
	void produceOxygenByQuantity_shouldReturnEmptyList_whenCategoryIsSupernovaButReserveCanCoverAllTanksNeeded() {
		OxygenCategory category = mock(OxygenCategory.class);
		when(inventory.requestTankByCategory(eq(OxygenCategories.E), any(), anyInt())).thenReturn(0);

		List<OxygenTank> createdTanks = producer.produceOxygenByQuantity(category, VALID_CATEGORY_E_BUILD_DATE, 1);

		assertTrue(createdTanks.isEmpty());
	}

	@Test
	void produceOxygenByQuantity_shouldUpdateInventory() {
		OxygenCategory category = mock(OxygenCategory.class);
		when(inventory.requestTankByCategory(eq(OxygenCategories.E), any(), anyInt())).thenReturn(0);

		producer.produceOxygenByQuantity(category, VALID_CATEGORY_E_BUILD_DATE, 1);

		verify(inventoryRepository).setInventory(any(OxygenInventory.class));
	}

	@Test
	void produceOxygenByQuantity_shouldUpdateHistory() {
		OxygenCategory category = mock(OxygenCategory.class);
		when(inventory.requestTankByCategory(eq(OxygenCategories.E), any(), anyInt())).thenReturn(0);

		producer.produceOxygenByQuantity(category, VALID_CATEGORY_E_BUILD_DATE, 1);

		verify(inventoryRepository).setInventory(any(OxygenInventory.class));
	}

	@Test
	private Integer getNumberCreated(Integer quantityNeededByDays, Integer quantityByBundle) {
		return (int) (Math.ceil((quantityNeededByDays / (double) quantityByBundle)) * quantityByBundle);
	}
}