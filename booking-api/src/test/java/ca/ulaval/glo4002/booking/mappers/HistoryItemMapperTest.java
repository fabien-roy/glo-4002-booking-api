package ca.ulaval.glo4002.booking.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;

class HistoryItemMapperTest {

	HistoryItemMapper mapper;
	OxygenTankInventory mockedInventory;

	@BeforeEach
	void setUpMapper() {
		mapper = new HistoryItemMapper();
		mockedInventory = mock(OxygenTankInventory.class);
	}

	@Test
	void toDto_shouldBuildWithCorrectDate() {
		
	}

	@Test
	void toDto_shouldBuildWithCorrectQtyOxygenTankBought() {

	}

	@Test
	void toDto_shouldBuildWithCorrectQtyWaterUsed() {

	}

	@Test
	void toDto_shouldBuildWithCorrectQtyCandlesUsed() {

	}

	@Test
	void toDto_shouldBuildWithCorrectQtyOxygenTankMade() {

	}
	
	private void fillInventory(OxygenTankInventory mockedInventory) {
		
	}
}
