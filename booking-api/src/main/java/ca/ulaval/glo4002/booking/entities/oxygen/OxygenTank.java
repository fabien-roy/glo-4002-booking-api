package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.entities.orders.OrderItem;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.exceptions.InvalidEventDateException;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
public class OxygenTank extends OrderItem {
	
	@Id
	protected Long id;

	private OxygenCategory category;
	private LocalDate timeProduced;
	private LocalDate timeRequested;

	public OxygenTank(OxygenCategory category, LocalDate timeRequested) {
		if(timeRequested.isAfter(FestivalConstants.Dates.START_DATE)) {
			throw new InvalidEventDateException();
		}
		
		this.category = getOxygenCategoryForTimeTable(category, timeRequested);
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

	private OxygenCategory getOxygenCategoryForTimeTable(OxygenCategory category, LocalDate timeRequested) {
		Long timeToProduce = category.getProduction().getProductionTime().toDays();
		LocalDate timeReady = timeRequested.plusDays(timeToProduce);
		OxygenCategoryBuilder oxygenCategoryBuilder = new OxygenCategoryBuilder();

		if(timeReady.isBefore(FestivalConstants.Dates.START_DATE)) {
			return category;
		}
		else if(timeRequested.plus(10, DAYS).isBefore(FestivalConstants.Dates.START_DATE)){
			return  oxygenCategoryBuilder.buildById(OxygenConstants.Categories.B_ID);
		}
		else {
			return oxygenCategoryBuilder.buildById(OxygenConstants.Categories.E_ID);
		}
	}

}
