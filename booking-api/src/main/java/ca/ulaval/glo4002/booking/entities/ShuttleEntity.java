package ca.ulaval.glo4002.booking.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Shuttles")
public class ShuttleEntity extends OrderItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long shuttleCategoryId;
	@OneToMany(mappedBy = "shuttle")
	private List<TripEntity> trips = new ArrayList<>();

	public ShuttleEntity() {
		
	}

	public ShuttleEntity(Long id, Long shuttleCategoryId) {
		this.id = id;
		this.shuttleCategoryId = shuttleCategoryId;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getShuttleCategoryId() {
		return shuttleCategoryId;
	}
}
