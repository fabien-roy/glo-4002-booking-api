package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "OxygenTanks")
public class OxygenTankEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long categoryId;
	private LocalDate requestDate;
	@ManyToOne
	OxygenTankInventoryEntity inventory;

	public OxygenTankEntity() {

	}

	public OxygenTankEntity(Long categoryId, LocalDate requestDate) {
		this.categoryId = categoryId;
		this.requestDate = requestDate;
	}

	public OxygenTankEntity(Long id, Long categoryId, LocalDate requestDate) {
		this.id = id;
		this.categoryId = categoryId;
		this.requestDate = requestDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setInventory(OxygenTankInventoryEntity inventory) {
		this.inventory = inventory;
	}
}
