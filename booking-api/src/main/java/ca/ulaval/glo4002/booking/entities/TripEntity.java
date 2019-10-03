package ca.ulaval.glo4002.booking.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@OneToMany(mappedBy = "trip")
	private List<PassengerEntity> passengers;

	public TripEntity() {

	}

	public TripEntity(Long id, LocalDate date, Long typeId, ShuttleEntity shuttleEntity, List<PassengerEntity> passengers) {
		this.id = id;
        this.date = date;
        this.typeId = typeId;
        this.shuttle = shuttleEntity;
        this.passengers = passengers;
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
    
    public List<PassengerEntity> getPassengers() {
    	return passengers;
    }
}
