package ca.ulaval.glo4002.booking.factories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenDate;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTankInventory;
import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

public class OxygenTankFactory {

	private OxygenTankInventory inventory;

	private static final LocalDate START_OF_FESTIVAL_DATE = LocalDate.of(2050, 07, 17);
	private static final Integer CATEGORY_A_CREATION_NUMBER = 5;
	private static final Integer CATEGORY_B_CREATION_NUMBER = 3;
	private static final Integer CATEGORY_E_CREATION_NUMBER = 1;

	public OxygenTankFactory(OxygenTankInventory inventory) {
		this.inventory = inventory;
	}

	public List<OxygenTank> buildOxygenTank(OxygenTankCategory category, LocalDate requestDate, Long numberOfDays) {
		List<OxygenTank> newTanks = new ArrayList<>();
		Integer quantityToCover = getQuantityToCoverForOrderCategory(category, numberOfDays);

		quantityToCover = inventory.requestTankByCategory(category, quantityToCover);
		OxygenTankCategory possibleCategory = getCategoryForRequestDate(category, requestDate);

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

	private Integer checkInventory(OxygenTankCategory category, OxygenTankCategory possibleCategory,
			Integer quantityToCover) {
		if (category == OxygenTankCategory.CATEGORY_A) {
			quantityToCover = inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_B, quantityToCover);
			if (possibleCategory == OxygenTankCategory.CATEGORY_E) {
				quantityToCover = inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_E, quantityToCover);
			}
		} else if (category == OxygenTankCategory.CATEGORY_B) {
			quantityToCover = inventory.requestTankByCategory(OxygenTankCategory.CATEGORY_E, quantityToCover);
		}

		return quantityToCover;
	}

	// TODO : Switch case ? and no object member all in the method?
	private Integer getNumberOfTanksByCategoryForCreation(OxygenTankCategory category) {
		if (category == OxygenTankCategory.CATEGORY_A) {
			return CATEGORY_A_CREATION_NUMBER;
		} else if (category == OxygenTankCategory.CATEGORY_B) {
			return CATEGORY_B_CREATION_NUMBER;
		} else {
			return CATEGORY_E_CREATION_NUMBER;
		}
	}

	private Integer getQuantityToCoverForOrderCategory(OxygenTankCategory category, Long numberOfDays) {
		if (category == OxygenTankCategory.CATEGORY_E) {
			return (int) (numberOfDays * 5);
		} else {
			return (int) (numberOfDays * 3);
		}
	}

	// TODO : Refactor needed seem too complexe
	private OxygenTankCategory getCategoryForRequestDate(OxygenTankCategory category, LocalDate requestDate) {
		LocalDate readyBeforeDate = START_OF_FESTIVAL_DATE.plusDays(1);

		if (category == OxygenTankCategory.CATEGORY_A) {
			if (requestDate.plusDays(20).isBefore(readyBeforeDate)) {
				return OxygenTankCategory.CATEGORY_A;
			} else if (requestDate.plusDays(10).isBefore(readyBeforeDate)) {
				return OxygenTankCategory.CATEGORY_B;
			} else {
				return OxygenTankCategory.CATEGORY_E;
			}
		} else if (category == OxygenTankCategory.CATEGORY_B) {
			if (requestDate.plusDays(10).isBefore(readyBeforeDate)) {
				return OxygenTankCategory.CATEGORY_B;
			} else {
				return OxygenTankCategory.CATEGORY_E;
			}
		} else {
			return OxygenTankCategory.CATEGORY_E;
		}
	}

}