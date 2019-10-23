package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.passes.money.Money;

public class OxygenTank {
	private Long id;
	private Long category;
	private OxygenDate requestDate;
	private OxygenDate readyDate;
	private Money totalPrice;

	public OxygenTank(Long id, Long category, OxygenDate requestDate) {
		this.id = id;
		this.category = category;
		this.requestDate = requestDate;
		this.setMoney();
		this.setReadyDate();
	}

	public OxygenTank(Long category, OxygenDate requestDate) {
		this.category = category;
		this.requestDate = requestDate;
		this.setMoney();
		this.setReadyDate();
	}

	private void setMoney() {
		// TO DO adapt & set
		/*
		 * Double pricePerUnit = category.getProduction().getPricePerUnit(); Long
		 * producedUnit = category.getProduction().getProducedUnits(); Long producedTank
		 * = category.getProduction().getProducedTanks();
		 * 
		 * return (pricePerUnit * producedUnit) / producedTank;
		 */
	}

	private void setReadyDate() {
		/*
		 * TODO adapt
		 * requestDate.plusDays(category.getProduction().getProductionTime().toDays());
		 */
	}

	public Long getId() {
		return this.id;
	}

	public Long getCategory() {
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

	private void calculateReadyDate() {

	}
}
