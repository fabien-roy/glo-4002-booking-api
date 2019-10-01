package ca.ulaval.glo4002.booking.dto;

import java.time.LocalDate;

public class HistoryItemDto implements Dto {

	public LocalDate date;
	public Long qtyOxygenTankBought;
	public Long qtyWaterUsed;
	public Long qtyCandlesUsed;
	public Long qtyOxygenTankMade;
}
