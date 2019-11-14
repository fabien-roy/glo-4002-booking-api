package ca.ulaval.glo4002.booking.oxygen;

import ca.ulaval.glo4002.booking.events.EventDate;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenTankProducer {

	// TODO : Test calls to history

    private OxygenInventoryRepository inventoryRepository;
    private OxygenHistoryRepository historyRepository;
	private OxygenFactory factory;

	@Inject
	public OxygenTankProducer(OxygenInventoryRepository inventoryRepository, OxygenHistoryRepository historyRepository, OxygenFactory factory) {
	    this.inventoryRepository = inventoryRepository;
	    this.historyRepository = historyRepository;
		this.factory = factory;
	}

	public List<OxygenTank> produceForDay(OxygenCategories category, LocalDate requestDate) {
		OxygenInventory inventory = inventoryRepository.getInventory();
		OxygenHistory history = historyRepository.getHistory();

		List<OxygenTank> newTanks = new ArrayList<>();
		Integer quantityToCover = getQuantityToCoverForCategory(category);
		OxygenCategories possibleCategory;

		if (quantityToCover > inventory.getNotInUseQuantityByCategory(category)) {
			possibleCategory = getCategoryForRequestDate(category, requestDate);
			quantityToCover = inventory.requestTankByCategory(category, possibleCategory, quantityToCover);
		} else {
			possibleCategory = category;
			quantityToCover = inventory.requestTankByCategory(category, category, quantityToCover);
		}

		if (quantityToCover > 0) {
		    // TODO : Add water and candles used to history

		    List<OxygenTank> producedTanks = factory.buildOxygenTank(possibleCategory, requestDate, quantityToCover);
			newTanks.addAll(producedTanks);

			history.addMadeTanks(getReadyDateForCategory(possibleCategory, requestDate), producedTanks.size());
			inventory.addTanksToInventory(category, newTanks);
		}

		history.addTanksBought(requestDate, quantityToCover);

		inventoryRepository.setInventory(inventory);
		historyRepository.setHistory(history);

		return newTanks;
	}

	// TODO : Refactor to use OxygenCategory.getTanksNeededPerDay()
	private Integer getQuantityToCoverForCategory(OxygenCategories category) {
		if (category.equals(OxygenCategories.E)) {
			return 5;
		} else {
			return 3;
		}
	}

	// TODO : Refactor to use OxygenCategory.getReadyDate(requestDate)
	private OxygenCategories getCategoryForRequestDate(OxygenCategories category, LocalDate requestDate) {
		LocalDate readyBeforeDate = EventDate.START_DATE.plusDays(1);

		switch (category) {
            case A:
                if (requestDate.plusDays(20).isBefore(readyBeforeDate)) {
                    return OxygenCategories.A;
                } else if (requestDate.plusDays(10).isBefore(readyBeforeDate)) {
                    return OxygenCategories.B;
                } else {
                    return OxygenCategories.E;
                }
            case B:
                if (requestDate.plusDays(10).isBefore(readyBeforeDate)) {
                    return OxygenCategories.B;
                } else {
                    return OxygenCategories.E;
                }
			default:
			case E:
                return OxygenCategories.E;
        }
	}

	// TODO : Refactor to use OxygenCategory.getReadyDate(requestDate)
	private LocalDate getReadyDateForCategory(OxygenCategories category, LocalDate requestDate) {
		switch (category) {
			case A:
				return requestDate.plusDays(20);
			case B:
				return requestDate.plusDays(10);
			default:
			case E:
				return requestDate;
		}
	}
}