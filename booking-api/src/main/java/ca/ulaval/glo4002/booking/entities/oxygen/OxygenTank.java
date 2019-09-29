package ca.ulaval.glo4002.booking.entities.oxygen;

import ca.ulaval.glo4002.booking.builders.oxygen.OxygenCategoryBuilder;
import ca.ulaval.glo4002.booking.constants.FestivalConstants;
import ca.ulaval.glo4002.booking.entities.orders.OrderItem;
import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class OxygenTank extends OrderItem {
	
	@Id
	protected Long id;

	private OxygenCategory category;
	private LocalDate timeProduced;
	private LocalDate timeRequested;

	public OxygenTank(OxygenCategory category, LocalDate timeRequested) {
		this.category = getCategoryForExpectedTimeTable(category, timeRequested);
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

	private OxygenCategory getCategoryForExpectedTimeTable(OxygenCategory category, LocalDate timeRequested) {
		Long timeToProduce = category.getProduction().getProductionTime().toDays();
		LocalDate timeProduced = timeRequested.plusDays(timeToProduce);
		OxygenCategoryBuilder oxygenCategoryBuilder = new OxygenCategoryBuilder();

		if(timeProduced.isAfter(FestivalConstants.Dates.START_DATE)){
			category = oxygenCategoryBuilder.buildById(category.getId() - 1);
			category = getCategoryForExpectedTimeTable(category, timeRequested);
			return category;
		}
		else {
			return category;
		}
	}
}
