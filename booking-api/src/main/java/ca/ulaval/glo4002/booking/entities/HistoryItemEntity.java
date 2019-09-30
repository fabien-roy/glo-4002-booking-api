package ca.ulaval.glo4002.booking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "HistoryItem")
public class HistoryItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public Long qtyOxygenTankBought;
	public Long qtyWaterUsed;
	public Long qtyCandlesUsed;
	public Long qtyOxygenTankMade;

	public HistoryItemEntity() {
	}

	public HistoryItemEntity(Long qtyOxygenTankBought, Long qtyWaterUsed, Long qtyCandlesUsed, Long qtyOxygenTankMade) {
		this.qtyOxygenTankBought = qtyOxygenTankBought;
		this.qtyWaterUsed = qtyWaterUsed;
		this.qtyCandlesUsed = qtyCandlesUsed;
		this.qtyOxygenTankMade = qtyOxygenTankMade;
	}
}
