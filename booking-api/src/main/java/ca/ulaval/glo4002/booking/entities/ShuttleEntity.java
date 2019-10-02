package ca.ulaval.glo4002.booking.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Shuttles")
public class ShuttleEntity extends OrderItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long shuttleCategoryId;
	private Double price;
	@OneToMany(mappedBy = "shuttle")
	private List<TripEntity> trips;

	public ShuttleEntity() {
		
	}

	public ShuttleEntity(Long id, Long shuttleCategoryId) {
		this.id = id;
		this.shuttleCategoryId = shuttleCategoryId;
	}
	
	@Override
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
