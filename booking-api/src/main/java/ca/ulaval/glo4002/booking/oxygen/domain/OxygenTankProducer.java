package ca.ulaval.glo4002.booking.oxygen.domain;

import ca.ulaval.glo4002.booking.oxygen.history.domain.OxygenHistory;
import ca.ulaval.glo4002.booking.oxygen.history.infrastructure.OxygenHistoryRepository;
import ca.ulaval.glo4002.booking.oxygen.inventory.domain.OxygenInventory;
import ca.ulaval.glo4002.booking.oxygen.inventory.infrastructure.OxygenInventoryRepository;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;

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

	public List<OxygenTank> produceOxygenForOrder(OxygenCategories requestedCategory, LocalDate requestDate) {
		OxygenInventory inventory = inventoryRepository.getInventory();
		OxygenHistory history = historyRepository.getHistory();
		PassCategories passCategories = convertOxygenCategoryToPassCategories(requestedCategory);
		OxygenProduction requestCategory = factory.createCategory(passCategories);
		OxygenProduction actualOxygenProduction = factory.createCategoryForRequestDate(requestDate, requestedCategory);
		OxygenCategories category = actualOxygenProduction.getCategory();

		List<OxygenTank> newTanks = new ArrayList<>();
		Integer quantityToCover = requestCategory.getTanksNeededPerDay();

		quantityToCover = inventory.requestTankByCategory(requestedCategory, actualOxygenProduction.getCategory(), quantityToCover);

		if (quantityToCover > 0) {
			List<OxygenTank> producedTanks = factory.createOxygenTank(actualOxygenProduction, requestDate, quantityToCover);
			newTanks.addAll(producedTanks);

			if (category == OxygenCategories.E) {
				history.addTanksBought(requestDate, quantityToCover);
			} else {
				history.addMadeTanks(actualOxygenProduction.calculateReadyDateForCategory(requestDate).getValue(), producedTanks.size());
				history.addCategoryProduction(requestDate, actualOxygenProduction.getCategory(), actualOxygenProduction.getNumberOfUnitsUsed()); // TODO : Test this call
			}

			inventory.addTanksToInventory(category, newTanks);
			inventory.requestTankByCategory(category, category, quantityToCover);
		}

		inventoryRepository.setInventory(inventory);
		historyRepository.setHistory(history);

		return newTanks;
	}

	public List<OxygenTank> produceOxygenByQuantity(OxygenProduction oxygenProduction, LocalDate requestDate,
													Integer numberOfTanks) {
		OxygenInventory inventory = inventoryRepository.getInventory();
		OxygenHistory history = historyRepository.getHistory();
		List<OxygenTank> newTanks = new ArrayList<>();
		OxygenCategories category = oxygenProduction.getCategory();

		Integer quantityToCover = inventory.requestTankByCategory(category, category, numberOfTanks);

		if (quantityToCover > 0) {
			LocalDate readyDate = oxygenProduction.calculateReadyDateForCategory(requestDate).getValue();
			newTanks = factory.createOxygenTank(oxygenProduction, requestDate, quantityToCover);

			if (category == OxygenCategories.E) {
				history.addTanksBought(readyDate, quantityToCover);
			} else {
				history.addMadeTanks(readyDate, quantityToCover);
				history.addCategoryProduction(requestDate, oxygenProduction.getCategory(), oxygenProduction.getNumberOfUnitsUsed() * quantityToCover); // TODO : Test this call
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