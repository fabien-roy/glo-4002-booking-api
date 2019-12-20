package ca.ulaval.glo4002.booking.oxygen.domain;

import ca.ulaval.glo4002.booking.festival.domain.FestivalConfiguration;
import ca.ulaval.glo4002.booking.passes.domain.PassCategories;
import ca.ulaval.glo4002.booking.profits.domain.Money;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenFactory {

	private final FestivalConfiguration festivalConfiguration;

	@Inject
	public OxygenFactory(FestivalConfiguration festivalConfiguration) {
		this.festivalConfiguration = festivalConfiguration;
	}

	public List<OxygenTank> createOxygenTank(OxygenProduction production, Integer quantityToCover) {
		List<OxygenTank> newTanks = new ArrayList<>();
		int numberOfTanksByBundle = production.getNumberOfTanksByBundle();

		while (quantityToCover > 0) {
			for (int i = 0; i < numberOfTanksByBundle; i++) {
				newTanks.add(new OxygenTank(production));
			}

			quantityToCover -= numberOfTanksByBundle;
		}

		return newTanks;
	}

	public OxygenCategories createCategory(PassCategories category) {
		switch(category) {
			case SUPERNOVA:
			    return OxygenCategories.E;
			case SUPERGIANT:
				return OxygenCategories.B;
			default:
			case NEBULA:
				return OxygenCategories.A;
		}
	}

	public OxygenProduction createProduction(PassCategories passCategory) {
	    OxygenCategories oxygenCategory = createCategory(passCategory);

	    return createProduction(oxygenCategory);
	}

	public OxygenProduction createProduction(OxygenCategories oxygenCategory) {
		switch(oxygenCategory) {
			case E:
				return new OxygenProduction(
						OxygenCategories.E,
						5,
						0,
						1,
						new Money(BigDecimal.valueOf(5000))
				);
			case B:
				return new OxygenProduction(
						OxygenCategories.B,
						3,
						10,
						3,
						8,
						new Money(BigDecimal.valueOf(600))
				);
			default:
			case A:
				return new OxygenProduction(
						OxygenCategories.A,
						3,
						20,
						5,
						15,
						new Money(BigDecimal.valueOf(650))
				);
		}
	}

	public OxygenProduction createProductionForRequestDate(LocalDate requestDate, OxygenCategories oxygenCategories) {
		LocalDate readyBeforeDate = festivalConfiguration.getStartEventDate().plusDays(1).getValue();

		if (oxygenCategories == OxygenCategories.A) {
			if (requestDate.plusDays(20).isBefore(readyBeforeDate)){
				return createProduction(PassCategories.NEBULA);
			} else if (requestDate.plusDays(10).isBefore(readyBeforeDate)) {
				return createProduction(PassCategories.SUPERGIANT);
			} else {
				return createProduction(PassCategories.SUPERNOVA);
			}
		} else if (oxygenCategories == OxygenCategories.B) {
			if (requestDate.plusDays(10).isBefore(readyBeforeDate)) {
				return createProduction(PassCategories.SUPERGIANT);
			} else {
				return createProduction(PassCategories.SUPERNOVA);
			}
		} else {
			return createProduction(PassCategories.SUPERNOVA);
		}
	}
}