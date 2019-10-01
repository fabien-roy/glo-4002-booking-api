package ca.ulaval.glo4002.booking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Shuttles")
public class ShuttleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long shuttleCategoryId;
	private Long tripId;

	//@ManyToOne(cascade = CascadeType.ALL)
	//private List<Passenger> passengers;

	public ShuttleEntity() {
		
	}

	public ShuttleEntity(Long id, Long shuttleCategoryId, Long tripId) {
		this.id = id;
		this.shuttleCategoryId = shuttleCategoryId;
		this.tripId = tripId;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getShuttleCategoryId() {
		return shuttleCategoryId;
	}
	
	public Long getShuttleTypeId() {
		return tripId;
	}
}
