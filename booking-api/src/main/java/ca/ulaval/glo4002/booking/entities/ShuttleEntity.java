package ca.ulaval.glo4002.booking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "shuttles")
public class ShuttleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long shuttleCategoryId;
	private Long shuttleTypeId;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	//private List<Passenger> passengers;
	
	public ShuttleEntity() {
		
	}

	public ShuttleEntity(Long id, Long shuttleCategoryId, Long shuttleTypeId) {
		super();
		this.id = id;
		this.shuttleCategoryId = shuttleCategoryId;
		this.shuttleTypeId = shuttleTypeId;
	}
	
	public Long getId() {
		return id;
	}
	

}
