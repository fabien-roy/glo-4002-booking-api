package ca.ulaval.glo4002.booking.domainObjects.oxygen;

import java.time.LocalDate;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.exceptions.InvalidEventDateException;
import ca.ulaval.glo4002.booking.parsers.ParsableEntity;
import ca.ulaval.glo4002.booking.services.OxygenTankService;

@Entity
public class OxygenTank implements ParsableEntity {

	@Id
	protected Long id;
	private OxygenCategory category;
	private LocalDate timeProduced;
	private LocalDate timeRequested;

	public OxygenTank(OxygenCategory category, LocalDate timeRequested) {
		if (timeRequested.isAfter(FestivalConstants.Dates.START_DATE)) {
			throw new InvalidEventDateException();
		}

		// TODO : Refactor, when calling constructor the services should already have
		// been called by the requester
		// TODO : and the correct category should by passed.
		OxygenTankService oxygenTankService = new OxygenTankService();

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
