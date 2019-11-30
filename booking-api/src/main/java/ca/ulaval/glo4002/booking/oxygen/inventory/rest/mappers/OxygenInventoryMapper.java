package ca.ulaval.glo4002.booking.oxygen.inventory.rest.mappers;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.rest.OxygenInventoryItemResponse;

import java.util.ArrayList;
import java.util.List;

public class OxygenInventoryMapper {

    // TODO : Test that item is not there when quantity is 0

	public List<OxygenInventoryItemResponse> toResponse(OxygenInventory inventory) {
		int eOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.E);
		int bOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.B);
		int aOxygenTankQuantity = inventory.getAllQuantityByCategory(OxygenCategories.A);

		List<OxygenInventoryItemResponse> responses = new ArrayList<>();
		if (eOxygenTankQuantity > 0) responses.add(new OxygenInventoryItemResponse(OxygenCategories.E.toString(), eOxygenTankQuantity));
		if (bOxygenTankQuantity > 0) responses.add(new OxygenInventoryItemResponse(OxygenCategories.B.toString(), bOxygenTankQuantity));
		if (aOxygenTankQuantity > 0) responses.add(new OxygenInventoryItemResponse(OxygenCategories.A.toString(), aOxygenTankQuantity));

		return responses;
	}
}
