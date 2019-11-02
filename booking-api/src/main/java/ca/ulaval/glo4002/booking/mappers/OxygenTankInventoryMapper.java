package ca.ulaval.glo4002.booking.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.OxygenTankInventoryDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategory;

public class OxygenTankInventoryMapper {

	public List<OxygenTankInventoryDto> toDto(OxygenTankInventory inventory) {
		// TODO test this
		Long eOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategory.E).longValue();
		Long bOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategory.B).longValue();
		Long aOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategory.A).longValue();

		OxygenTankInventoryDto eGradeDto = new OxygenTankInventoryDto(OxygenCategory.E.toString(), eOxygenTankQuantity);
		OxygenTankInventoryDto bGradeDto = new OxygenTankInventoryDto(OxygenCategory.B.toString(), bOxygenTankQuantity);
		OxygenTankInventoryDto aGradeDto = new OxygenTankInventoryDto(OxygenCategory.A.toString(), aOxygenTankQuantity);

		return new ArrayList<>(Arrays.asList(eGradeDto, bGradeDto, aGradeDto));
	}

}
