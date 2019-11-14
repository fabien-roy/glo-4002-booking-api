package ca.ulaval.glo4002.booking.producers;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenHistory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.exceptions.oxygen.InvalidOxygenCategoryException;
import ca.ulaval.glo4002.booking.factories.OxygenFactory;
import ca.ulaval.glo4002.booking.repositories.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.repositories.OxygenInventoryRepository;

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
	
	public List<OxygenTank> produce(OxygenCategories category, LocalDate requestDate) {
		OxygenInventory inventory = inventoryRepository.getInventory();
		OxygenHistory history = historyRepository.getHistory(); // TODO : Do things with history

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
	
	private Integer getQuantityToCoverForCategory(OxygenCategories category) {
		if (category.equals(OxygenCategories.E)) {
			return 5;
		} else {
			return 3;
		}
	}

	// TODO : Refactor to use readyDateForCategory
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