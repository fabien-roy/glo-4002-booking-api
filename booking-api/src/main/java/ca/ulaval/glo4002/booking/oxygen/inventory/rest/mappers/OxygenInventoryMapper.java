package ca.ulaval.glo4002.booking.oxygen.inventory.rest.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.OxygenInventoryItemResponse;

public class OxygenInventoryMapper {

	public List<OxygenInventoryItemResponse> toResponse(OxygenInventory inventory) {
		Long eOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.E).longValue();
		Long bOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.B).longValue();
		Long aOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.A).longValue();

		OxygenInventoryItemResponse eGradeDto = new OxygenInventoryItemResponse(OxygenCategories.E.toString(), eOxygenTankQuantity);
		OxygenInventoryItemResponse bGradeDto = new OxygenInventoryItemResponse(OxygenCategories.B.toString(), bOxygenTankQuantity);
		OxygenInventoryItemResponse aGradeDto = new OxygenInventoryItemResponse(OxygenCategories.A.toString(), aOxygenTankQuantity);

		return new ArrayList<>(Arrays.asList(eGradeDto, bGradeDto, aGradeDto));
	}
}
