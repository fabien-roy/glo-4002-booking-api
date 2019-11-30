package ca.ulaval.glo4002.booking.oxygen.history.rest;

public class OxygenHistoryItemResponse {

	private String date;
	private int qtyOxygenTankBought;
	private int qtyWaterUsed;
	private int qtyCandlesUsed;
	private int qtyOxygenTankMade;

	public OxygenHistoryItemResponse(String date, int qtyOxygenTankBought, int qtyWaterUsed, int qtyCandlesUsed, int qtyOxygenTankMade) {
		this.date = date;
		this.qtyOxygenTankBought = qtyOxygenTankBought;
		this.qtyWaterUsed = qtyWaterUsed;
		this.qtyCandlesUsed = qtyCandlesUsed;
		this.qtyOxygenTankMade = qtyOxygenTankMade;
	}

	public String getDate() {
		return date;
	}

	public int getQtyOxygenTankBought() {
		return qtyOxygenTankBought;
	}

	public int getQtyWaterUsed() {
		return qtyWaterUsed;
	}

	public int getQtyCandlesUsed() {
		return qtyCandlesUsed;
	}

	public int getQtyOxygenTankMade() {
		return qtyOxygenTankMade;
	}
}