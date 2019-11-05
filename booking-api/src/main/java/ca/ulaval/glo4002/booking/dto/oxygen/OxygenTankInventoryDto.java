package ca.ulaval.glo4002.booking.dto.oxygen;

public class OxygenTankInventoryDto {
	// TODO : String and Long changed (were list). Check if its okay
	String oxygenCategories;
	Long tanksQuantity;

	public OxygenTankInventoryDto(String categories, Long tanksQuantity) {
		this.oxygenCategories = categories;
		this.tanksQuantity = tanksQuantity;
	}

	public String getCategories() {
		return oxygenCategories;
	}

	public Long getTanksQuantity() {
		return tanksQuantity;
	}

}
