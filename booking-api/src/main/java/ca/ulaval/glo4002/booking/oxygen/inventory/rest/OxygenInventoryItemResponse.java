package ca.ulaval.glo4002.booking.oxygen.inventory.rest;

public class OxygenInventoryItemResponse {

	private String gradeTankOxygen;
	private Long quantity;

	public OxygenInventoryItemResponse(String gradeTankOxygen, Long quantity) {
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
