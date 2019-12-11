package ca.ulaval.glo4002.booking.oxygen.history.domain;

import ca.ulaval.glo4002.booking.oxygen.domain.OxygenCategories;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class OxygenHistory {

	// TODO : Use OxygenDate in OxygenHistory
	private Map<LocalDate, OxygenHistoryItem> historyItems;

	public OxygenHistory() {
		historyItems = new TreeMap<>();
	}

	public Map<LocalDate, OxygenHistoryItem> getHistoryItems() {
	    return historyItems;
	}

	public void addCategoryProduction(LocalDate requestDate, OxygenCategories category, Integer numberOfUnitsUsed) {
		switch (category) {
			case A:
				addCandlesUsed(requestDate, numberOfUnitsUsed);
				break;
			case B:
				addWaterUsed(requestDate, numberOfUnitsUsed.doubleValue());
				break;
			default:
			case E:
				break;
		}
	}

	public void addTanksBought(LocalDate date, Integer amountOfTanksBought) {
		createHistoryItemIfAbsent(date);

		historyItems.get(date).addTanksBought(amountOfTanksBought);
	}

	public void addMadeTanks(LocalDate date, Integer amountOfTanksMade) {
		createHistoryItemIfAbsent(date);

		historyItems.get(date).addTanksMade(amountOfTanksMade);
	}

	public void addWaterUsed(LocalDate date, Double amountWaterUsed) {
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