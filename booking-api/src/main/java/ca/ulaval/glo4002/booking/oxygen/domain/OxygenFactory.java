package ca.ulaval.glo4002.booking.oxygen.domain;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.profits.domain.Money;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenFactory {

	private final Festival festival;

	@Inject
	public OxygenFactory(Festival festival) {
		this.festival = festival;
	}

	public List<OxygenTank> createOxygenTank(OxygenCategory category, LocalDate requestDate, Integer quantityToCover) {
		List<OxygenTank> newTanks = new ArrayList<>();
		OxygenDate requestedDate = new OxygenDate(requestDate);

		while (quantityToCover > 0) {
			for (Integer i = 0; i < category.getNumberOfTanksByBundle(); i++) {
				newTanks.add(new OxygenTank(category, requestedDate));
			}

			quantityToCover -= category.getNumberOfTanksByBundle();
		}

		return newTanks;
	}

	public OxygenCategory createCategory(PassCategories category) {
		switch(category) {
			case SUPERNOVA:
			    return createCategory(OxygenCategories.E);
			case SUPERGIANT:
				return createCategory(OxygenCategories.B);
			default:
			case NEBULA:
				return createCategory(OxygenCategories.A);
		}
	}

	// TODO : Can't OxygenFactory.createCategory only have PassCategories as a parameter?
	public OxygenCategory createCategory(OxygenCategories category) {
		switch(category) {
			case E:
				return new OxygenCategory(
						OxygenCategories.E,
						5,
						0,
						1,
						new Money(BigDecimal.valueOf(5000))
				);
			case B:
				return new OxygenCategory(
						OxygenCategories.B,
						3,
						10,
						3,
						8,
						new Money(BigDecimal.valueOf(600))
				);
			default:
			case A:
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

	public OxygenCategory createCategoryForRequestDate(LocalDate requestDate, OxygenCategories oxygenCategories) {
		// TODO : Use OxygenDate
		LocalDate readyBeforeDate = festival.getStartEventDate().plusDays(1).getValue();

		if(oxygenCategories == OxygenCategories.A) {
			if(requestDate.plusDays(20).isBefore(readyBeforeDate)){
				return createCategory(PassCategories.NEBULA);
			} else if(requestDate.plusDays(10).isBefore(readyBeforeDate)) {
				return createCategory(PassCategories.SUPERGIANT);
			} else {
				return createCategory(PassCategories.SUPERNOVA);
			}
		} else if(oxygenCategories == OxygenCategories.B) {
			if(requestDate.plusDays(10).isBefore(readyBeforeDate)) {
				return createCategory(PassCategories.SUPERGIANT);
			} else {
				return createCategory(PassCategories.SUPERNOVA);
			}
		} else {
			return createCategory(PassCategories.SUPERNOVA);
		}
	}
}