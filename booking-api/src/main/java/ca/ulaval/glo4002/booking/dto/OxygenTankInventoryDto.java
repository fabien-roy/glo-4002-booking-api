package ca.ulaval.glo4002.booking.dto;

import java.util.List;

public class OxygenTankInventoryDto {
	// TODO : OXY : not sure how we implements this, same as v1 ? With InventoryItemDto ??
	List<String> oxygenCategories;
	List<Long> tanksQuantity;

	public OxygenTankInventoryDto(List<String> categories, List<Long> tanksQuantity) {
		this.oxygenCategories = categories;
		this.tanksQuantity = tanksQuantity;
	}

	public List<String> getCategories() {
		return oxygenCategories;
	}

	public List<Long> getTanksQuantity() {
		return tanksQuantity;
	}

}
