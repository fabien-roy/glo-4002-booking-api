package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Shuttles")
public class ShuttleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long shuttleCategoryId;
	private Double price;
	@OneToMany(mappedBy = "shuttle")
	private List<TripEntity> trips;

	public ShuttleEntity() {
		
	}

	public ShuttleEntity(Long id, Long shuttleCategoryId, Double price) {
		this.id = id;
		this.shuttleCategoryId = shuttleCategoryId;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getShuttleCategoryId() {
		return shuttleCategoryId;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public List<TripEntity> getTrips() {
		return trips;
	}
}
