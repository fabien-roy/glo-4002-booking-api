package ca.ulaval.glo4002.booking.domain.oxygen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OxygenHistory {

	private Map<LocalDate, OxygenHistoryItem> historyItems;

	public OxygenHistory() {
		historyItems = new TreeMap<>();
	}

	public List<OxygenHistoryItem> getHistoryItems() {
		return new ArrayList<>(historyItems.values());
	}

	public void addTanksBought(LocalDate date, Integer amountOfTanksBought) {
	    createHistoryItemIfAbsent(date);

		historyItems.get(date).addTanksBought(amountOfTanksBought);
	}

	public void addMadeTanks(LocalDate date, Integer amountOfTanksMade) {
		createHistoryItemIfAbsent(date);

		historyItems.get(date).addTanksMade(amountOfTanksMade);
	}

	public void addWaterUsed(LocalDate date, Integer amountWaterUsed) {
		createHistoryItemIfAbsent(date);

		historyItems.get(date).addWaterUsed(amountWaterUsed);
	}

	public void addCandlesUsed(LocalDate date, Integer amountCandlesUsed) {
		createHistoryItemIfAbsent(date);

		historyItems.get(date).addCandleUsed(amountCandlesUsed);
	}

	private void createHistoryItemIfAbsent(LocalDate date) {
		if (!historyItems.containsKey(date)) {
			historyItems.put(date, new OxygenHistoryItem());
		}
	}
}