package ca.ulaval.glo4002.booking.dto.oxygen;

public class OxygenTankInventoryItemDto {

	String gradeTankOxygen;
	Long quantity;

	public OxygenTankInventoryItemDto(String gradeTankOxygen, Long quantity) {
		this.gradeTankOxygen = gradeTankOxygen;
		this.quantity = quantity;
	}

	public String getGradeTankOxygen() {
		return gradeTankOxygen;
	}

	public Long getQuantity() {
		return quantity;
	}

}
