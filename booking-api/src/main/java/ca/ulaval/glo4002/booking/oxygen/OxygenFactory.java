package ca.ulaval.glo4002.booking.oxygen;

import ca.ulaval.glo4002.booking.festival.domain.Festival;
import ca.ulaval.glo4002.booking.passes.PassCategories;
import ca.ulaval.glo4002.booking.profits.Money;

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


	public List<OxygenTank> buildOxygenTank(OxygenCategory category, LocalDate requestDate, Integer quantityToCover) {
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

	public OxygenCategory buildCategory(PassCategories category) {
		switch(category) {
			case SUPERNOVA:
			    return buildCategory(OxygenCategories.E);
			case SUPERGIANT:
				return buildCategory(OxygenCategories.B);
			default:
			case NEBULA:
				return buildCategory(OxygenCategories.A);
		}
	}

	public OxygenCategory buildCategory(OxygenCategories category) {
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

	public OxygenCategory buildCategoryForRequestDate(LocalDate requestDate, OxygenCategories oxygenCategories) {
		// TODO : Use EventDate in OxygenFactory when OxygenDate is removed
		LocalDate readyBeforeDate = festival.getStartEventDate().plusDays(1).getValue();

		if(oxygenCategories == OxygenCategories.A) {
			if(requestDate.plusDays(20).isBefore(readyBeforeDate)){
				return buildCategory(PassCategories.NEBULA);
			} else if(requestDate.plusDays(10).isBefore(readyBeforeDate)) {
				return buildCategory(PassCategories.SUPERGIANT);
			} else {
				return buildCategory(PassCategories.SUPERNOVA);
			}
		} else if(oxygenCategories == OxygenCategories.B) {
			if(requestDate.plusDays(10).isBefore(readyBeforeDate)) {
				return buildCategory(PassCategories.SUPERGIANT);
			} else {
				return buildCategory(PassCategories.SUPERNOVA);
			}
		} else {
			return buildCategory(PassCategories.SUPERNOVA);
		}
	}
}