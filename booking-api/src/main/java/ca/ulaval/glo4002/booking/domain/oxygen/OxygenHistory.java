package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.enums.OxygenCategories;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OxygenHistory {

	private Map<LocalDate, OxygenHistoryItem> historyItems;

	public OxygenHistory() {
		this.historyItems = new TreeMap<>();
	}

	// TODO OXY : Not sure is usefull, maybe if we had persistance
	public OxygenHistory(Map<LocalDate, OxygenHistoryItem> historyItems) {
		this.historyItems = historyItems;
	}

	public void buildHistoryItem(List<OxygenTank> allTanks) {
		allTanks.forEach(tank -> {
			// TODO OXY : Maybe not bother with OxygenDate not sure if usefull! anywhere
			LocalDate requestDate = tank.getRequestDate().getValue();
			LocalDate readyDate = tank.getReadyDate().getValue();
			OxygenHistoryItem item;

			if(!historyItems.containsKey(requestDate)) {
				item = new OxygenHistoryItem(readyDate);
				historyItems.put(requestDate, item);
			} else {
				item = historyItems.get(requestDate);
			}

			switch(tank.getCategory()) {
				case E:
					item.addTankBought(OxygenTank.CATEGORY_E_NUMBER_OF_TANKS_CREATED);
					break;
				case B:
					// TODO OXY : Problem missing .6 period water hot fix with modulo not sure if working
					item.addWaterUsed(2);
					if(item.getQtyWaterUsed() % 6 == 0) {
						item.addWaterUsed(2);
					}
					break;
				case A:
					item.addCandleUsed(3);
					break;
			}

			if(!historyItems.containsKey(readyDate)) {
				item = new OxygenHistoryItem(readyDate);
				historyItems.put(readyDate, item);
			} else {
				item = historyItems.get(readyDate);
			}

			if(tank.getCategory() == OxygenCategories.A || tank.getCategory() == OxygenCategories.B){
				item.addTankMade(1);
			}

		});
	}

	public List<OxygenHistoryItem> returnSortedListByDate() {
		return historyItems.values().stream().collect(Collectors.toList());
	}
}