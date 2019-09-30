package ca.ulaval.glo4002.booking.dto;

import java.util.ArrayList;
import java.util.List;

public class O2ReportDto implements Dto {
	
	public List<InventoryItemDto> inventory = new ArrayList<>();
	public List<HistoryItemDto> history = new ArrayList<>();
}
