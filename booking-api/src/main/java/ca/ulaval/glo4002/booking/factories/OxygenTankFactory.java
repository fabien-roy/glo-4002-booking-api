package ca.ulaval.glo4002.booking.factories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenDate;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.domain.EventDate;
import ca.ulaval.glo4002.booking.enums.OxygenCategory;
import ca.ulaval.glo4002.booking.exceptions.oxygen.InvalidOxygenCategoryException;

public class OxygenTankFactory {

	// TODO : OXY : Refactor this whole thing, it is way, way to massive. (how ?)

	private OxygenTankInventory inventory;

	private static final Integer CATEGORY_A_CREATION_NUMBER = 5;
	private static final Integer CATEGORY_B_CREATION_NUMBER = 3;
	private static final Integer CATEGORY_E_CREATION_NUMBER = 1;

	public OxygenTankFactory(OxygenTankInventory inventory) {
		this.inventory = inventory;
	}

	public List<OxygenTank> buildOxygenTank(OxygenCategory category, LocalDate requestDate, Long numberOfDays) {
		List<OxygenTank> newTanks = new ArrayList<>();
		Integer quantityToCover = getQuantityToCoverForOrderCategory(category, numberOfDays);

		quantityToCover = inventory.requestTankByCategory(category, quantityToCover);
		OxygenCategory possibleCategory = getCategoryForRequestDate(category, requestDate);

		if (possibleCategory != category) {
			quantityToCover = checkInventory(category, possibleCategory, quantityToCover);
		}

		Integer numberOfTanksByBundle = getNumberOfTanksByCategoryForCreation(possibleCategory);
		OxygenDate requestedDate = new OxygenDate(requestDate);

		while (quantityToCover > 0) {
			for (Integer i = 0; i < numberOfTanksByBundle; i++) {
				newTanks.add(new OxygenTank(possibleCategory, requestedDate));
			}
			quantityToCover -= numberOfTanksByBundle;
		}

		// TODO : add in inventory from the factory or from somewhere else?
		return newTanks;
	}

	private Integer checkInventory(OxygenCategory category, OxygenCategory possibleCategory,
                                   Integer quantityToCover) {
		if (category == OxygenCategory.A) {
			quantityToCover = inventory.requestTankByCategory(OxygenCategory.B, quantityToCover);
			if (possibleCategory == OxygenCategory.E) {
				quantityToCover = inventory.requestTankByCategory(OxygenCategory.E, quantityToCover);
			}
		} else if (category == OxygenCategory.B) {
			quantityToCover = inventory.requestTankByCategory(OxygenCategory.E, quantityToCover);
		}

		return quantityToCover;
	}

	private Integer getNumberOfTanksByCategoryForCreation(OxygenCategory category) {
	    switch (category) {
            case A:
                return CATEGORY_A_CREATION_NUMBER;
            case B:
                return CATEGORY_B_CREATION_NUMBER;
            case E:
                return CATEGORY_E_CREATION_NUMBER;
            default:
                throw new InvalidOxygenCategoryException();
        }
	}

	private Integer getQuantityToCoverForOrderCategory(OxygenCategory category, Long numberOfDays) {
		if (category == OxygenCategory.E) {
			return (int) (numberOfDays * 5);
		} else {
			return (int) (numberOfDays * 3);
		}
	}

	// TODO : Refactor needed seem too complexe
	private OxygenCategory getCategoryForRequestDate(OxygenCategory category, LocalDate requestDate) {
		LocalDate readyBeforeDate = EventDate.START_DATE.plusDays(1);

		switch (category) {
            case A:
                if(requestDate.plusDays(20).isBefore(readyBeforeDate)) {
                    return OxygenCategory.A;
                } else if (requestDate.plusDays(10).isBefore(readyBeforeDate)) {
                    return OxygenCategory.B;
                } else {
                    return OxygenCategory.E;
                }
            case B:
                if(requestDate.plusDays(10).isBefore(readyBeforeDate)) {
                    return OxygenCategory.B;
                } else {
                    return OxygenCategory.E;
                }
            case E:
                return OxygenCategory.E;
            default:
                throw new InvalidOxygenCategoryException();
        }
	}

}