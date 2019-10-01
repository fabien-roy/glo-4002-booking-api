package ca.ulaval.glo4002.booking.domainObjects.oxygen;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.exceptions.InvalidDateException;
import ca.ulaval.glo4002.booking.services.OxygenTankServiceImpl;

import java.time.LocalDate;

public class OxygenTank {

	protected Long id;
	private OxygenCategory category;
	private LocalDate timeProduced;
	private LocalDate timeRequested;

	// TODO : A lot of logic here should be in a OxygenTankService class
	public OxygenTank(OxygenCategory category, LocalDate timeRequested) {
		if (timeRequested.isAfter(FestivalConstants.Dates.START_DATE)) {
			throw new InvalidDateException();
		}

		// TODO : Refactor, when calling constructor the services should already have
		// been called by the requester
		// TODO : and the correct category should by passed.
		OxygenTankServiceImpl oxygenTankService = new OxygenTankServiceImpl();

		this.category = oxygenTankService.getOxygenCategoryForTimeTable(category, timeRequested);
		this.timeRequested = timeRequested;
		this.timeProduced = timeRequested.plusDays(this.category.getProduction().getProductionTime().toDays());
	}

	public Double getPrice() {
		return 0.0; // TODO : Oxygen tank price calculation
	}

	public Long getId() {
		return this.id;
	}

	public OxygenCategory getOxygenTankCategory() {
		return category;
	}

	public LocalDate getTimeRequested() {
		return timeRequested;
	}

	public LocalDate getTimeProduced() {
		return timeProduced;
	}

}
