package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Passengers")
public class PassengerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tripId", nullable = false)
	private TripEntity trip;

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
}
