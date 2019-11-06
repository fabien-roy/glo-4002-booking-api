package ca.ulaval.glo4002.booking.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.oxygen.OxygenTankInventoryDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;

public class OxygenTankInventoryMapper {

	public List<OxygenTankInventoryDto> toDto(OxygenTankInventory inventory) {
		Long eOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.E).longValue();
		Long bOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.B).longValue();
		Long aOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.A).longValue();

		OxygenTankInventoryDto eGradeDto = new OxygenTankInventoryDto(OxygenCategories.E.toString(),
				eOxygenTankQuantity);
		OxygenTankInventoryDto bGradeDto = new OxygenTankInventoryDto(OxygenCategories.B.toString(),
				bOxygenTankQuantity);
		OxygenTankInventoryDto aGradeDto = new OxygenTankInventoryDto(OxygenCategories.A.toString(),
				aOxygenTankQuantity);

		return new ArrayList<>(Arrays.asList(eGradeDto, bGradeDto, aGradeDto));
	}

}
