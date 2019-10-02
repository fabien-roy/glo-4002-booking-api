package ca.ulaval.glo4002.booking.domainobjects.oxygen;

import java.time.LocalDate;

import ca.ulaval.glo4002.booking.constants.OxygenConstants;
import ca.ulaval.glo4002.booking.domainobjects.orders.OrderItem;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;

public class OxygenTank extends OrderItem {

	private OxygenCategory category;
	private LocalDate requestDate;
	private LocalDate readyDate;

	public OxygenTank(OxygenCategory category, LocalDate requestDate, LocalDate readyDate) {
		this.category = category;
		this.requestDate = requestDate;
		this.readyDate = readyDate;
	}

	// TODO Test this
	public Double getPrice() {
		// TODO Look if there is a more optimized way to do this
		Double pricePerUnit = 0.0;
		Long producedUnit = 0L;
		Long producedTank = 0L;

		if (this.category.getName() == "A") {
			pricePerUnit = OxygenConstants.Productions.SPARK_PLUGS_PRICE_PER_UNIT;
			producedUnit = OxygenConstants.Productions.SPARK_PLUGS_PRODUCED_UNITS;
			producedTank = OxygenConstants.Productions.SPARK_PLUGS_PRODUCED_TANKS;
		} else if (this.category.getName() == "B") {
			pricePerUnit = OxygenConstants.Productions.ELECTROLYTES_PRICE_PER_UNIT;
			producedUnit = OxygenConstants.Productions.ELECTROLYTES_PRODUCED_UNITS;
			producedTank = OxygenConstants.Productions.ELECTROLYTES_PRODUCED_TANKS;
		} else if (this.category.getName() == "E") {
			pricePerUnit = OxygenConstants.Productions.IMMEDIATE_PRICE_PER_UNIT;
			producedUnit = OxygenConstants.Productions.IMMEDIATE_PRODUCED_UNITS;
			producedTank = OxygenConstants.Productions.IMMEDIATE_PRODUCED_TANKS;
		}
		return (pricePerUnit * producedUnit) / producedTank;
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
