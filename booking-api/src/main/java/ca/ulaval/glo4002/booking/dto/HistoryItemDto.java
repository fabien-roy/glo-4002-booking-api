package ca.ulaval.glo4002.booking.dto;

public class HistoryItemDto {
	public String date;
	public Long qtyOxygenTankBought;
	public Long qtyWaterUsed;
	public Long qtyCandlesUsed;
	public Long qtyOxygenTankMade;

	public HistoryItemDto(String date, Long qtyOxygenTankBought, Long qtyWaterUsed, Long qtyCandlesUsed,
			Long qtyOxygenTankMade) {
		this.date = date;
		this.qtyOxygenTankBought = qtyOxygenTankBought;
		this.qtyWaterUsed = qtyWaterUsed;
		this.qtyCandlesUsed = qtyCandlesUsed;
		this.qtyOxygenTankMade = qtyOxygenTankMade;
	}

	public String getDate() {
		return date;
	}

	public Long getQtyOxygenTankBought() {
		return qtyOxygenTankBought;
	}

	public Long getQtyWaterUsed() {
		return qtyWaterUsed;
	}

	public Long getQtyCandlesUsed() {
		return qtyCandlesUsed;
	}

	public Long getQtyOxygenTankMade() {
		return qtyOxygenTankMade;
	}

}
