package ca.ulaval.glo4002.booking.oxygen.inventory.rest;

public class OxygenInventoryItemResponse {

	private String gradeTankOxygen;
	private int quantity;

	public OxygenInventoryItemResponse(String gradeTankOxygen, int quantity) {
		this.gradeTankOxygen = gradeTankOxygen;
		this.quantity = quantity;
	}

	public String getGradeTankOxygen() {
		return gradeTankOxygen;
	}

	public int getQuantity() {
		return quantity;
	}
}
