package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;

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

	// TODO : Remove this type of usage of History
	public OxygenHistory(Map<OxygenCategories, List<OxygenTank>> allTanks) {
		historyItems = new TreeMap<>();

		// TODO : Refactor this call
		allTanks.keySet().forEach(category -> buildHistoryItems(allTanks.get(category)));
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

	// TODO OXY : Maybe not bother with OxygenDate not sure if useful! anywhere
	private void buildHistoryItems(List<OxygenTank> allTanks) {
		allTanks.forEach(tank -> {
			LocalDate requestDate = tank.getRequestDate().getValue();
			LocalDate readyDate = tank.getReadyDate().getValue();

			createHistoryItemIfAbsent(requestDate);
			OxygenHistoryItem item = historyItems.get(requestDate);

			switch(tank.getCategory()) {
				case E:
					item.addTanksBought(OxygenTank.CATEGORY_E_NUMBER_OF_TANKS_CREATED);
					break;
				case B:
					// TODO OXY : Problem missing .6 period water hot fix with modulo not sure if working and seem to be domain logic
					item.addWaterUsed(2);

					if (item.getQtyWaterUsed() % 6 == 0 && item.getQtyWaterUsed() != 0) {
						item.addWaterUsed(2);
					}

					break;
				case A:
					item.addCandleUsed(3);
					break;
			}

			createHistoryItemIfAbsent(readyDate);
			item = historyItems.get(readyDate);

			if (tank.getCategory() == OxygenCategories.A || tank.getCategory() == OxygenCategories.B){
				item.addTanksMade(1);
			}
		});
	}
}