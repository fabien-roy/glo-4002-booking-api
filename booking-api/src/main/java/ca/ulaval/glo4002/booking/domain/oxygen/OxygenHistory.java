package ca.ulaval.glo4002.booking.domain.oxygen;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OxygenHistory {

	private Map<LocalDate, OxygenHistoryItem> historyItems;

	public OxygenHistory() {
		this.historyItems = new HashMap<>();
	}

	// TODO OXY : Not sure is usefull, maybe if we had persistance
	public OxygenHistory(Map<LocalDate, OxygenHistoryItem> historyItems) {
		this.historyItems = historyItems;
	}

	public void buildHistory(List<OxygenTank> allTanks) {
		allTanks.forEach(oxygenTank -> {
			// TODO OXY : Maybe not bother with OxygenDate not sure if usefull! anywhere
			LocalDate requestDate = oxygenTank.getRequestDate().getValue();
			LocalDate readyDate = oxygenTank.getReadyDate().getValue();

			if(!historyItems.containsKey(requestDate)) {
				historyItems.put(requestDate, new OxygenHistoryItem());
			}
			if(!historyItems.containsKey(readyDate)) {
				historyItems.put(readyDate, new OxygenHistoryItem());
			}
		});
	}

}