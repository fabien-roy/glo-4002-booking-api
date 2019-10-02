package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Trips")
public class TripEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private LocalDate date;
	private Long typeId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shuttleId", nullable = false)
	private ShuttleEntity shuttle;

    // TODO : Passengers
	//@ManyToOne(cascade = CascadeType.ALL)
	// private List<PassengerEntity> passengerIds;

	public TripEntity() {

	}

	public TripEntity(Long id, LocalDate date, Long typeId) {
		this.id = id;
        this.date = date;
        this.typeId = typeId;
	}
	
	public Long getId() {
		return id;
	}

    public LocalDate getDate() {
        return date;
    }

    public Long getTypeId() {
        return typeId;
    }
    
    public ShuttleEntity getShuttle() {
    	return shuttle;
    }
}
