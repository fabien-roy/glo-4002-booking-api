package ca.ulaval.glo4002.booking.oxygen;

import ca.ulaval.glo4002.booking.passes.PassCategories;
import ca.ulaval.glo4002.booking.profits.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenFactory {

	public List<OxygenTank> buildOxygenTank(OxygenCategory category, LocalDate requestDate, Integer quantityToCover) {
		List<OxygenTank> newTanks = new ArrayList<>();
		OxygenDate requestedDate = new OxygenDate(requestDate);

		// TODO : category.getNumberOfTanksByBundle() != TDA
		while (quantityToCover > 0) {
			for (Integer i = 0; i < category.getNumberOfTanksByBundle(); i++) {
				newTanks.add(new OxygenTank(category, requestedDate));
			}

			quantityToCover -= category.getNumberOfTanksByBundle();
		}

		return newTanks;
	}

	public OxygenCategory buildCategory(PassCategories category) {
		switch(category) {
			case SUPERNOVA:
				return new OxygenCategory(
						OxygenCategories.E,
						5,
						0,
						1
				);
			case SUPERGIANT:
				return new OxygenCategory(
						OxygenCategories.B,
						3,
						10,
						3,
						8,
						new Money(BigDecimal.valueOf(600))
				);
			default:
			case NEBULA:
				return new OxygenCategory(
						OxygenCategories.A,
						3,
						20,
						5,
						15,
						new Money(BigDecimal.valueOf(650))
				);
		}
	}

}