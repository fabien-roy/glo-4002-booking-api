package ca.ulaval.glo4002.booking.domain.shuttles;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.domain.Number;
import ca.ulaval.glo4002.booking.enums.ShuttleCategories;

public class Shuttle {

	private Number shuttleNumber;
	private ShuttleCategories category;
	private Integer maxCapacity;
	private Money price;
	
	
	public Shuttle(Number shuttleNumber, ShuttleCategories category, Integer maxCapacity, Money price) {
		this.shuttleNumber = shuttleNumber;
		this.category = category;
		this.maxCapacity = maxCapacity;
		this.price = price;
	}
	
	public Number getShuttleNumber() {
		return shuttleNumber;
	}
	
	public ShuttleCategories getCategory() {
		return category;
	}
	
	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public Money getPrice() {
		return price;
	}
}
