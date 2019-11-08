package ca.ulaval.glo4002.booking.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenInventoryItemDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;

public class OxygenTankInventoryMapper {

	public List<OxygenInventoryItemDto> toDto(OxygenInventory inventory) {
		Long eOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.E).longValue();
		Long bOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.B).longValue();
		Long aOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.A).longValue();

		OxygenInventoryItemDto eGradeDto = new OxygenInventoryItemDto(OxygenCategories.E.toString(), eOxygenTankQuantity);
		OxygenInventoryItemDto bGradeDto = new OxygenInventoryItemDto(OxygenCategories.B.toString(), bOxygenTankQuantity);
		OxygenInventoryItemDto aGradeDto = new OxygenInventoryItemDto(OxygenCategories.A.toString(), aOxygenTankQuantity);

		return new ArrayList<>(Arrays.asList(eGradeDto, bGradeDto, aGradeDto));
	}
}
