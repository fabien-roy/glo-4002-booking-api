package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OxygenHistory {

	private Map<LocalDate, OxygenHistoryItem> historyItems;

	public OxygenHistory(Map<OxygenCategories, List<OxygenTank>> allTanks) {
		historyItems = new TreeMap<>();

		// TODO : Refactor this call
		allTanks.keySet().forEach(category -> buildHistoryItems(allTanks.get(category)));
	}

	public List<OxygenHistoryItem> getHistoryItems() {
		return new ArrayList<>(historyItems.values());
	}

	// TODO OXY : Maybe not bother with OxygenDate not sure if useful! anywhere
	private void buildHistoryItems(List<OxygenTank> allTanks) {
		allTanks.forEach(tank -> {
			LocalDate requestDate = tank.getRequestDate().getValue();
			LocalDate readyDate = tank.getReadyDate().getValue();
			OxygenHistoryItem item;

			if (historyItems.containsKey(requestDate)) {
				item = historyItems.get(requestDate);
			} else {
				item = new OxygenHistoryItem(requestDate);
				historyItems.put(requestDate, item);
			}

			switch(tank.getCategory()) {
				case E:
					item.addTankBought(OxygenTank.CATEGORY_E_NUMBER_OF_TANKS_CREATED);
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

			if (historyItems.containsKey(readyDate)) {
				item = historyItems.get(readyDate);
			} else {
				item = new OxygenHistoryItem(readyDate);
				historyItems.put(readyDate, item);
			}

			if (tank.getCategory() == OxygenCategories.A || tank.getCategory() == OxygenCategories.B){
				item.addTankMade(1);
			}
		});
	}

	private void addMaterialUsed(OxygenTank tank) {
		LocalDate requestDate = tank.getRequestDate().getValue();
		OxygenHistoryItem item;

		if (historyItems.containsKey(requestDate)) {
			item = historyItems.get(requestDate);
		} else {
			item = new OxygenHistoryItem(requestDate);
			historyItems.put(requestDate, item);
		}

		switch(tank.getCategory()) {
			case E:
				item.addTankBought(OxygenTank.CATEGORY_E_NUMBER_OF_TANKS_CREATED);
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
	}

	private void addTanksCreated() {

	}
}