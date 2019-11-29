package ca.ulaval.glo4002.booking.oxygen.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;

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

	public List<OxygenTank> produceOxygenForOrder(OxygenCategories requestedCategory, LocalDate requestDate) {
		OxygenInventory inventory = inventoryRepository.getInventory();
		OxygenHistory history = historyRepository.getHistory();
		PassCategories passCategories = convertOxygenCategoryToPassCategories(requestedCategory);
		OxygenCategory requestCategory = factory.createCategory(passCategories);
		OxygenCategory actualOxygenCategory = factory.createCategoryForRequestDate(requestDate, requestedCategory);
		OxygenCategories category = actualOxygenCategory.getCategory();

		List<OxygenTank> newTanks = new ArrayList<>();
		Integer quantityToCover = requestCategory.getTanksNeededPerDay();

		quantityToCover = inventory.requestTankByCategory(requestedCategory, actualOxygenCategory.getCategory(), quantityToCover);

		if (quantityToCover > 0) {
			List<OxygenTank> producedTanks = factory.createOxygenTank(actualOxygenCategory, requestDate, quantityToCover);
			newTanks.addAll(producedTanks);

			if (category == OxygenCategories.E) {
				history.addTanksBought(requestDate, quantityToCover);
			} else {
				history.addMadeTanks(actualOxygenCategory.calculateReadyDateForCategory(requestDate).getValue(), producedTanks.size());
				actualOxygenCategory.addCategoryProductionInformationToHistory(requestDate, history, producedTanks.size());
			}

			inventory.addTanksToInventory(category, newTanks);
			inventory.requestTankByCategory(category, category, quantityToCover);
		}

		inventoryRepository.setInventory(inventory);
		historyRepository.setHistory(history);

		return newTanks;
	}

	public List<OxygenTank> produceOxygenByQuantity(OxygenCategory oxygenCategory, LocalDate requestDate,
			Integer numberOfTanks) {
		OxygenInventory inventory = inventoryRepository.getInventory();
		OxygenHistory history = historyRepository.getHistory();
		List<OxygenTank> newTanks = new ArrayList<>();
		OxygenCategories category = oxygenCategory.getCategory();

		Integer quantityToCover = inventory.requestTankByCategory(category, category, numberOfTanks);

		if (quantityToCover > 0) {
			LocalDate readyDate = oxygenCategory.calculateReadyDateForCategory(requestDate).getValue();
			newTanks = factory.createOxygenTank(oxygenCategory, requestDate, quantityToCover);

			if (category == OxygenCategories.E) {
				history.addTanksBought(readyDate, quantityToCover);
			} else {
				history.addMadeTanks(readyDate, quantityToCover);
				oxygenCategory.addCategoryProductionInformationToHistory(requestDate, history, quantityToCover);
			}

			inventory.requestTankByCategory(category, category, quantityToCover);
		}

		inventoryRepository.setInventory(inventory);
		historyRepository.setHistory(history);

		return newTanks;
	}

	private PassCategories convertOxygenCategoryToPassCategories(OxygenCategories oxygenCategories) {
		PassCategories passCategories;

		switch (oxygenCategories) {
		case E:
			passCategories = PassCategories.SUPERNOVA;
			break;
		case B:
			passCategories = PassCategories.SUPERGIANT;
			break;
		default:
		case A:
			passCategories = PassCategories.NEBULA;
			break;
		}

		return passCategories;
	}
}