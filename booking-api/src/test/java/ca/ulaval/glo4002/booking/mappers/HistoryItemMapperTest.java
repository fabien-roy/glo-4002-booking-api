package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.oxygen.History;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.oxygen.HistoryItemDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;

class HistoryItemMapperTest {

	HistoryItemMapper mapper;
	OxygenTankInventory mockedInventory;
	History mockedHistory;
	LocalDate date = LocalDate.of(2050, 7, 1);

	@BeforeEach
	void setUpMapper() {
		mapper = new HistoryItemMapper();
		mockedInventory = mock(OxygenTankInventory.class);
	}

	@BeforeEach
	void setupHistory() {
		// TODO maybe refactor this
		mockedHistory = mock(History.class);
		List<OxygenTank> tanks = new ArrayList<OxygenTank>();
		OxygenTank tankA = mock(OxygenTank.class);
		when(tankA.getCategory()).thenReturn(OxygenCategories.A);
		tanks.add(tankA);
		OxygenTank tankB = mock(OxygenTank.class);
		when(tankB.getCategory()).thenReturn(OxygenCategories.B);
		tanks.add(tankB);
		OxygenTank tankE = mock(OxygenTank.class);
		when(tankE.getCategory()).thenReturn(OxygenCategories.E);
		tanks.add(tankE);
		when(mockedHistory.getProducedOxygenTanksForDate(any())).thenReturn(tanks);
		Map<LocalDate, List<OxygenTank>> oxygenMap = new HashMap<LocalDate, List<OxygenTank>>();
		oxygenMap.put(date, tanks);
		when(mockedHistory.getProducedOxygenTanks()).thenReturn(oxygenMap);
		when(mockedHistory.getRequestedOxygenTanks()).thenReturn(oxygenMap);
	}

	@Test
	void toDto_shouldBuildWithCorrectDate() {
		List<HistoryItemDto> items = mapper.toDto(mockedHistory);

		assertEquals(items.get(0).getDate(), date.toString());
	}

	@Test
	void toDto_shouldBuildWithCorrectQtyOxygenTankBought() {
		List<HistoryItemDto> items = mapper.toDto(mockedHistory);

		List<OxygenTank> tanks = mockedHistory.getProducedOxygenTanksForDate(date);
		Integer expectedOxygenBought = OxygenTank.CATEGORY_E_NUMBER_OF_RESOURCES_NEEDED;

		assertEquals(expectedOxygenBought.longValue(), items.get(0).getQtyOxygenTankBought());
	}

	@Test
	void toDto_shouldBuildWithCorrectQtyWaterUsed() {
		List<HistoryItemDto> items = mapper.toDto(mockedHistory);

		List<OxygenTank> tanks = mockedHistory.getProducedOxygenTanksForDate(date);
		Integer expectedWater = OxygenTank.CATEGORY_B_NUMBER_OF_RESOURCES_NEEDED;

		assertEquals(expectedWater, items.get(0).getQtyWaterUsed());
	}

	@Test
	void toDto_shouldBuildWithCorrectQtyCandlesUsed() {
		List<HistoryItemDto> items = mapper.toDto(mockedHistory);

		List<OxygenTank> tanks = mockedHistory.getProducedOxygenTanksForDate(date);
		Integer expectedCandle = OxygenTank.CATEGORY_A_NUMBER_OF_RESOURCES_NEEDED;

		assertEquals(expectedCandle, items.get(0).getQtyCandlesUsed());
	}

	@Test
	void toDto_shouldBuildWithCorrectQtyOxygenTankMade() {
		List<HistoryItemDto> items = mapper.toDto(mockedHistory);

		Integer qtyOxygen = mockedHistory.getProducedOxygenTanksForDate(date).size();

		assertEquals(qtyOxygen.longValue(), items.get(0).getQtyOxygenTankMade());
	}

	private void fillInventory(OxygenTankInventory mockedInventory) {

	}
}
