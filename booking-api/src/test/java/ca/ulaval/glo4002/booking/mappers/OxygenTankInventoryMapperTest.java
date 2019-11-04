package ca.ulaval.glo4002.booking.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.OxygenTankInventoryDto;
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

	void toDto_shouldBuildDtoWithCorrectOxygenCategories() {
		List<OxygenTankInventoryDto> oxygenTankInventoryDto = subject.toDto(inventory);
		assertEquals(oxygenTankInventoryDto.get(0).getCategories(), OxygenCategories.E.toString());
		assertEquals(oxygenTankInventoryDto.get(1).getCategories(), OxygenCategories.B.toString());
		assertEquals(oxygenTankInventoryDto.get(2).getCategories(), OxygenCategories.A.toString());
	}

	void toDto_shouldBuildDtoWithCorrectTanksQuantity() {
		List<OxygenTankInventoryDto> oxygenTankInventoryDto = subject.toDto(inventory);

		Long dtoQuantityE = oxygenTankInventoryDto.get(0).getTanksQuantity();
		Long dtoQuantityB = oxygenTankInventoryDto.get(1).getTanksQuantity();
		Long dtoQuantityA = oxygenTankInventoryDto.get(2).getTanksQuantity();
		Integer inventoryQuantityE = inventory.getAllQuantityByCategory(OxygenCategories.E);
		Integer inventoryQuantityB = inventory.getAllQuantityByCategory(OxygenCategories.B);
		Integer inventoryQuantityA = inventory.getAllQuantityByCategory(OxygenCategories.A);

		assertEquals(dtoQuantityE, inventoryQuantityE.longValue());
		assertEquals(dtoQuantityB, inventoryQuantityB.longValue());
		assertEquals(dtoQuantityA, inventoryQuantityA.longValue());
	}
}
