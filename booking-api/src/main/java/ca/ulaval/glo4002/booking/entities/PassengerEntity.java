package ca.ulaval.glo4002.booking.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Passengers")
public class PassengerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tripId", nullable = false)
	private TripEntity trip;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shuttleId", nullable = false)
	private ShuttleEntity shuttle;

	public PassengerEntity() {

	}

	public PassengerEntity(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

    public void setShuttle(ShuttleEntity shuttle) {
		this.shuttle = shuttle;
    }
}
