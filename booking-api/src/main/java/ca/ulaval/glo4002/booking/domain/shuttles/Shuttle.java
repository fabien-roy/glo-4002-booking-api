package ca.ulaval.glo4002.booking.domain.shuttles;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

public class Shuttle {

	private ShuttleCategories category;
	private Capacity maxCapacity;
	private Money price;
	
	
	public Shuttle(ShuttleCategories category, Capacity maxCapacity, Money price) {
		this.category = category;
		this.maxCapacity = maxCapacity;
		this.price = price;
	}
	
	public String getShuttleName() {
		return category.toString();
	}
	
	public Integer getMaxPassengersCapacity() {
		return maxCapacity.getNumberOfPeople();
	}
}
