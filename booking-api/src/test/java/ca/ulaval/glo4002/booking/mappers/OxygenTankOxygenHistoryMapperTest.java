package ca.ulaval.glo4002.booking.mappers;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;

class OxygenTankOxygenHistoryMapperTest {

	private static final OxygenCategories CATEGORY_A = OxygenCategories.A;
	private static final OxygenCategories CATEGORY_B = OxygenCategories.B;
	private static final OxygenCategories CATEGORY_E = OxygenCategories.E;

	private OxygenHistoryMapper mapper;
	private OxygenInventory mockedInventory;
	private OxygenHistory mockedOxygenHistory;
	private LocalDate date = LocalDate.of(2050, 7, 1);

	@BeforeEach
	void setUpMapper() {
		mapper = new OxygenHistoryMapper();
	}

	@BeforeEach
	void setUpOxygenHistory() {
		mockedOxygenHistory = mock(OxygenHistory.class);

		List<OxygenTank> tanks = new ArrayList<>();
		OxygenTank tankA = mock(OxygenTank.class);
		when(tankA.getCategory()).thenReturn(CATEGORY_A);
		tanks.add(tankA);
		OxygenTank tankB = mock(OxygenTank.class);
		when(tankB.getCategory()).thenReturn(CATEGORY_B);
		tanks.add(tankB);
		OxygenTank tankE = mock(OxygenTank.class);
		when(tankE.getCategory()).thenReturn(CATEGORY_E);
		tanks.add(tankE);

		// when(mockedOxygenHistory.getProducedOxygenTanksForDate(any())).thenReturn(tanks);
		// Map<LocalDate, List<OxygenTank>> oxygenMap = new HashMap<>();
		// oxygenMap.put(date, tanks);
		// when(mockedOxygenHistory.getProducedOxygenTanks()).thenReturn(oxygenMap);
		// when(mockedOxygenHistory.getRequestedOxygenTanks()).thenReturn(oxygenMap);
	}

	@Test
	void toDto_shouldBuildWithCorrectOxygenHistoryItemDtoList() {

	}

	@Test
	void buildItemDto_shouldBuildDtoWithCorrectDate() {

	}

	@Test
	void buildItemDto_shouldBuildDtoWithCorrectQtyCandlesUsed() {

	}

	@Test
	void buildItemDto_shouldBuildDtoWithCorrectQtyOxygenTankBought() {

	}

	@Test
	void buildItemDto_shouldBuildDtoWithCorrectQtyOxygenTankMade() {

	}

	@Test
	void buildItemDto_shouldBuildDtoWithCorrectQtyWaterUsed() {

	}
}
