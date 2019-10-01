package ca.ulaval.glo4002.booking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Trips")
public class TripEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// private ShuttleEntity shuttle; // TODO : One to many

    // TODO : Passengers
	//@ManyToOne(cascade = CascadeType.ALL)
	// private List<PassengerEntity> passengerIds;

	public TripEntity() {

	}

	public TripEntity(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
