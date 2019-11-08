package ca.ulaval.glo4002.booking.factories;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenDate;
import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.exceptions.oxygen.InvalidOxygenCategoryException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenTankFactory {

	private static final Integer CATEGORY_A_CREATION_NUMBER = 5;
	private static final Integer CATEGORY_B_CREATION_NUMBER = 3;
	private static final Integer CATEGORY_E_CREATION_NUMBER = 1;

	public OxygenTankFactory() {
		
	}

	public List<OxygenTank> buildOxygenTank(OxygenCategories category, LocalDate requestDate, Integer quantityToCover) {
		List<OxygenTank> newTanks = new ArrayList<>();
		Integer numberOfTanksByBundle = getNumberOfTanksByCategoryForCreation(category);
		OxygenDate requestedDate = new OxygenDate(requestDate);
		
		while (quantityToCover > 0) {
			for (Integer i = 0; i < numberOfTanksByBundle; i++) {
				newTanks.add(new OxygenTank(category, requestedDate));
			}
			quantityToCover -= numberOfTanksByBundle;
		}

		return newTanks;
	}

	private Integer getNumberOfTanksByCategoryForCreation(OxygenCategories category) {
	    switch (category) {
            case A:
                return CATEGORY_A_CREATION_NUMBER;
            case B:
                return CATEGORY_B_CREATION_NUMBER;
            case E:
                return CATEGORY_E_CREATION_NUMBER;
            default:
                throw new InvalidOxygenCategoryException(category);
        }
	}

}