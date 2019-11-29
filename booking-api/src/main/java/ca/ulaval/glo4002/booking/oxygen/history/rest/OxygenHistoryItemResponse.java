package ca.ulaval.glo4002.booking.oxygen.history.rest;

public class OxygenHistoryItemResponse {

	private String date;
	private Integer qtyOxygenTankBought;
	private Integer qtyWaterUsed;
	private Integer qtyCandlesUsed;
	private Integer qtyOxygenTankMade;

	public OxygenHistoryItemResponse(String date, Integer qtyOxygenTankBought, Integer qtyWaterUsed, Integer qtyCandlesUsed, Integer qtyOxygenTankMade) {
		this.date = date;
		this.qtyOxygenTankBought = qtyOxygenTankBought;
		this.qtyWaterUsed = qtyWaterUsed;
		this.qtyCandlesUsed = qtyCandlesUsed;
		this.qtyOxygenTankMade = qtyOxygenTankMade;
	}

	public String getDate() {
		return date;
	}

	public Integer getQtyOxygenTankBought() {
		return qtyOxygenTankBought;
	}

	public Integer getQtyWaterUsed() {
		return qtyWaterUsed;
	}

	public Integer getQtyCandlesUsed() {
		return qtyCandlesUsed;
	}

	public Integer getQtyOxygenTankMade() {
		return qtyOxygenTankMade;
	}

}