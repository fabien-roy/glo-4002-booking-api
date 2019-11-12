package ca.ulaval.glo4002.booking.producers;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenInventory;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.exceptions.oxygen.InvalidOxygenCategoryException;
import ca.ulaval.glo4002.booking.factories.OxygenFactory;
import ca.ulaval.glo4002.booking.repositories.OxygenInventoryRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenTankProducer {

    private OxygenInventoryRepository inventoryRepository;
	private OxygenFactory factory;

	@Inject
	public OxygenTankProducer(OxygenInventoryRepository inventoryRepository, OxygenFactory factory) {
	    this.inventoryRepository = inventoryRepository;
		this.factory = factory;
	}
	
	public List<OxygenTank> produceOxygenForOrder(OxygenCategories category, LocalDate requestDate, Integer numberOfDays) {
		OxygenInventory inventory = inventoryRepository.getInventory();
		List<OxygenTank> newTanks = new ArrayList<>();
		Integer quantityToCover = getQuantityToCoverForOrderCategory(category, numberOfDays);
		OxygenCategories possibleCategory;

		if (quantityToCover > inventory.getNotInUseQuantityByCategory(category)) {
			possibleCategory = getCategoryForRequestDate(category, requestDate);
			quantityToCover = inventory.requestTankByCategory(category, possibleCategory, quantityToCover);
		} else {
			possibleCategory = category;
			quantityToCover = inventory.requestTankByCategory(category, category, quantityToCover);
		}

		if (quantityToCover > 0) {
			newTanks.addAll(factory.buildOxygenTank(possibleCategory, requestDate, quantityToCover));

			inventory.addTanksToInventory(category, newTanks);
		}

		inventoryRepository.setInventory(inventory); // TODO : Is there a better way than this? Could the inventory actually be the repository?
		
		return newTanks;
	}
	
	private Integer getQuantityToCoverForOrderCategory(OxygenCategories category, Integer numberOfDays) {
		if (category.equals(OxygenCategories.E)) {
			return (numberOfDays * 5);
		} else {
			return (numberOfDays * 3);
		}
	}

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
            case E:
                return OxygenCategories.E;
            default:
                throw new InvalidOxygenCategoryException(category);
        }
	}
}