package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

public class OxygenTank {
	private Long id;
	private OxygenTankCategory category;
	private OxygenDate requestDate;
	private OxygenDate readyDate;
	private Money totalPrice;

	public OxygenTank(Long id, OxygenTankCategory category, OxygenDate requestDate) {
		this.id = id;
		this.category = category;
		this.requestDate = requestDate;
		this.setMoney();
		this.setReadyDate();
	}

	// TODO : generate an new ID ?
	public OxygenTank(OxygenTankCategory category, OxygenDate requestDate) {
		this.category = category;
		this.requestDate = requestDate;
		this.setMoney();
		this.setReadyDate();
	}

	private void setMoney() {
		this.totalPrice = this.calculateMoney();
	}

	private Money calculateMoney() {
		// TO DO adapt & set
		/*
		 * Double pricePerUnit = category.getProduction().getPricePerUnit(); Long
		 * producedUnit = category.getProduction().getProducedUnits(); Long producedTank
		 * = category.getProduction().getProducedTanks();
		 * 
		 * return (pricePerUnit * producedUnit) / producedTank;
		 */
		return null;
	}

	private void setReadyDate() {
		this.readyDate = this.calculateReadyDate();
	}

	private OxygenDate calculateReadyDate() {
		OxygenDate readyDate = this.requestDate;
		if (this.category == OxygenTankCategory.CATEGORY_A) {
			readyDate.addDays(20L);
		} else if (this.category == OxygenTankCategory.CATEGORY_B) {
			readyDate.addDays(10L);
		}
		return readyDate;
	}

	public Long getId() {
		return this.id;
	}

	public OxygenTankCategory getCategory() {
		return this.category;
	}

	public OxygenDate getRequestDate() {
		return this.requestDate;
	}

	public OxygenDate getReadyDate() {
		return this.readyDate;
	}

	public Money getMoney() {
		return this.totalPrice;
	}
}
