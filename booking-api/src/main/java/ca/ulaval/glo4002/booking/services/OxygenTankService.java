package ca.ulaval.glo4002.booking.services;

import static java.time.temporal.ChronoUnit.*;

import java.time.LocalDate;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.categories.OxygenCategory;

public class OxygenTankService {

	public OxygenCategory getOxygenCategoryForTimeTable(OxygenCategory category, LocalDate timeRequested) {
		Long timeToProduce = category.getProduction().getProductionTime().toDays();
		LocalDate timeReady = timeRequested.plusDays(timeToProduce);
		OxygenCategoryBuilder categoryBuilder = new OxygenCategoryBuilder();

		if (timeReady.isBefore(FestivalConstants.Dates.START_DATE)) {
			return category;
		} else if (timeRequested.plus(10, DAYS).isBefore(FestivalConstants.Dates.START_DATE)) {
			return categoryBuilder.buildById(OxygenConstants.Categories.B_ID);
		} else {
			return categoryBuilder.buildById(OxygenConstants.Categories.E_ID);
		}
	}

	public void save(OxygenTank oxygenTank) {
		// TODO make this work
	}

	public Iterable<OxygenTank> findAll() {
		// TODO make this work
		return null;
	}
}
