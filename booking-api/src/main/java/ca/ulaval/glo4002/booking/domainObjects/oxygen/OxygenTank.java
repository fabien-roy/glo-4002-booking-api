package ca.ulaval.glo4002.booking.domainObjects.oxygen;

import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.domainObjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainObjects.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.exceptions.InvalidDateException;
import ca.ulaval.glo4002.booking.services.OxygenTankService;

import java.time.LocalDate;

public class OxygenTank extends OrderItem {
	
	protected Long id;
	private OxygenCategory category;
	private LocalDate timeProduced;
	private LocalDate timeRequested;
	private OxygenTankService oxygenTankService = new OxygenTankService();

	public OxygenTank(OxygenCategory category, LocalDate timeRequested) {
		if (timeRequested.isAfter(FestivalConstants.Dates.START_DATE)) {
			throw new InvalidDateException();
		}
		
		this.category = oxygenTankService.getOxygenCategoryForTimeTable(category, timeRequested);
		this.timeRequested = timeRequested;
		this.timeProduced = timeRequested.plusDays(this.category.getProduction().getProductionTime().toDays());
	}

	@Override
	public Double getPrice() {
	    return 0.0; // TODO : Oxygen tank price calculation
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
