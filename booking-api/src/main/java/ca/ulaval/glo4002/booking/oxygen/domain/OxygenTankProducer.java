package ca.ulaval.glo4002.booking.oxygen.domain;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventoryRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenTankProducer {

	// TODO : Completely rebuild OxygenTankProducer

	private OxygenInventoryRepository inventoryRepository;
	private OxygenHistoryRepository historyRepository;
	private OxygenFactory factory;

	@Inject
	public OxygenTankProducer(OxygenInventoryRepository inventoryRepository, OxygenHistoryRepository historyRepository, OxygenFactory factory) {
		this.inventoryRepository = inventoryRepository;
		this.historyRepository = historyRepository;
		this.factory = factory;
	}

	public List<OxygenTank> produceOxygenForOrder(OxygenCategories requestedCategory, LocalDate requestedDate) {
		OxygenInventory inventory = inventoryRepository.getInventory();
		OxygenHistory history = historyRepository.getHistory();
		OxygenProduction requestedProduction = factory.createProduction(requestedCategory);
		OxygenProduction actualProduction = factory.createProductionForRequestDate(requestedDate, requestedCategory);
		OxygenCategories actualCategory = actualProduction.getCategory();

		List<OxygenTank> newTanks = new ArrayList<>();
		Integer quantityToCover = requestedProduction.getTanksNeededPerDay();

		quantityToCover = inventory.assignTanksByCategory(requestedCategory, actualCategory, quantityToCover);

		if (quantityToCover > 0) {
			List<OxygenTank> producedTanks = factory.createOxygenTank(actualProduction, quantityToCover);
			newTanks.addAll(producedTanks);

			if (actualCategory == OxygenCategories.E) {
				history.addTanksBought(requestedDate, quantityToCover);
			} else {
				history.addMadeTanks(actualProduction.calculateReadyDateForCategory(requestedDate), producedTanks.size());
				actualProduction.addCategoryProductionInformationToHistory(requestedDate, history, producedTanks.size());
			}

			inventory.addTanksToInventory(actualCategory, newTanks);
			inventory.assignTanksByCategory(actualCategory, actualCategory, quantityToCover);
		}

		inventoryRepository.updateInventory(inventory);
		historyRepository.updateHistory(history);

		return newTanks;
	}

	public List<OxygenTank> produceOxygenByQuantity(OxygenCategories category, LocalDate requestDate, Integer numberOfTanks) {
	    OxygenProduction production = factory.createProduction(category);
		OxygenInventory inventory = inventoryRepository.getInventory();
		OxygenHistory history = historyRepository.getHistory();
		List<OxygenTank> newTanks = new ArrayList<>();

		Integer quantityToCover = inventory.assignTanksByCategory(category, category, numberOfTanks);

		if (quantityToCover > 0) {
			LocalDate readyDate = production.calculateReadyDateForCategory(requestDate);
			newTanks = factory.createOxygenTank(production, quantityToCover);

			if (category == OxygenCategories.E) {
				history.addTanksBought(readyDate, quantityToCover);
			} else {
				history.addMadeTanks(readyDate, quantityToCover);
				production.addCategoryProductionInformationToHistory(requestDate, history, quantityToCover);
			}

			inventory.assignTanksByCategory(category, category, quantityToCover);
		}

		inventoryRepository.updateInventory(inventory);
		historyRepository.updateHistory(history);

		return newTanks;
	}
}