package ca.ulaval.glo4002.booking.oxygen;

import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.passes.PassCategories;

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
		PassCategories passCategories = convertOxygenCategoryToPassCategories(category);
		OxygenCategory requestCategory = factory.buildCategory(passCategories);
		OxygenCategory actualOxygenCategory = factory.buildCategoryForRequestDate(requestDate, category);

		List<OxygenTank> newTanks = new ArrayList<>();
		Integer quantityToCover = requestCategory.getTanksNeededPerDay();

		quantityToCover = inventory.requestTankByCategory(category, actualOxygenCategory.getCategory(), quantityToCover);
		Integer quantityToReserve = quantityToCover;

		if (quantityToCover > 0) {
		    List<OxygenTank> producedTanks = factory.buildOxygenTank(actualOxygenCategory, requestDate, quantityToCover);
			newTanks.addAll(producedTanks);

			if(actualOxygenCategory.getCategory() == OxygenCategories.E) {
				history.addTanksBought(requestDate, quantityToCover);
			} else {
				history.addMadeTanks(actualOxygenCategory.calculateReadyDateForCategory(requestDate).getValue(), producedTanks.size());
				actualOxygenCategory.addCategoryProductionInformationToHistory(requestDate, history, producedTanks.size());
			}
            OxygenCategories categoryToReserve = actualOxygenCategory.getCategory();
			inventory.addTanksToInventory(category, newTanks);
			inventory.requestTankByCategory(categoryToReserve, categoryToReserve, quantityToReserve);
		}

		inventoryRepository.setInventory(inventory);
		historyRepository.setHistory(history);

		return newTanks;
	}

	// TODO Move somewhere else
	private PassCategories convertOxygenCategoryToPassCategories(OxygenCategories oxygenCategories) {
		PassCategories passCategories = PassCategories.SUPERNOVA;

		switch (oxygenCategories) {
			case E:
				passCategories = PassCategories.SUPERNOVA;
				break;
			case B:
				passCategories = PassCategories.SUPERGIANT;
				break;
			case A:
				passCategories = PassCategories.NEBULA;
				break;
			default:
				break;
		}

		return passCategories;
	}

}