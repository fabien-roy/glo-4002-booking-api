package ca.ulaval.glo4002.booking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OxygenTankEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public Long catId;
	public String requestDate;

	public OxygenTankEntity(Long id, Long catId, String requestDate) {
		this.id = id;
		this.catId = catId;
		this.requestDate = requestDate;
	}
}
