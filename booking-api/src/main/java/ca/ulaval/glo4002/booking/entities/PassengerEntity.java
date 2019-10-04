package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;

@Entity(name = "Passengers")
public class PassengerEntity {

	@Id
	private Long id;
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
