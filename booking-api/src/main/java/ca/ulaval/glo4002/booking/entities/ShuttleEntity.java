package ca.ulaval.glo4002.booking.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ca.ulaval.glo4002.booking.domainObjects.trips.Trip;

@Entity(name = "shuttles")
public class ShuttleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long shuttleCategoryId;
	private Long shuttleTypeId;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	//private List<Passenger> passengers;
	
	@OneToMany(mappedBy = "shuttle")
	private List<Trip> trips;
	
	public ShuttleEntity() {
		
	}

	public ShuttleEntity(Long id, Long shuttleCategoryId, Long shuttleTypeId, List<Trip> trips) {
		this.id = id;
		this.shuttleCategoryId = shuttleCategoryId;
		this.shuttleTypeId = shuttleTypeId;
		this.trips = trips;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getShuttleCategoryId() {
		return shuttleCategoryId;
	}
	
	public Long getShuttleTypeId() {
		return shuttleTypeId;
	}
	

}
