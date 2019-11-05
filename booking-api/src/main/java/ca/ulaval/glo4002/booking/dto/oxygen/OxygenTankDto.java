package ca.ulaval.glo4002.booking.dto.oxygen;

// TODO : OXY : Can someone PLEASE tell me what this is?
public class OxygenTankDto {

	private Long oxygenId;
	private String oxygenCategory;
	private String oxygenRequestDate;

	public OxygenTankDto(Long oxygenId, String oxygenCategory, String oxygenRequestDate) {
		this.oxygenId = oxygenId;
		this.oxygenCategory = oxygenCategory;
		this.oxygenRequestDate = oxygenRequestDate;
	}

	public Long getOxygenId() {
		return oxygenId;
	}

	public String getOxygenCategory() {
		return oxygenCategory;
	}

	public String getOxygenRequestDate() {
		return oxygenRequestDate;
	}
}
