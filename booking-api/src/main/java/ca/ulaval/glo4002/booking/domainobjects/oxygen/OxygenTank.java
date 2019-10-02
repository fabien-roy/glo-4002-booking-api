package ca.ulaval.glo4002.booking.domainobjects.oxygen;

import ca.ulaval.glo4002.booking.domainobjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;

import java.time.LocalDate;

public class OxygenTank extends OrderItem {

	private OxygenCategory category;
	private LocalDate requestDate;
	private LocalDate readyDate;

	public OxygenTank(OxygenCategory category, LocalDate requestDate, LocalDate readyDate) {
        this.category = category;
		this.requestDate = requestDate;
		this.readyDate = readyDate;
	}

	public Double getPrice() {
		return 0.0; // TODO : Oxygen tank price calculation
	}

	public OxygenCategory getOxygenTankCategory() {
		return category;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public LocalDate getReadyDate() {
		return readyDate;
	}

}
