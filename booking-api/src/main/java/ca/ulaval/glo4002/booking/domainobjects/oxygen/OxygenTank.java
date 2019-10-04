package ca.ulaval.glo4002.booking.domainobjects.oxygen;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;

import java.time.LocalDate;

public class OxygenTank {

    private Long id;
	private OxygenCategory category;
	private LocalDate requestDate;

	public OxygenTank(Long id, OxygenCategory category, LocalDate requestDate) {
	    this.id = id;
        this.category = category;
		this.requestDate = requestDate;
	}

	public OxygenTank(OxygenCategory category, LocalDate requestDate) {
		this.category = category;
		this.requestDate = requestDate;
	}

	public Double getPrice() {
		Double pricePerUnit = category.getProduction().getPricePerUnit();
		Long producedUnit = category.getProduction().getProducedUnits();
		Long producedTank = category.getProduction().getProducedTanks();

		return (pricePerUnit * producedUnit) / producedTank;
	}

	public Long getId() {
		return id;
	}

	public OxygenCategory getCategory() {
		return category;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

    public LocalDate getReadyDate() {
	    return requestDate.plusDays(category.getProduction().getProductionTime().toDays());
    }
}
