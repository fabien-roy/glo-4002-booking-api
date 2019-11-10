package ca.ulaval.glo4002.booking.domain.oxygen;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class OxygenHistory {

	private Map<LocalDate, OxygenHistoryItem> historyItems;

	public OxygenHistory() {
		this.historyItems = new HashMap<>();
	}

	public OxygenHistory(Map<LocalDate, OxygenHistoryItem> historyItems) {
		this.historyItems = historyItems;
	}

}