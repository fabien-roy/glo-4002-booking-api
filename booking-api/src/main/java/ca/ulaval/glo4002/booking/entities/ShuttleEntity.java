package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Shuttles")
public class ShuttleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long categoryId;
	private LocalDate date;
	@OneToMany(mappedBy = "shuttle")
	private List<PassengerEntity> passengers;
	@ManyToOne
	ShuttleInventoryEntity inventory;

	public ShuttleEntity() {
		
	}

	public ShuttleEntity(Long id, Long categoryId, List<PassengerEntity> passengers) {
		this.id = id;
		this.categoryId = categoryId;
		this.passengers = passengers;
	}

	public Long getId() {
		return id;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}

	public LocalDate getDate() {
		return date;
	}

	public List<PassengerEntity> getPassengers() {
		return passengers;
	}

	public void setInventory(ShuttleInventoryEntity inventory) {
		this.inventory = inventory;
	}
}
