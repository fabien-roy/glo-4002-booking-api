package ca.ulaval.glo4002.booking.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenTankInventoryItemDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;

public class OxygenTankInventoryMapper {

	public List<OxygenTankInventoryItemDto> toDto(OxygenInventory inventory) {
		Long eOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.E).longValue();
		Long bOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.B).longValue();
		Long aOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.A).longValue();

		OxygenTankInventoryItemDto eGradeDto = new OxygenTankInventoryItemDto(OxygenCategories.E.toString(), eOxygenTankQuantity);
		OxygenTankInventoryItemDto bGradeDto = new OxygenTankInventoryItemDto(OxygenCategories.B.toString(), bOxygenTankQuantity);
		OxygenTankInventoryItemDto aGradeDto = new OxygenTankInventoryItemDto(OxygenCategories.A.toString(), aOxygenTankQuantity);

		return new ArrayList<>(Arrays.asList(eGradeDto, bGradeDto, aGradeDto));
	}
}
