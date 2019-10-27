package ca.ulaval.glo4002.booking.dto;

import java.util.List;

import ca.ulaval.glo4002.booking.domain.oxygen.OxygenTank;

public class OxygenTankInventoryDto {
	private String oxygenCategory;
	List<OxygenTank> notInUseTanks;
	List<OxygenTank> inUseTanks;

	public OxygenTankInventoryDto(String oxygenCategory, List<OxygenTank> notInUseTanks, List<OxygenTank> inUseTanks) {
		this.oxygenCategory = oxygenCategory;
		this.notInUseTanks = notInUseTanks;
		this.inUseTanks = inUseTanks;
	}

	public String getOxygenCategory() {
		return oxygenCategory;
	}

	public List<OxygenTank> getNotInUseTanks() {
		return notInUseTanks;
	}

	public List<OxygenTank> getInUseTanks() {
		return inUseTanks;
	}

}
