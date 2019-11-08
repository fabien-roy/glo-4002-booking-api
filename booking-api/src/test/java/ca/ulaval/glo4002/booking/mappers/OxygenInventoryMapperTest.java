package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenInventoryItemDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;

public class OxygenInventoryMapperTest {

	private OxygenTankInventoryMapper subject;
	private OxygenInventory inventory;

	@BeforeEach
	void setUpSubject() {
		subject = new OxygenTankInventoryMapper();
	}

	@BeforeEach
	void setUpInventory() {
		inventory = mock(OxygenInventory.class);
		OxygenTank tank = mock(OxygenTank.class);
		when(tank.getCategory()).thenReturn(OxygenCategories.A);
		List<OxygenTank> newTanks = new ArrayList<>();
		newTanks.add(tank);
		inventory.addTanksToInventory(OxygenCategories.A, newTanks);
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectOxygenCategoriesOrder() {
		List<OxygenInventoryItemDto> oxygenInventoryItemDto = subject.toDto(inventory);

		assertEquals(oxygenInventoryItemDto.get(0).getGradeTankOxygen(), OxygenCategories.E.toString());
		assertEquals(oxygenInventoryItemDto.get(1).getGradeTankOxygen(), OxygenCategories.B.toString());
		assertEquals(oxygenInventoryItemDto.get(2).getGradeTankOxygen(), OxygenCategories.A.toString());
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectTanksQuantityE() {
		Integer expectedQuantityE = inventory.getAllQuantityByCategory(OxygenCategories.E);

		List<OxygenInventoryItemDto> oxygenInventoryItemDto = subject.toDto(inventory);
		Long quantityE = oxygenInventoryItemDto.get(0).getQuantity();

		assertEquals(expectedQuantityE.longValue(), quantityE);
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectTanksQuantityB() {
		Integer expectedQuantityB = inventory.getAllQuantityByCategory(OxygenCategories.B);

		List<OxygenInventoryItemDto> oxygenInventoryItemDto = subject.toDto(inventory);
		Long quantityB = oxygenInventoryItemDto.get(1).getQuantity();

		assertEquals(expectedQuantityB.longValue(), quantityB);
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectTanksQuantityA() {
		Integer expectedQuantityA = inventory.getAllQuantityByCategory(OxygenCategories.A);

		List<OxygenInventoryItemDto> oxygenInventoryItemDto = subject.toDto(inventory);
		Long quantityA = oxygenInventoryItemDto.get(2).getQuantity();

		assertEquals(expectedQuantityA.longValue(), quantityA);
	}
}
