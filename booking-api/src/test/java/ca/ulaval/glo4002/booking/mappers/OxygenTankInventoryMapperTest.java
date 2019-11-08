package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenTankInventoryDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;

public class OxygenTankInventoryMapperTest {

	private OxygenTankInventoryMapper subject;
	private OxygenTankInventory inventory;

	@BeforeEach
	void setUpSubject() {
		subject = new OxygenTankInventoryMapper();
	}

	@BeforeEach
	void setUpInventory() {
		inventory = mock(OxygenTankInventory.class);
		OxygenTank tank = mock(OxygenTank.class);
		when(tank.getCategory()).thenReturn(OxygenCategories.A);
		List<OxygenTank> newTanks = new ArrayList<>();
		newTanks.add(tank);
		inventory.addTanksToInventory(OxygenCategories.A, newTanks);
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectOxygenCategoriesOrder() {
		List<OxygenTankInventoryDto> oxygenTankInventoryDto = subject.toDto(inventory);

		assertEquals(oxygenTankInventoryDto.get(0).getCategories(), OxygenCategories.E.toString());
		assertEquals(oxygenTankInventoryDto.get(1).getCategories(), OxygenCategories.B.toString());
		assertEquals(oxygenTankInventoryDto.get(2).getCategories(), OxygenCategories.A.toString());
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectTanksQuantityE() {
		Integer expectedQuantityE = inventory.getAllQuantityByCategory(OxygenCategories.E);

		List<OxygenTankInventoryDto> oxygenTankInventoryDto = subject.toDto(inventory);
		Long quantityE = oxygenTankInventoryDto.get(0).getTanksQuantity();

		assertEquals(expectedQuantityE.longValue(), quantityE);
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectTanksQuantityB() {
		Integer expectedQuantityB = inventory.getAllQuantityByCategory(OxygenCategories.B);

		List<OxygenTankInventoryDto> oxygenTankInventoryDto = subject.toDto(inventory);
		Long quantityB = oxygenTankInventoryDto.get(1).getTanksQuantity();

		assertEquals(expectedQuantityB.longValue(), quantityB);
	}

	@Test
	void toDto_shouldBuildDtoWithCorrectTanksQuantityA() {
		Integer expectedQuantityA = inventory.getAllQuantityByCategory(OxygenCategories.A);

		List<OxygenTankInventoryDto> oxygenTankInventoryDto = subject.toDto(inventory);
		Long quantityA = oxygenTankInventoryDto.get(2).getTanksQuantity();

		assertEquals(expectedQuantityA.longValue(), quantityA);
	}
}
