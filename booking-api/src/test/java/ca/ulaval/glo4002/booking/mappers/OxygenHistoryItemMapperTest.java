package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistoryItem;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenHistoryItemDto;

class OxygenHistoryItemMapperTest {

	private OxygenHistoryItemMapper oxygenHistoryItemMapper;
	private OxygenHistoryItem mockedHistoryItem;

	@BeforeEach()
	void setupHistoryItemMapper() {
		oxygenHistoryItemMapper = new OxygenHistoryItemMapper();
	}

	@BeforeEach()
	void setupHistoryItem() {
		mockedHistoryItem = mock(OxygenHistoryItem.class);
		when(mockedHistoryItem.getDate()).thenReturn(LocalDate.of(2050, 7, 1));
		when(mockedHistoryItem.getQtyCandlesUsed()).thenReturn(15);
		when(mockedHistoryItem.getQtyOxygenTankBought()).thenReturn(1);
		when(mockedHistoryItem.getQtyOxygenTankMade()).thenReturn(9);
		when(mockedHistoryItem.getQtyWaterUsed()).thenReturn(8);
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectDate() {
		OxygenHistoryItemDto dto = oxygenHistoryItemMapper.toDto(mockedHistoryItem);

		assertEquals(dto.getDate(), mockedHistoryItem.getDate().toString());
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectQtyCandlesUsed() {
		OxygenHistoryItemDto dto = oxygenHistoryItemMapper.toDto(mockedHistoryItem);

		assertEquals(dto.getQtyCandlesUsed(), mockedHistoryItem.getQtyCandlesUsed());
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectQtyOxygenTankBought() {
		OxygenHistoryItemDto dto = oxygenHistoryItemMapper.toDto(mockedHistoryItem);

		assertEquals(dto.getQtyOxygenTankBought(), mockedHistoryItem.getQtyOxygenTankBought());
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectQtyOxygenTankMade() {
		OxygenHistoryItemDto dto = oxygenHistoryItemMapper.toDto(mockedHistoryItem);

		assertEquals(dto.getQtyOxygenTankMade(), mockedHistoryItem.getQtyOxygenTankMade());
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectQtyWaterUsed() {
		OxygenHistoryItemDto dto = oxygenHistoryItemMapper.toDto(mockedHistoryItem);

		assertEquals(dto.getQtyWaterUsed(), mockedHistoryItem.getQtyWaterUsed());
	}

}
