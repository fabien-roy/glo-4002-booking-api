package ca.ulaval.glo4002.booking.dto.oxygen;

public class OxygenInventoryItemDto {

	private String gradeTankOxygen;
	private Long quantity;

	public OxygenInventoryItemDto(String gradeTankOxygen, Long quantity) {
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
