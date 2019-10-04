package ca.ulaval.glo4002.booking.entities;

import ca.ulaval.glo4002.booking.domainobjects.shuttles.ShuttleInventoryItem;

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
	private Double price; // TODO : TRANS : Probably useless
	@OneToMany(mappedBy = "shuttle") // TODO : TRANS : Probably useless
	private List<TripEntity> trips;
	@OneToMany(mappedBy = "shuttle")
	private List<PassEntity> passengers;
	@ManyToOne
	ShuttleInventoryEntity inventory;

	public ShuttleEntity() {
		
	}

	public ShuttleEntity(Long id, Long categoryId, Double price, List<PassengerEntity> passengers) {
		this.id = id;
		this.categoryId = categoryId;
		this.price = price;
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

	public Double getPrice() {
		return price;
	}
	
	public List<TripEntity> getTrips() {
		return trips;
	}

	public List<PassengerEntity> getPassengers() {
		return passengers;
	}

	public void setInventory(ShuttleInventoryEntity inventory) {
		this.inventory = inventory;
	}
}
