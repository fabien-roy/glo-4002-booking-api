package ca.ulaval.glo4002.booking.mappers;

import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.dto.OxygenTankInventoryDto;
import ca.ulaval.glo4002.booking.enums.OxygenCategory;

public class OxygenTankInventoryMapper {

	public List<OxygenTankInventoryDto> toDto(OxygenTankInventory inventory) {
		/*
		 * TODO Get oxygen category for every type, return List, test
		 */
		OxygenTankInventoryDto eGradeDto = new OxygenTankInventoryDto(OxygenCategory.E.toString(), 0L);
		OxygenTankInventoryDto bGradeDto = new OxygenTankInventoryDto(OxygenCategory.B.toString(), 0L);
		OxygenTankInventoryDto aGradeDto = new OxygenTankInventoryDto(OxygenCategory.A.toString(), 0L);

		return null;
	}

}
